package com.qa.opencat.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;


	public JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
		js = (JavascriptExecutor)this.driver;
	}

	public String getTitleByJS()
	{
		return js.executeScript("return document.title").toString();
	}

	public String getURLByJS()
	{
		return js.executeScript("return document.URL").toString();

	}


	public void generateJSAlert(String mesg) {

		js.executeScript("alert('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}


	public void generateJSConfirmation(String mesg) {

		js.executeScript("confirm('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSPrompt(String mesg, String value) {

		js.executeScript("prompt('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}

	public void goBackWithJS()
	{
		js.executeScript("history.go(-1)");
	}

	public void goForwardWithJS()
	{
		js.executeScript("history.go(+1)");	
	}

	public void goRefreshWithJS()
	{
		js.executeScript("history.go(0)");	
	}

	public String getPageInnerText()
	{
		return	js.executeScript("return document.documentElement.innerText").toString();
	}


	public void scrollPageDownJS()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollMiddlePageUpJS()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight)/2",0);
	}


	public void scrollMiddlePageDownJS()
	{
		js.executeScript("0, window.scrollTo(document.body.scrollHeight)/2");
	}

	public void scrollPageUpJS()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollPageDownJS(String height)
	{
		js.executeScript("window.scrollTo(0, '"+height+"')");
	}

	public void scrollIntoViewJS(WebElement ele)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", ele);

	}
	
	




}







