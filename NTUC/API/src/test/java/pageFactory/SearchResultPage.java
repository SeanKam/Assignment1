package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Search Result page displaying list of results that matches the search text containing the user name
public class SearchResultPage {

	protected WebDriver driver;
	private WebDriverWait explicitWait= null;

	@FindBy(xpath="//main[@id='js-pjax-container']/div[1]/div[2]/nav[1]/a[10]")
	private WebElement link_Users;

	public SearchResultPage(WebDriver wDriver) {
		driver = wDriver;
		explicitWait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	public void clickLinkUsers() {
		WebElement ele = explicitWait.until(ExpectedConditions.visibilityOf(link_Users));
		ele.click();
	}
	
	
	public void clickUserByGivenName(String fullName) throws Exception {
		
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.linkText(fullName));
		ele.click();
	}
	
}
