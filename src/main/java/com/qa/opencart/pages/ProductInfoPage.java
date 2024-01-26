package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	//to get HashMap in unordered collevtion
	//private Map <String, String> productmap= new HashMap<String, String> (); 
	
	//to get HashMap in an ordered collection
	private Map <String, String> productmap= new LinkedHashMap<String, String> ();
	
	//to get HashMap in alphabetical order/sorted collevtion
	//private Map <String, String> productmap= new TreeMap<String, String> ();


	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}


	public String getProductHeaderName()
	{
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println(productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImageCount()
	{
		int productImageCount = eleUtil.waitforVisibilityofAllElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product " +getProductHeaderName() + "Count "  +productImageCount);
		return productImageCount;
	}

	private void getProductMetaData()
	{
		//Brand: Apple
		//		Product Code: Product 18
		//		Reward Points: 800
		//		Availability: In Stock

		List<WebElement> metaDataList = eleUtil.waitforVisibilityofAllElements(productMetaData, AppConstants.SHORT_DEFAULT_WAIT);
		for(WebElement e : metaDataList)
		{
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue =metaData.split(":")[1].trim();
			productmap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData()
	{
		//		$2,000.00
		//		Ex Tax: $2,000.00

		List<WebElement> metaPriceList = eleUtil.waitforVisibilityofAllElements(productPricingData, AppConstants.SHORT_DEFAULT_WAIT);
		{
			String productPrice = metaPriceList.get(0).getText();
			String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim(); 
			
			productmap.put("Price", productPrice);
			productmap.put("ExTaxPrice", productExTaxPrice);
							
		}
	}
	
	public Map<String, String> getProductDetails()
	{
		productmap.put("ProductName", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productmap);
		return productmap;
	}
}
