package com.open.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//span[text()='My Tasks']")
	WebElement myTaskIcon;
	
		public HomePage(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Boolean waitForPageLoad() {
			// TODO Auto-generated method stub
			return seleniumGateway.waitForPageElement(myTaskIcon);
							
		}
		
		public Boolean isMyTasksIconDisplayed(){
			return myTaskIcon.isDisplayed();
		}
		
		public void navigateToProfilePage(){
			menuSection.selectSecondLevelMenuItem("Key Risk Indicators,KRI Profiles");
		
		}
		

}
