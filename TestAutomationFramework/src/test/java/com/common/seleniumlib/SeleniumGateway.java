package com.common.seleniumlib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumGateway {
	WebDriver webdriver;
	public SeleniumGateway(WebDriver webdriver){
	this.webdriver=webdriver;
	}
	public WebElement getWebElement(By locator,Integer... timeout){
	try{	
		int defaultTimeout = 10;
		if(timeout!=null && timeout.length>0)
			defaultTimeout = timeout[0];
		return new WebDriverWait(webdriver,defaultTimeout).until(ExpectedConditions.presenceOfElementLocated(locator));
	}catch(Throwable e){
		return null;
		
	}
	
	}
	
	public Boolean waitForPageElement(WebElement webelement){
		try {
			By byObject=new CustomWebElementHelper(webelement).getByObject();
			new WebDriverWait(webdriver,35).until(ExpectedConditions.presenceOfElementLocated(byObject));
			return true;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public Boolean waitForPageElementDisplayed(WebElement webelement){
		try {
			new WebDriverWait(webdriver,35).until(ExpectedConditions.visibilityOf(webelement));
			return true;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
	
	public String getCurrentPageUrl(){
		return webdriver.getCurrentUrl();
	}
}
