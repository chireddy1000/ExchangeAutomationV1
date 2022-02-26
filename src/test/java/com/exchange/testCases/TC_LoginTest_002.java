package com.exchange.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.exchange.pageObjects.LoginPage;
import com.exchange.utilities.XLUtils;

public class TC_LoginTest_002 extends BaseClass {

	@Test(priority = 1)
	public void loginClick() throws InterruptedException {
		 driver.get(baseURL);		 
		 logger.info("URL is Opened : " + baseURL);
		
		 driver.findElement(By.linkText("Sign in")).click();
		
		Thread.sleep(1000);
	}
	
	@DataProvider(name="logindata")
	String[][] getLoginData() throws IOException{
		String path = System.getProperty("user.dir") + "/src/test/java/com/exchange/testdata/loginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1"); 
		int colcount = XLUtils.getCellCount(path, "Sheet1",1);
			
		String[][] loginData = new String[rownum][colcount];
		//row
		for(int i=1; i <= rownum;i++) {
			// if j starts 1 you need to j <= else <
			for(int j=0;j<colcount;j++) {
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);  // (1,0)
			}
		}
		return loginData;
	}
	
	@Test(dataProvider="logindata")
	public void loginTest(String loginName,String password) throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setLogin(loginName);
		lp.setPassword(password);
		lp.clickLogin();
		
		if(driver.findElement(By.className("logout-link")).isDisplayed()) {
			Assert.assertTrue(true);
			lp.clickLogout();
			lp.clickLogoutBtn();						
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
	//user defined method created to check alert is present or not
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}catch (NoAlertPresentException e) {
			return false;
		}
	}
}
