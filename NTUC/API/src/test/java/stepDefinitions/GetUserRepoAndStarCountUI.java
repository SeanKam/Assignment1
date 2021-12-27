package stepDefinitions;

import org.openqa.selenium.WebDriver;

import pageFactory.HomePage;
import pageFactory.RepositoryPage;
import pageFactory.SearchResultPage;
import pageFactory.UserProfilePage;
import pageFactory.UserRepositoriesPage;

public class GetUserRepoAndStarCountUI {

	//Map<String, String> data;

	private HomePage HomePagePF = null;
	private SearchResultPage SearchResultPagePF = null;
	private UserProfilePage userProfilePF = null;
	private RepositoryPage repositoryPagePF = null;
	private UserRepositoriesPage userRepositoriesPagePF = null;
	
	public GetUserRepoAndStarCountUI(WebDriver driver) {
		init(driver);
	}
	
	//Fill in the user name in openingGitHub page 
	public void enterUserName(String userName) {
		HomePagePF.enterUserName(userName);
	}
	
	//Click hyperlink 'Users' tab
	public void clickUserLink() {
		SearchResultPagePF.clickLinkUsers();
	}

	//Click hyperlink of user's full name AFTER 'Users' tab is clicked to load user profile page
	public void clickUser(String fullName) throws Exception {
		SearchResultPagePF.clickUserByGivenName(fullName);
	}
	
	//On User profile page, click 'Repositories' to display the list of repository belonging to the user 
	public void clickUserRepositoriesLink() throws Exception {
		userProfilePF.clickRepositoriesLink();
	}
	
	//Click on the specific repository shown in the webpage
	public void clickRepoLink(String repoName) throws Exception {
		userRepositoriesPagePF.clickSelRepository(repoName);
	}

	//get star count displayed on the repository page
	public String getStarCount() throws Exception {
		return repositoryPagePF.getStarCount();
	}
	
	private void init(WebDriver driver) {
		HomePagePF = new HomePage(driver);
		SearchResultPagePF = new SearchResultPage(driver);
		userProfilePF = new UserProfilePage(driver);
		repositoryPagePF = new RepositoryPage(driver);
		userRepositoriesPagePF = new UserRepositoriesPage(driver);
		
	}
}
