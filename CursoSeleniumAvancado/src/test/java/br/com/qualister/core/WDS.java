package br.com.qualister.core;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDS {

	private static WebDriver driver = null;

	private static WebDriverWait wait = null;

	public static long DELAY = 0;

	public static String PATH_EVIDENCIAS = "";

	public static int TIMEOUT_EXPLICIT_WAIT = 15;
	
	//private static String URL_GRID = "http://10.10.63.5:4444/wd/hub";

	private static String URL_GRID = "http://roberto_ungarelli:be5098a7-8c57-496e-924e-6528a5c6f319@ondemand.saucelabs.com:80/wd/hub";
	
	public static WebDriver get() {
		if (driver == null) {
//			dc.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\phantomjs\\bin\\phantomjs.exe");
//			dc.setCapability("takesScreenshot", true);
			try {
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				driver = new RemoteWebDriver(new URL(URL_GRID),dc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void finalizar() {
		// if (driver != null) {
		// while(getNumeroJanelas()>0){
		// WDS.get().switchTo().window(getUltimaJanela());
		// driver.close();
		// }
		// }
		if (driver != null) {
			// Encerra todas as janelas
			driver.quit();
		}
	}

	private static int getNumeroJanelas() {
		int contador = 0;
		String[] handles = new String[20];
		try {
			WDS.get().getWindowHandles().toArray(handles);
			for (String handle : handles) {
				if (handle != null) {
					contador++;
				}
			}
		} catch (UnreachableBrowserException e) {
		}
		return contador;
	}

	private static String getUltimaJanela() {
		String[] handles = new String[20];
		WDS.get().getWindowHandles().toArray(handles);
		int numeroJanelas = getNumeroJanelas();
		if (numeroJanelas > 0) {
			return handles[numeroJanelas - 1];
		} else {
			return null;
		}
	}

	public static WebDriverWait getWait() {
		if (wait == null) {
			wait = new WebDriverWait(get(), TIMEOUT_EXPLICIT_WAIT);
		}
		return wait;
	}

}