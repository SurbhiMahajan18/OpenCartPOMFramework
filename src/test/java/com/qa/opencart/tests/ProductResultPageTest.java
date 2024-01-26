package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencat.utils.ExcelUtils;

public class ProductResultPageTest extends BaseTest {
	@BeforeClass
	public void productInfoSetUp()
	{
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	//@DataProvider
//	public Object[][] getSearchData()
//	{
//		return new Object[][] {
//			{"MacBook" , "MacBook Pro", 4 },
//			{"MacBook" , "MacBook Air", 4 },
//			{"iMac" , "iMac", 3 },
//			{"Samsung" , "Samsung SyncMaster 941BW" , 1},
//		};
//	}
	
	
	@DataProvider
	public Object[][] getSearchExcelTestData()
	{
		return ExcelUtils.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);	
	}
	
	
	@Test(dataProvider ="getSearchExcelTestData" )
	public void ProductImageTest(String searchKey,String productName, String imageCount)
	{
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);	
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImageCount()), imageCount);	
	}
	
	@Test
	public void ProductInfoTest()
	{
		searchResultsPage = accPage.doSearch("MacBook Pro");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("ExTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}

}
