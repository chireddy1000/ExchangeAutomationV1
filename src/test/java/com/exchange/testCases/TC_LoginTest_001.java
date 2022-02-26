package com.exchange.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.exchange.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginClick() throws InterruptedException {
		 driver.get(baseURL);		 
		 logger.info("URL is Opened : " + baseURL);
		
		 driver.findElement(By.linkText("Sign in")).click();
		
		Thread.sleep(1000);
	}
	@Test
	public void loginTest() throws InterruptedException {
		
		
		
		//WebDriverWait  wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOf(element));
		//element.click();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setLogin("ramatest");
		lp.setPassword("aafes123");
		lp.clickLogin();
		
		if(driver.findElement(By.className("logout-link")).isDisplayed()) {
			Assert.assertTrue(true);			
		}else {
			try {
				captureSceen(driver, "login_tc001");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(false);
		}
		
		Thread.sleep(1000);
	}
}
