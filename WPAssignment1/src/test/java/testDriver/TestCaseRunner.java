package testDriver;

import org.testng.annotations.Test;

import pages.CurrencyConverterPage;
import pages.HomePage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestCaseRunner {
	//declaring the variables
	WebDriver driver;
	String baseURL = "https://www.westpac.co.nz";
	String driverPath = "Drivers\\chromedriver.exe";
	String result = "";
	int defaultWaitTime = 15;
	HomePage homePage;
	CurrencyConverterPage currencyConverterPage;
	boolean compare;
	
  @Test
  //Scenario - Verify the error message while no amount is entered in currency converter
  public void noAmountCurrencyConverterErrorMessageValidation() {
	  homePage = new HomePage(driver);
	  homePage.clickTravelAndMigrantLink();
	  homePage.clickCurrencyConverter();
	  currencyConverterPage = new CurrencyConverterPage(driver);
	  result = currencyConverterPage.getErrorMessage();
	  Assert.assertEquals(result, "Please enter the amount you want to convert.");
  }
  
  @Test(dataProvider = "dataProvider1", dataProviderClass = data.DataClass.class)
  //Scenario - Verify if 1 NZD is converted to other currencies
  public void currencyConverterValidation(String currencyFrom, String currencyTo, String amount) {
	  homePage = new HomePage(driver);
	  homePage.clickTravelAndMigrantLink();
	  homePage.clickCurrencyConverter();
	  currencyConverterPage = new CurrencyConverterPage(driver);
	  //convert the currency and compare the message - return true or false
	  compare = currencyConverterPage.convertCurrency(currencyFrom,currencyTo,amount);
	  Assert.assertEquals(compare, true);
  }
  
  @BeforeMethod
  public void setUP() {
	  //Launching the browser and navigating to url
	  System.setProperty("webdriver.chrome.driver", driverPath);
	  driver = new ChromeDriver();
	  //Setting the wait time
	  //driver.manage().timeouts().pageLoadTimeout(defaultWaitTime, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(defaultWaitTime, TimeUnit.SECONDS);
	  driver.get(baseURL);
	  driver.manage().window().maximize();
	  System.out.println("Browser Launched");
	  //DataClass.getData(sheetPath);
  }

  @AfterMethod
  public void tearDown() {
	  //Closing browser instances opened by driver
	  driver.quit();
	  System.out.println("Browser closed");
  }

}
