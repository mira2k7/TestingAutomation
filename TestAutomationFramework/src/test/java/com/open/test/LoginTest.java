package com.open.test;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.common.util.ReportingUtil;
import com.open.dataobjects.LoginDataObjects;
import com.open.pages.HomePage;
import com.open.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Test(dataProvider="readFromDataSource")
	public void testOPLogin(LoginDataObjects loginDataObjects){
		LoginPage login=getPageObject(LoginPage.class);
		login.doLogin(loginDataObjects.userName, loginDataObjects.password);
		if(loginDataObjects.expectedErrorMessage!=null && loginDataObjects.expectedErrorMessage.trim().length()>0){
			Assert.assertEquals(login.getErrorMessage().trim(), loginDataObjects.expectedErrorMessage.trim());
		}
		else
		{
			Assert.assertTrue(((HomePage)getPageObject(HomePage.class)).isMyTasksIconDisplayed(),"Successful login attempt failed");
		}
		loginDataObjects.reportData();
	
		
	}
}
	
	
	

