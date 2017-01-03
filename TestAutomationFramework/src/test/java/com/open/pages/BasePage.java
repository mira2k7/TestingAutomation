package com.open.pages;

import com.common.seleniumlib.SeleniumGateway;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected SeleniumGateway seleniumGateway;
	protected MenuSection menuSection;
	
	public BasePage(WebDriver driver){
		this.driver=driver;
		this.seleniumGateway = new SeleniumGateway(driver);
		this.menuSection=new MenuSection(driver);
	}
	
	public abstract Boolean waitForPageLoad();
	
	

}
