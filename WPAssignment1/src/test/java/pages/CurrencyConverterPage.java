package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CurrencyConverterPage {
	WebDriver driver;
	String sMessage;
	
	@FindBy(id = "ConvertFrom")
	public WebElement liConvertFrom;
	
	@FindBy(id ="Amount")
	public WebElement txtAmount;
	
	@FindBy(id = "ConvertTo")
	public WebElement liConvertTo;
	
	@FindBy(id="convert")
	public WebElement btnConvert;
	
	@FindBy(xpath = "//*[@id=\"errordiv\"]/ul/li")
	public WebElement lblErrorMsg;
	
	@FindBy(id = "westpac-iframe")
	public WebElement iFrame;
	
	public CurrencyConverterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Method to fetch the error message text when no amount entered in amount field
	public String getErrorMessage() {
		driver.switchTo().frame(iFrame);
		btnConvert.click();
		return lblErrorMsg.getText();
	}
	
	//Method converts currencies and validates if success message is displayed.
	//input arguments: convert from value, convert to value, and amount value
	public boolean convertCurrency (String sConvertFrom, String sConvertTo, String sAmount) {
		
		try {
			driver.switchTo().frame(iFrame);
			Select from = new Select(liConvertFrom);
			Select to = new Select(liConvertTo);
			from.selectByVisibleText(sConvertFrom);
			txtAmount.sendKeys(sAmount);
			to.selectByVisibleText(sConvertTo);
			btnConvert.click();
			
			//Fetch the success message text 
			sMessage = driver.findElement(By.xpath("//*[@id=\"resultsdiv\"]/em")).getText();
			
			//Validate if "Convert from" and "Convert to" currency values are present in the text and return true or false.
			if (sMessage.contains(sConvertFrom) && sMessage.contains(sConvertTo)) {
				System.out.println("success");
				return true;
			}
			else {
				return false;
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.err.println("Element not found");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
