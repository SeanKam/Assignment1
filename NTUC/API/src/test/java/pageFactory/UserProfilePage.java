package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;

//Profile page of a specific user
public class UserProfilePage {

	protected WebDriver driver;
	private WebDriverWait explicitWait= null;

	@FindBy(xpath="//a[@data-tab-item='repositories']")
	private WebElement link_Repo;
	
	public UserProfilePage(WebDriver wDriver) {
		driver = wDriver;
		explicitWait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	public void clickRepositoriesLink() {
		WebElement ele = explicitWait.until(ExpectedConditions.visibilityOf(link_Repo));
		ele.click();
	}
	

}
