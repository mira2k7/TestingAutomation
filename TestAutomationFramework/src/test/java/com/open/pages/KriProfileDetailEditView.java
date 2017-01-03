package com.open.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KriProfileDetailEditView extends BasePage{
	
	public KriProfileDetailEditView(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(xpath="//a[text()='Instance Creation Helper']")
	WebElement instanceCreationHelperLink;
	
	@Override
	public Boolean waitForPageLoad() {
		// TODO Auto-generated method stub
		return seleniumGateway.waitForPageElement(instanceCreationHelperLink);
				
	}
	
	public void clickKriInstanceCreationHelperLink(){
		instanceCreationHelperLink.click();
	}
}
