package com.open.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.common.uilib.BaseUIElement;


public class MenuSection extends BaseUIElement {
	public MenuSection(WebDriver driver){
		super(driver);
			
	}
	
	private void shortSleep(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectSecondLevelMenuItem(String menuHierarchy){
		String[] menuItems=menuHierarchy.split(",");
		for(int i=0; i<10; ++i){
			WebElement parentMenuItem=seleniumGateway.getWebElement(By.xpath("//span[text()='"+menuItems[0]+"']"));
			parentMenuItem.click();
			if(seleniumGateway.getWebElement(By.xpath("span[text()='"+menuItems[0]+"']/parent::div[contains(@class,'popupOpen')]"),0)!=null){break;}
			
		}
		seleniumGateway.getWebElement(By.xpath("//span[text()='"+menuItems[1]+"']")).click();
	}
		
}


