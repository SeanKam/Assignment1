package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//List of repositories belonging to a specific user
public class UserRepositoriesPage {
	
	protected WebDriver driver;
	private WebDriverWait explicitWait= null;

	@FindBy(id="your-repos-filter")
	private WebElement textBox_SearchFilter;
	
	public UserRepositoriesPage(WebDriver wDriver) {
		driver = wDriver;
		explicitWait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public void searchRepoName(String repoName) throws Exception {
		WebElement ele = explicitWait.until(ExpectedConditions.visibilityOf(textBox_SearchFilter));
		ele.sendKeys(repoName);
		Thread.sleep(2000);
	}

	public void clickSelRepository(String repoName) throws Exception {
		searchRepoName(repoName);

		WebElement ele = driver.findElement(By.linkText(repoName));
		ele = explicitWait.until(ExpectedConditions.visibilityOf(ele));
		ele.click();
		
	}
}
