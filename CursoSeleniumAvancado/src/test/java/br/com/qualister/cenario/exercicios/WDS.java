package br.com.qualister.cenario.exercicios;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

// WebDriver Singleton
// Criou um Singleton para não precisar sair trocando o driver em todos os meus testes
public class WDS {

	private static WebDriver driver = null;

	private static WebDriverWait wait = null;

	public static long DELAY = 0;

	public static String PATH_EVIDENCIAS = "";

	public static int TIMEOUT_EXPLICIT_WAIT = 15;

	// private static String URL_GRID = "http://10.10.63.5:4444/wd/hub";
	private static String URL_GRID = "http://ricardoramos:11ab0a1b-59f9-46f5-ab0c-7be35b42d2a5@ondemand.saucelabs.com:80/wd/hub";

	// private static WebDriver driver = null;

	// Delay de 2000 milisegundos = 2 segundos
	// public static long DELAY = 2000;
	// public static long DELAY = 0;

	public static WebDriver get() {
		if (driver == null) {
			// PhantomJS
			// DesiredCapabilities dc = new DesiredCapabilities();
			// dc.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
			// "C:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
			// driver = new PhantomJSDriver(dc);

			// HtmlUnitDriver é um navegador headless padrão do WebDriver
			// driver = new HtmlUnitDriver(true);

			/*
			 * Espera meio segundo se não encontrou o elemento espera mais meio
			 * segundo, aí já se passou 1s, se não encontrou espera mais meio
			 * segundo até completar os 10 segundos A diferença básica entre o
			 * Implicit Wait e o Explicit Wait O Implicit Wait serve para o
			 * findElements de todo o sistema O Explicit Wait serve para partes
			 * complexas especificas do sistema
			 */

			try {
				DesiredCapabilities capabillities = DesiredCapabilities.firefox();
				capabillities.setCapability("takesScreenshot", true);
				capabillities.setCapability("version", "38.0");
				capabillities.setCapability("platform", Platform.MAC);
				driver = new RemoteWebDriver(new URL(URL_GRID), capabillities);

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
