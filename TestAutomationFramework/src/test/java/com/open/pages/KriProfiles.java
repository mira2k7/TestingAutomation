package com.open.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.common.util.Retry;

public class KriProfiles extends BasePage {
	
	public KriProfiles(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css=".tabButtonContent")
    WebElement advancedLink;
	
	@FindBy(id="objectListView1_delete")
    WebElement deleteIcon;
	
	@FindBy(xpath="//select[@dojoattachpoint='fieldSelector']")
	WebElement filterByCategory;

	@FindBy(xpath="//label[text()='Select Values...']")
	WebElement selectValueLink;
	
	@FindBy(xpath="//input[@type='button' and @value='Filter' and not(@disabled)]")
	WebElement filterButton;
	
	@FindBy(css=".idxLink")
	List<WebElement> allKriProfileNames;
	
	@FindBy(xpath="//div[text()='KRI Profile Name']")
    WebElement kriProfileColumnName;
	
	
	@Override
	public Boolean waitForPageLoad() {
		// TODO Auto-generated method stub
		return seleniumGateway.waitForPageElement(kriProfileColumnName);
				
	}
	
	public void clickAdvancedButton(){
		advancedLink.click();
	}
	
	public void selectCategoryToFilter(String filterCategory){
		Select dropDownBox = new Select(filterByCategory); 
		dropDownBox.selectByVisibleText(filterCategory);
	}
	
	public void clickSelectValueLink(){
		selectValueLink.click();
	}
	
	public void switchToPopupDialog(){
		
	}
	

	public void clickFilterButton(){
		filterButton.click();
	}
	
	
	public Boolean isDeleteIconEnabled(){
		if(deleteIcon.getAttribute("aria-disabled")==null)
			return true;
		return !deleteIcon.getAttribute("aria-disabled").equalsIgnoreCase("true");
	}
	
	
	private int getProfileIndex(String profileName){
		int index = 0;
		for(WebElement element : allKriProfileNames){
			if(element.getText().equalsIgnoreCase(profileName))
				return index;
			++index;
		}
		return -1;
		
	}
		
	public void selectFirstKriProfile(){
		 new Retry(5).untilNoException(new Runnable(){  
	    	  public void run()
	    	  {
	    		  String firstTitleCellXpath ="//a[@class='idxLink'][1]/following::td";
	    			String selectedRowXpath = "//div[@rowindex='0' and contains(@class,'gridxRowSelected')]";
	    		  seleniumGateway.getWebElement(By.xpath(firstTitleCellXpath)).click();
	    		    if(seleniumGateway.getWebElement(By.xpath(selectedRowXpath), 2)==null)
	    		    	throw new RuntimeException("Error Selecting Row");
	    		    
	    	  }
		 });
		
		
	}
	
	
	

	
}
