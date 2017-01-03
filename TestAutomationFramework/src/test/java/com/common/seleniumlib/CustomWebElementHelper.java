package com.common.seleniumlib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomWebElementHelper {
	WebElement webelement;
	
	public CustomWebElementHelper(WebElement webelement){
		this.webelement=webelement;
		
	}
	
	public By getByObject(){
		return ((CustomLocatingElementHandler)webelement).getElementLocator();
		
	}

}
