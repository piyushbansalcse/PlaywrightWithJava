package com.qa.admin.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	//1.String locators
	
	//2.Page constructor
	public HomePage(Page page) {
		this.page = page;
	}
	
	//3.Page actions/methods
	public String HomePageTitle() {
		String title = page.title();
		System.out.println("home page title :" + title );
		return title;
	}
	
	public String getHomePageUrl() {
		String url = page.url();
		System.out.println("home page url :" + url);
		return url;
	}
	
	//4.Page chaining

}
