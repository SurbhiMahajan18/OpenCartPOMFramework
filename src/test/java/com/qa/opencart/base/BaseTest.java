package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.UserRegistrationPage;

public class BaseTest {

	protected WebDriver driver;
	 DriverFactory df;
	protected LoginPage loginPage;
	protected UserRegistrationPage resigterPage ;
	protected AccountsPage accPage;
	protected Properties prop;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	
	//@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName)
	{
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		driver  = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
		
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
