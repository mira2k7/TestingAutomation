package com.open.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="username")
    WebElement username;
	
	@FindBy(id="password")
    WebElement password;
	
	@FindBy(css=".messageTitles.errorMessagesCol2")
    WebElement errorMessageElement;
	
	@FindBy(id="com.link.logout")
    WebElement logoutButton;
	
	@FindBy(id="submit.button")
    WebElement loginButton;
	
	@Override
	public Boolean waitForPageLoad() {
		// TODO Auto-generated method stub
		return true;
		
				
	}
	
	public void doLogin(String username, String password){
		this.username.clear();
		this.password.clear();
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
	}
	
	public void logout(){
		if (!seleniumGateway.getCurrentPageUrl().endsWith("log.off.do") && !seleniumGateway.getCurrentPageUrl().endsWith("log.on.do") && !seleniumGateway.getCurrentPageUrl().endsWith("authenticate.do"))
				this.logoutButton.click();
		
	}
	
	public String getErrorMessage(){
		return errorMessageElement.getText();
	}

}
