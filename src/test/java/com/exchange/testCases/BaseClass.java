package com.exchange.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.exchange.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	//public static String baseURL = "https://www.shopmyexchange.com";
	public String baseURL = readconfig.getApplicationURL();
	public static WebDriver driver;
	public static Logger logger;
	
	// System.getProperty("user.dir")  use only in java
	//./ use in java and properties
	//  ./Drivers\\chromedriver.exe
	@Parameters("browser")
	@BeforeClass
	public void openBrowser(String br) {
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		}else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.getFireFoxPath());
			driver = new FirefoxDriver();
		}else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		logger =  Logger.getLogger("exchange");
		PropertyConfigurator.configure("Log4j.properties");
	}
    @AfterClass
	public void closeBrowser() {
		driver.quit();
	}
    
    public void captureSceen(WebDriver ldriver,String tName) throws IOException{
    	TakesScreenshot ts = (TakesScreenshot) ldriver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File target = new File(System.getProperty("user.dir")+"/screenshots/"+tName+".png");
    	FileUtils.copyFile(source,target);
    	System.out.println("Screen shot captured");
    }
}
