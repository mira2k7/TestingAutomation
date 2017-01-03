package com.common.uilib;


import org.openqa.selenium.WebDriver;

import com.common.seleniumlib.SeleniumGateway;

public class BaseUIElement {
	protected SeleniumGateway seleniumGateway;
	public BaseUIElement(WebDriver webdriver){
		seleniumGateway=new SeleniumGateway(webdriver);
		
	}
}
