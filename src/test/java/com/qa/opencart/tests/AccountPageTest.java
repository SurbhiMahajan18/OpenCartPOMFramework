package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountPageTest extends BaseTest{

	@BeforeClass
	public void accSetUp()
	{
		accPage = new AccountsPage(driver);
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest()
	{
		Assert.assertTrue(accPage.getAccountPageTitle().contains(AppConstants.ACCOUNTS_PAGE_TITLE));
	}

	@Test
	public void accPageURLTest()
	{
		Assert.assertTrue(accPage.getAccountPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogOutExistTest()
	{
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

	@Test
	public void isSearchExistsTest()
	{
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void accPageHeadersCountTest()
	{
		List<String> actAccPageHeadersList = 	accPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		//Assertion for this how?????????
		Assert.assertEquals(actAccPageHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	}
	

	@Test
	public void accPageHeadersTest()
	{
		List<String> actAccPageHeadersList = 	accPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		//Assertion for this how?????????
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACC_PAGE_HEADERS_TEST);
	}

	
	@Test
	public void searchTest()
	{
		searchResultsPage =accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
}
