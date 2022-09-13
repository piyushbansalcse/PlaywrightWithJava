package com.qa.admin.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	Page page;
	
	//1.String locators
	private String username = "input[name='login']";
	private String password = "#username";
	private String loginBttn = "span:text('Sign in')";
	
	//2.Page constructor
	public LoginPage(Page page) {
		this.page = page;
	}
	
	//3.Page actions/methods
	public String loginPageTitle() {
		String title = page.title();
		System.out.println("login page title :" + title );
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = page.url();
		System.out.println("login page url :" + url);
		return url;
	}
	
	//4.Page chaining concept 
	public HomePage naviateHomePage() {
		page.click(loginBttn);
		return new HomePage(page);
	}

}
