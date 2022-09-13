package com.qa.admin.factory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Properties prop;
	Page page;
	
	private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> threadLocalBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();
	
	public static Playwright getPlaywright() {
		return threadLocalPlaywright.get();
	}
	
	public static Browser getBrowser() {
		return threadLocalBrowser.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return threadLocalBrowserContext.get();
	}
	
	public static Page getPage() {
		return threadLocalPage.get();
	}
	
	public Page initBrowser(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser selected is : " + browserName);
		
		//playwright = Playwright.create();
		threadLocalPlaywright.set(Playwright.create());
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
			
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
			
		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			threadLocalBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
			
		case "chrome":
			//browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			threadLocalBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
			
		default:
			System.out.println("Please pass the correct browser name..");
			break;
		}
		
		/* browserContext = browser.newContext();
		page = browserContext.newPage();
		page.navigate(prop.getProperty("url").trim());
		return page; */
		
		threadLocalBrowserContext.set(getBrowser().newContext());
		threadLocalPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		
		return getPage();
		
	}
	/**
	 * This method is used to initialize the properties from configuration file
	 */
	public Properties init_prop() {
		try {
			FileInputStream fip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(fip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir" + "/screenshot/" + System.currentTimeMillis() + ".png");
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path;
	}

}
