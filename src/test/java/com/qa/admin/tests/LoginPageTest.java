package com.qa.admin.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.admin.base.BaseTest;
import com.qa.admin.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginTest() {
		String actualTitle = loginPage.loginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
}