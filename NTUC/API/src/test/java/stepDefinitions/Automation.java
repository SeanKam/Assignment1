package stepDefinitions;

import resources.Utils;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

//import cucumber.api.Scenario;


public class Automation extends Utils {
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	Map<String, String> dataMap;
	
	private static String firstRepoName=null;
	private static String fullName=null;
	private static int firstStarCount=0;

	public WebDriver driver=null;
	
	private static final Logger logger = LogManager.getLogger(Automation.class);
	//private Scenario s;
	
	public GetUserRepoAndStarCountUI GetUserRepoAndStarCount = null;

	
	private String gitUserName = null;
	
	@Before
	public void before() throws Exception {
		
		java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
		java.util.Properties properties = new Properties();
		properties.load(inputStream);

		//mvn clean test -Duser=torvalds
		 //mvn clean test -Duser=defunkt
		 
		gitUserName = properties.getProperty("user");
		System.out.println("gitUserName is:" + gitUserName);
		if(gitUserName==null || gitUserName.equals("${gitUserName}")) {
			 gitUserName = "torvalds";
		}
	 
	}


	public void tearDown() throws Exception {		
		if(driver!=null) {
			driver.quit();
		}
		logger.info("Teardown Completed-----------------------");
	}
	
	@Given("initialize API environment")
	public void setupAPIEnvironment() throws Exception {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@After
	public void cleanUp() throws Exception {
		tearDown();
	}

	@Given("I specify the username of a GitHub User in CLI")
	public void getUserFromGitHub() throws Exception {
		req = given().spec(requestSpecification())
				.contentType(ContentType.JSON);

	}
	
	@Given("Get User Repositories from GitHub")
	public void getUserRepo() throws Exception {
		req = given().spec(requestSpecification())
				.contentType(ContentType.JSON);

	}
	
	@Then("Above information of the user is printout")
	public void printUserInfo(DataTable testData) throws Exception {

		int responseCode = response.getStatusCode();
		if(responseCode!=200) {
			System.out.println("HTTP Response is : " + responseCode + ". ABORT TASK 1");
			Assert.fail();
		}
		
		dataMap = testData.asMap(String.class, String.class);
		String username = dataMap.get("gitHub_Username");
		String name = dataMap.get("gitHub_name");
		String createdOn = dataMap.get("gitHub_AccountCreatedOn");

		System.out.println("--------------------");
		System.out.println("Username is : " + getJsonPath(response, username));
		System.out.println("Name is : " + getJsonPath(response, name));
		
		
		fullName = getJsonPath(response, name); 
		System.out.println("Account created at : " + getJsonPath(response, createdOn));
		System.out.println("--------------------");
	}
	
	
	@Then("Above information of each repository belonging to user is printout")
	public void getGitHubUserRepoRequest() throws Exception {
		int responseCode = response.getStatusCode();
		if(responseCode!=200) {
			System.out.println("HTTP Response is:  " + responseCode + ". ABORT TASK 1");
			Assert.fail();
		}
		
		JsonPath jsonPathEvaluator=response.jsonPath();
		
		List <Integer> allRepoStarCount = jsonPathEvaluator.getList("stargazers_count");
		List <String> allRepoNames = jsonPathEvaluator.getList("name");
		
		int totalRepositories = allRepoNames.size();
		
		
		String repoName = null;
		Integer RepoStarInt = null;
		
	     for (int i = 0; i < totalRepositories; i++) {
	    	 repoName = allRepoNames.get(i);
	    	 RepoStarInt = allRepoStarCount.get(i);
	    	 System.out.println("--------------------------------");
	    	 System.out.println("Repository " + String.valueOf(i+1) + ": " + repoName);
	    	 System.out.println("Star count: " + RepoStarInt.toString());
	    	 System.out.println("Number of Releases: " + getNumOfReleaseOfEachRepo(repoName));
	    	 
	     }

	     //Save the FIRST repo name and its STAR count in the above list, which will be reference in #Task2
		firstRepoName=allRepoNames.get(0);
		firstStarCount=allRepoStarCount.get(0);

	}		

	@Given("I navigate to GitHub main page")
	public void navigateToGitHubPage(DataTable testData) throws Exception {
		dataMap = testData.asMap(String.class, String.class);
		String url = dataMap.get("webURL");
//		logger.info("url is: " + url);
//		logger.info("repoName is: " + firstRepoName);
//		logger.info("starCount is: " + firstStarCount);
		if(firstRepoName==null) {
			System.out.println("Task 1 did not complete successfully. ABORT Task 2");
			Assert.fail();
		}
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get(url);
		GetUserRepoAndStarCount = new GetUserRepoAndStarCountUI(driver);
		
	}
	
	@When("I search for the same user and navigate to his Repositories page")
	public void navigateToUserRepositoriesPage() throws Exception {
		GetUserRepoAndStarCount.enterUserName(gitUserName);

		GetUserRepoAndStarCount.clickUserLink();

		GetUserRepoAndStarCount.clickUser(fullName);
		
		GetUserRepoAndStarCount.clickUserRepositoriesLink();
		
	}
	
	@And("I navigate to the Repository that is returned FIRST in the list in TASK 1")
	public void clickRepository() throws Exception {
		GetUserRepoAndStarCount.clickRepoLink(firstRepoName);
	}
	
	@Then("I verify the star count shown in webpage is equal to value returned by the API")
	public void verifyStarCountValue() throws Exception {
		String str_starCountUI = GetUserRepoAndStarCount.getStarCount();
		System.out.println("\n--------------------------------");
		System.out.println("Star Count displayed in Repository '" + firstRepoName + "' Webpage is: " + str_starCountUI);
		
		//strFirstStarCount is the value returned by API in #Task 1
		String strFirstStarCount = Integer.toString(firstStarCount);

		//the star count value is in thousand and more, convert it to in 'k' 
		if(strFirstStarCount.length()>=4) {
			float fnum =Float.parseFloat(strFirstStarCount);
			strFirstStarCount = formatValue(fnum);
			//System.out.println("strFirstStarCount AFTER CONVERSION is : " + strFirstStarCount);
		}
		
		
		System.out.println("API Star Count for Repository '" + firstRepoName + "' is: " + strFirstStarCount);
		
		//Compare the value displayed in UI versus the value return by API
		Assert.assertEquals(str_starCountUI, strFirstStarCount);
		System.out.println("Star Count returned by API for Repository: '" + firstRepoName + "' is the SAME as the value displayed in the Repository webpage");
		System.out.println("--------------------------------");

	}

	private String formatValue(float value) {
	    String arr[] = {"", "k", "m", "b", "t", "p", "e"};
	    int index = 0;
	    while ((value / 1000) >= 1) {
	        value = value / 1000;
	        index++;
	    }
	    DecimalFormat decimalFormat = new DecimalFormat("#.#");
	    return String.format("%s%s", decimalFormat.format(value), arr[index]);
	}
	
	
	@When("I Send GET request to get this user information")
	public void getGitHubUser() throws Exception {
		response =req.queryParam("").when().get("users/" + gitUserName);

	}		

	@When("I Send GET request to get this user Repositories")
	public void getGitHubUserAllRepo() throws Exception {
		response =req.queryParam("").when().get("users/" + gitUserName + "/repos");

	}		

	private int getNumOfReleaseOfEachRepo(String repoName) throws IOException {
		req = given().spec(requestSpecification())
				.contentType(ContentType.JSON);

		response =req.queryParam("").when().get("repos/" + gitUserName + "/" + repoName + "/releases");
		
		JsonPath jsonPathEvaluator=response.jsonPath();
		
		List <String> totalReleaseVer = jsonPathEvaluator.getList("name");
		if (totalReleaseVer==null) {
			return 0;
		}
		
		return totalReleaseVer.size();
		
	}
	
	
}
