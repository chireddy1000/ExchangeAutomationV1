package com.exchange.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * Page Object Pattern 
 * wraps all elements, actions and 
 * validations happening on a page in 
 * one single object- Page Object. 
 * @author chire
 *
 */
public class LoginPage {
	WebDriver ldriver;
    //Similar to the other "initElements" methods, 
	// but takes an ElementLocatorFactory which is used for providing 
	//the mechanism for finding elements. 
	// If the ElementLocatorFactory returns null then the field won't be decorated.
	public LoginPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(id="loginform-modal-username")
	WebElement login;		
	
	@FindBy(id="loginform-modal-loginHeaderPassword")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement loginBtn;
	
	@FindBy(xpath ="//*[@id=\"atg_store_container\"]/header/div[2]/div/div[4]/div/div/a")
	WebElement logoutLink;
	
	@FindBy(id="logout-button-mobile")
	WebElement logoutBtn;
	
	
	
	public void setLogin(String pLogin) {
		login.sendKeys(pLogin);
	}
	public void setPassword(String pPassword) {
		password.sendKeys(pPassword);
	}
	public void clickLogin() {
		loginBtn.click();
	}  
	public void clickLogout() {
		logoutLink.click();		
	}
	public void clickLogoutBtn() {
		logoutBtn.click();		
	}
}
