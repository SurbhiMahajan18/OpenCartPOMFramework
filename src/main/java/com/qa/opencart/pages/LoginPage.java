package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;

	private ElementUtil eleUtil;

	//By locators
	private By userName= By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotFwdLink = By.linkText("Forgotten Password11");
	private By logo =By.cssSelector("img[title = 'naveenopencart']");
	private By registerLink = By.xpath("//div[@class='list-group']//a[contains(text(),'Register')]");

	//public page const...

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//page actions /methods:

	public String getLoginPageTitle()
	{
		String title=eleUtil.waitforTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title is :" +title);
		return title;
	}

	public String getLoginPageURL()
	{
		String url = eleUtil.waitforURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page URL " +url);
		return url;
	}

	public boolean isForgotPwdLinkexistsorNot()
	{
		//return driver.findElement(forgotFwdLink).isDisplayed();	
		return eleUtil.waitforVisibilityofElement(forgotFwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();

	}

	public boolean isLogoExist()
	{
		return eleUtil.waitforVisibilityofElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("Username is " +username+ "Password is " +pwd);
		eleUtil.waitforVisibilityofElement(userName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//		driver.findElement(userName).sendKeys(username);
		//		driver.findElement(password).sendKeys(pwd);
		//		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	public UserRegistrationPage navigateToRegisterPage()
	{
		eleUtil.waitforVisibilityofElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT).click();
		return new UserRegistrationPage(driver);
	}

}
