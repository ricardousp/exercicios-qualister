package br.com.qualister.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CustomFirefoxDriver extends FirefoxDriver {

	public WebElement findElement(By by) {
		try {
			Thread.sleep(WDSAntiga.DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return super.findElement(by);
	}
}
