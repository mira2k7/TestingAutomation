package com.common.uilib;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
	String parentWindowHandler;
	String subWindowHandler;
	WebDriver driver;
	
	public WindowHandler(WebDriver driver){
	parentWindowHandler = driver.getWindowHandle(); 
	subWindowHandler = null;

	Set<String> handles = driver.getWindowHandles();
	Iterator<String> iterator = handles.iterator();
	while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	}
	}
	
	public void switchToParentWindow(){
		driver.switchTo().window(parentWindowHandler); 
	}
	
	public void switchToSubWindow(){
	driver.switchTo().window(subWindowHandler); 
	}
          
}
