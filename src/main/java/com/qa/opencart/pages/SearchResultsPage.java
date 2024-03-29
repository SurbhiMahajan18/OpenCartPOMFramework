package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}


	public ProductInfoPage selectProduct(String productName)
	{
		
		eleUtil.waitforVisibilityofElement(By.linkText(productName), AppConstants.SHORT_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}
}
