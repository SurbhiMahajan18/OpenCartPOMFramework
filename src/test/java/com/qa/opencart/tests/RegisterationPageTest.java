package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ExcelUtils;

public class RegisterationPageTest extends BaseTest 

{
	@BeforeClass
	public void regSetUp()
	{
		resigterPage = loginPage.navigateToRegisterPage();
		
	}

	public String getRandomEmailID()
	{
		return "testautomation"+ System.currentTimeMillis()+"@opencart.com";
		//return "testautomation"+ UUID.randomUUID()+"@opencart.com";	
	}

	//@DataProvider
//	public Object[][] getUserRegistrationData()
//	{
//		return new Object[][] {
//			{"Rahul", "Sharma", "9367464532", "rahul@123", "rahul@123", "yes"},
//			{"Karishma", "Automation", "965412390", "test@123", "test@123", "no"},
//			{"Jyothi", "Kumar", "9367465554", "test@123", "test@123", "yes"},
//			
//		};
//	}

	@DataProvider
	public Object[][] getUserTestExcelData()
	{
		Object[][] regData = ExcelUtils.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	
	@Test(dataProvider = "getUserTestExcelData")
	public void userRegTest(String fname, String lname, String tphone, String password, String subscribe)
	{
		boolean isRegDone = resigterPage.userRegisterationForm(fname, lname, getRandomEmailID(), tphone, password, subscribe);
		Assert.assertTrue(isRegDone);
	}
}
