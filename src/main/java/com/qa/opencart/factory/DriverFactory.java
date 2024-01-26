package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop)
	{
		String browserName=prop.getProperty("browser");
		System.out.println("broser name is :" +browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome" :
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));//tldriver.set(webdriver value)
		//	driver= new ChromeDriver(optionsManager.getChromeOptions());
			break;

		case "firefox" :
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;

		case "edge" :
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver= new EdgeDriver(optionsManager.getEdgeOptions());
			break;

		default:
			System.out.println("Please pass the right browser name....  " +browserName);
			throw new FrameworkException("no browser found");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}

	public Properties initProp()
	{

		//mvn clean install -Denv="qa"
		FileInputStream fis=null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("env name is " +envName);
		try {
			if(envName == null)
			{
				fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}
			else
			{
				switch(envName.toLowerCase())
				{
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					fis = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "prod":
					fis = new FileInputStream("./src/test/resources/config/config.prod.properties");
					break;
				case "stage":
					fis = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					fis = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;


				default:
					System.out.println("please pass the right env name. ." +envName);
					throw new FrameworkException("Wrong env name : " +envName);

				}
			}
		}catch(FileNotFoundException e) {

		}



		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	
	public static String getScreenshot(String methodName)
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+ "/screenshot/" +methodName+ " " +System.currentTimeMillis()+ ".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
}










