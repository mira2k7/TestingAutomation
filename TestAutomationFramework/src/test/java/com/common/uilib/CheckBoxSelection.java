package com.common.uilib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxSelection extends BaseUIElement{
	
	private WebElement element;
	
	public CheckBoxSelection(WebDriver webdriver,WebElement element) {
		super(webdriver);
		this.element = element;
	}

	public void selectCheckbox() {
		try {
            if (element.isSelected()) {
               System.out.println("Checkbox: " + element + "is already selected");
            } else {
            	// Select the checkbox
                element.click();
               // System.out.println("element selected");
            }
        } catch (Exception e) {
        	System.out.println("Unable to select the checkbox: " + element);
        }
		
	}
	
	public void deselectCheckbox() {
		try {
            if (element.isSelected()) {
            	//De-select the checkbox
                element.click();
            } else {
            	//System.out.println("Checkbox: "+element+"is already deselected");
            }
        } catch (Exception e) {
        	System.out.println("Unable to deselect checkbox: "+element);
        }
    }		

}
