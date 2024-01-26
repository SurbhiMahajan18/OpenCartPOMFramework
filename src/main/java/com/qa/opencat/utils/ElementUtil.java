package com.qa.opencat.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

public class ElementUtil {

	private WebDriver driver;

	//this driver referenced constructor is used so driver does not becomes null
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
	}

	public By getBy(String locatorType, String locatorValue)
	{
		By by = null;
		switch(locatorType.toLowerCase().trim())
		{
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "cssSelector":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
		default:
			System.out.println("wrong locator type is passed " +locatorType);
			throw new FrameworkException("wrong locator type is passed");	
		}

		return by;
	}
	public WebElement getElement(By locator)
	{
		return driver.findElement(locator);	
	}

	public void doSendKeys(String locatorType, String locatorValue, String value)
	{
		getElement(getBy(locatorType,locatorValue)).sendKeys(value);
	}


	public void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator)
	{
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue)
	{
		getElement(locatorType, locatorValue).click();
	}

	public String doElementGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public String doElementGetText(String locatorType, String locatorValue)
	{
		return getElement(locatorType, locatorValue).getText();
	}
	


	public WebElement getElement(String locatorType, String locatorValue)
	{

		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public String doGetElementAttribute(By locator, String attributeName )
	{
		return getElement(locator).getAttribute(attributeName);	
	}

	public  List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}


	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}

	//WAF to capture specfic attribute from the list
	public List<String> getElementsTextList(By locator)
	{
		List <WebElement> eleList= getElements(locator);	
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e:eleList)
		{
			String text = e.getText();
			if(text.length()!=0)
			{
				eleTextList.add(text);	
			}

		}
		return  eleTextList;
	}

	//WAF to capture the text of all the page links and return list<String>
	public List<String> getElementsAttributeList(By locator, String attrName)
	{
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleAttriList = new ArrayList <String>();
		for(WebElement e: eleList)
		{
			String attrValue = e.getAttribute(attrName);
			eleAttriList.add(attrValue);
		}
		return eleAttriList;

	}

	public void Search(By searchField, By suggestions, String key, String suggName) throws InterruptedException
	{
		doSendKeys(searchField, key);
		Thread.sleep(2000);
		List <WebElement> suggList=getElements(suggestions);
		System.out.println(suggList.size());
		for(WebElement e: suggList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.contains(suggName))
			{e.click();
			break;
			}
		}
	}

	//*****************Select drop down utils**********************//

	private Select createSelect(By locator)
	{
		Select select = new Select(getElement(locator));
		return select;
	}

	public void doSelectDropDown(By locator, int index)
	{
		createSelect(locator).selectByIndex(index);	
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText)
	{
		createSelect(locator).selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value)
	{
		createSelect(locator).selectByValue(value);
	}

	public int getDropDownOptionsCount(By locator)
	{
		return createSelect(locator).getOptions().size();
	}

	public List<String> getDropDownOptions(By locator)
	{

		List<WebElement> optionsList = createSelect(locator).getOptions();
		List <String> optionsTextList = new ArrayList<String>();
		System.out.println(optionsList.size());

		for(WebElement e: optionsList )
		{
			String text = e.getText();
			optionsTextList.add(text);

		}
		return optionsTextList;
	}
	public void selectDropDownOption(By locator, String dropDownValue )
	{
		List<WebElement> optionsList = createSelect(locator).getOptions();
		System.out.println(optionsList.size());

		for(WebElement e: optionsList )
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals("India"))
			{
				e.click();
				break;
			}
		}		
	}

	public void selectDropDownValue(By locator, String value)
	{
		List <WebElement> optionsList = getElements(locator);
		for(WebElement e: optionsList)
		{
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}	
	}


	/**************************Actions Utils*****************/

	public void parentChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).perform();
		Thread.sleep(2000);
		doClick(childMenuLocator);
	}


	/*-------------------------WebDriver waits---------------------*/
	public  WebElement waitforVisibilityofElement(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
	}

	public  List waitforVisibilityofAllElements(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public  List waitforPresenceofAllElements(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public void doClickWithWait(By locator, int timeOut)
	{
		waitforVisibilityofElement(locator,  timeOut).click();
	}

	public void doSeneKeysWithWait(By locator, int timeOut, String text)
	{
		waitforVisibilityofElement(locator,  timeOut).sendKeys(text);
	}

	public  WebElement waitforVisibilityofElement(By locator, int timeOut, int internalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(internalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
	}

	public String waitforTitleContains(String titleFraction, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try{if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		}
		catch(TimeoutException e) {
			System.out.println(titleFraction+ " title value not present");
			e.printStackTrace();
		}
		return null;
	}

	public String waitforTitleIs(String title, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try{if(wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		}
		catch(TimeoutException e) {
			System.out.println(title+ " title value not present");
			e.printStackTrace();
		}
		return null;
	}


	public  String waitforURLContains(String url, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try{if(wait.until(ExpectedConditions.urlContains(url))) {
			
			return driver.getCurrentUrl();
		}
		}
		catch(TimeoutException e) {
			System.out.println(url+ " URL value not present");
			e.printStackTrace();
		}
		return null;
	}

	public  String waitforURLToe(String fullurl, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try{if(wait.until(ExpectedConditions.urlToBe(fullurl))) {
			return driver.getCurrentUrl();
		}
		}
		catch(TimeoutException e) {
			System.out.println(fullurl+ " URL value not present");
			e.printStackTrace();
		}
		return null;
	}

	/*------------------- wait alert utilities--------------------*/
	public Alert waitForJSAlert(int timeOut) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public  void acceptJSAlert(int timeOut) 
	{
		waitForJSAlert(timeOut).accept();
	}

	public void dismissJSAlert(int timeOut) 
	{
		waitForJSAlert(timeOut).dismiss();
	}
	public String getJSAlertText(int timeOut) 
	{
		return waitForJSAlert(timeOut).getText();
	}

	public void getValueonJSAlert(int timeOut, String val) 
	{
		waitForJSAlert(timeOut).sendKeys(val);
	}
}

