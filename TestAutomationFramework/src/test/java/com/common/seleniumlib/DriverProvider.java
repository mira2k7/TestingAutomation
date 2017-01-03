package com.common.seleniumlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.common.config.ExecutionParameters;
import com.open.test.BaseTest;

public class DriverProvider extends BaseTest{
	private ExecutionParameters testParameters;

	public DriverProvider(ExecutionParameters config){
		this.testParameters=config;
	}

	public WebDriver getDriver(){
		WebDriver driver=null;
		if(testParameters.browserType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver",testParameters.ieDriverPath);
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		else if(testParameters.browserType=="ff"){
			driver=new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver",testParameters.chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); 
			driver=new ChromeDriver(options);
			
		}
		driver.get(testParameters.testurl);
		return driver;
		}
	}

