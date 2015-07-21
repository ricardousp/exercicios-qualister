package br.com.qualister.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class WDS {

	private static WebDriver driver = null;
	
	public static long DELAY = 0; 

	public static WebDriver get() {
		if (driver == null) {
			driver = new CustomFirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		return driver;
	}

}
