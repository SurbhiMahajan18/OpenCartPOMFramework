package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class LoginPageTest extends BaseTest{

	@Test(priority=1)
	public void LoginPageTitleTest()
	{
		
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginPageURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority=3)
	public void forgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkexistsorNot());
	}
	
	@Test(priority=4)
	public void appLogoExistTest()
	{
		Assert.assertTrue(loginPage.isLogoExist());	
	}
	
	@Test(priority=5)
	public void loginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	
}

