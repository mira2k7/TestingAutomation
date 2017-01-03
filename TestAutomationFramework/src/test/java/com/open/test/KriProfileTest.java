package com.open.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.open.dataobjects.AllKriDataObjects;
import com.open.pages.HomePage;
import com.open.pages.KriProfiles;
import com.open.pages.KriSelectValuesPopup;
import com.open.pages.LoginPage;

public class KriProfileTest extends BaseTest{
	@Test(dataProvider="readFromDataSource")
	public void testKriDeleteProfileIcon(AllKriDataObjects allKriDataObjects){

		LoginPage login=getPageObject(LoginPage.class);
		login.doLogin(allKriDataObjects.loginData.userName, allKriDataObjects.loginData.password);

		HomePage homePage=getPageObject(HomePage.class);
		homePage.navigateToProfilePage();

		KriProfiles profile=getPageObject(KriProfiles.class);
		profile.clickAdvancedButton();

		profile.selectCategoryToFilter(allKriDataObjects.kriData.filterCategory);
		profile.clickSelectValueLink();

		KriSelectValuesPopup kriPopup= getPageObject(KriSelectValuesPopup.class);
		kriPopup.selectCheckBoxForFilterValue(allKriDataObjects.kriData.filterValue);
		kriPopup.clickApplyButton();

		profile.clickFilterButton();
		profile.selectFirstKriProfile();

		Assert.assertEquals(profile.isDeleteIconEnabled().toString(), allKriDataObjects.kriData.isDeleteButtonEnabled);
		allKriDataObjects.reportData();
		
		
	}
}

