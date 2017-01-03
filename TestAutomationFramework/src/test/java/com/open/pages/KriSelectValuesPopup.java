package com.open.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.common.uilib.CheckBoxSelection;

public class KriSelectValuesPopup extends BasePage {
	@FindBy(xpath="//span[@dojoattachpoint='buttonBar']/button[text()='Apply']")
	WebElement applyButton;
	
	public KriSelectValuesPopup(WebDriver driver){
		super(driver);
	}
	
	@Override
	public Boolean waitForPageLoad() {
		// TODO Auto-generated method stub
		return seleniumGateway.waitForPageElement(applyButton);
				
	}
	
		
	public void selectCheckBoxForFilterValue(String label){
		new CheckBoxSelection(driver,seleniumGateway.getWebElement(By.xpath("//input[@label='"+label+"']"))).selectCheckbox();			  
	}
		
	public void deselectCheckBoxForFilterValue(String label){
	    new CheckBoxSelection(driver,seleniumGateway.getWebElement(By.xpath("//input[@label='"+label+"']"))).deselectCheckbox();			  
	}
	
	public void clickApplyButton(){
		applyButton.click();
		
	}
	
	
	
}
