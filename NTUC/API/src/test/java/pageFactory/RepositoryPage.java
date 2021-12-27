package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;

//Repository page of a specific user
public class RepositoryPage {
	protected WebDriver driver;
	private WebDriverWait explicitWait= null;

	@FindBy(id="repo-stars-counter-star")
	private WebElement txtg_starCount;
	
	public RepositoryPage(WebDriver wDriver) {
		driver = wDriver;
		explicitWait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	public String getStarCount() {
		WebElement ele = explicitWait.until(ExpectedConditions.visibilityOf(txtg_starCount));
		//System.out.println("Star Count displayed in webpage is: " + ele.getText());
		return ele.getText();

	}
}
