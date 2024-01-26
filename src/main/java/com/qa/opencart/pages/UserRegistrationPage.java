package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ElementUtil;

public class UserRegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.cssSelector("input#input-firstname");
	private By lastName = By.cssSelector("input#input-lastname");
	private By email = By.cssSelector("input#input-email");
	private By telephone = By.cssSelector("input#input-telephone");
	private By passw = By.cssSelector("input#input-password");
	private By passwConfirm = By.cssSelector("input#input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	
	private By logOut =By.linkText("Logout");
	private By registerLink = By.xpath("//div[@class='list-group']//a[contains(text(),'Register')]");

	private By agreement = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");

	private By successMessage = By.cssSelector("div#content>h1");

	public UserRegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userRegisterationForm(String firstName, String lastName, 
			String email, String telephone, String passw, String subscribe)
	{
		eleUtil.waitforVisibilityofElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);

		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.passw, passw);
		eleUtil.doSendKeys(passwConfirm, passw);
		
		if(subscribe.equalsIgnoreCase("Yes"))
		{
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);	
		}
		//		
		eleUtil.doClick(agreement);
		eleUtil.doClick(continueBtn);

		String successMsg = eleUtil.waitforVisibilityofElement(successMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(successMsg);
		if(successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG))
		{
			eleUtil.doClick(logOut);
			eleUtil.doClick(registerLink);
			return true;
			
		}
		else
		{
			return false;

		}
	}

}
