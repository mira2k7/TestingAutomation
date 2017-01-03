package com.open.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KriProfileDetail extends BasePage {
	
	public KriProfileDetail(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[text()='Actions']")
	WebElement kriProfileDetailActionButton;

	@FindBy(xpath="//td[text()='Edit this KRI Profile...']")
	WebElement editKriProfileLink;
	
	@Override
	public Boolean waitForPageLoad() {
		// TODO Auto-generated method stub
		return seleniumGateway.waitForPageElement(kriProfileDetailActionButton);
				
	}

	public void clickActionsButton(){
		kriProfileDetailActionButton.click();
	}
	
	public void clickEditProfileLink(){
		editKriProfileLink.click();
	}

}
