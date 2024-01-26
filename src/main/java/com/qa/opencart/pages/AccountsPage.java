package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ElementUtil;


public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private  By logOutLink = By.linkText("Logout");
	private By nameSearch =By.name("search");
	private By accHeaders =By.cssSelector("div#content>h2");
	private By searchButton = By.cssSelector("div#search button");
	

	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	//page actions:
	public String getAccountPageTitle()
	{
		String title=eleUtil.waitforTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page title is :" +title);
		return title;
	}

	public String getAccountPageURL()
	{
		String url = eleUtil.waitforURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page URL " +url);
		return url;
	}
	public boolean isLogOutLinkExist()
	{
		return eleUtil.waitforVisibilityofElement(logOutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	public void logOut()
	{
		if(isLogOutLinkExist())
		{
			eleUtil.doClick(logOutLink);
		}
	}

	public boolean isSearchFieldExist()
	{
		return eleUtil.waitforVisibilityofElement(nameSearch, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();	
	}

	
	public List<String> getAccountHeaders()
	{
		List<WebElement> headersList = eleUtil.waitforVisibilityofAllElements(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList =new ArrayList<String>();
		for(WebElement e:headersList)
		{
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}


	public SearchResultsPage doSearch(String searchKey)
	{
		eleUtil.waitforVisibilityofElement(nameSearch,AppConstants.SHORT_DEFAULT_WAIT).clear();
		eleUtil.waitforVisibilityofElement(nameSearch,AppConstants.SHORT_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
		
	}
	
	
}
