package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

//GitHub Home page
public class HomePage {

	protected WebDriver driver;
	private WebDriverWait explicitWait= null;

	@FindBy(xpath="//input[@placeholder='Search GitHub']")
	private WebElement textBox_Search;

	public HomePage(WebDriver wDriver) {
		driver = wDriver;
		explicitWait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String userName) {
		WebElement ele = explicitWait.until(ExpectedConditions.visibilityOf(textBox_Search));
		ele.sendKeys(userName);
		ele.sendKeys(Keys.ENTER);
	}

	
}
