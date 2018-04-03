package pages;

import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	@FindBy(id = "ubermenu-section-link-fx-travel-and-migrant-ps")
	private WebElement lkTravelAndMigrant;
	
	@FindBy(id = "ubermenu-item-cta-currency-converter-ps")
	private WebElement btnCurrencyConverter;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Hover over FX, Travel and Migrant link
	public void clickTravelAndMigrantLink() {
		Actions action = new Actions(driver);
		action.moveToElement(lkTravelAndMigrant).perform();
	}
	
	//Click on Currency Converter button
	public void clickCurrencyConverter() {
		try {
			btnCurrencyConverter.click();
			System.out.println("Clicked on Currency calculator");
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			System.err.println("Element Doesn't Exist");
		}
	}

}
