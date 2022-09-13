package com.qa.admin.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.admin.factory.PlaywrightFactory;
import com.qa.admin.pages.HomePage;
import com.qa.admin.pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory factory;
	protected LoginPage loginPage;
	protected HomePage homepage;
	protected Properties prop;
	Page page;
	
	@BeforeTest
	public void setup() {
		factory =  new PlaywrightFactory();
		prop = factory.init_prop();
		page = factory.initBrowser(prop);
		loginPage = new LoginPage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
