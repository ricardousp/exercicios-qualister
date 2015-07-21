package br.com.qualister.cenario.exercicios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exercicio5 {

	@Test
	public void test() {
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/lista/actions_autocomplete/");
		WDS.get().switchTo().frame("paginas");
		WDS.get().findElement(By.id("proximo")).click();
		WDS.get().findElement(By.id("cep")).sendKeys("13566500");
		((JavascriptExecutor) WDS.get()).executeScript("getEndereco();");
		WebDriverWait wait = new WebDriverWait(WDS.get(), 15);
//		wait.until(new ExpectedCondition<Boolean>() {
//			
//			@Override
//			public Boolean apply(WebDriver driver){
//				return "".equals(WDS.get().findElement(By.id("rua")).getText());
//			}
//			
//		});
		assertEquals("Quadra: QRSW 02 Bloco B-15", WDS.get().findElement(By.id("rua")).getText());
		
	}

}
