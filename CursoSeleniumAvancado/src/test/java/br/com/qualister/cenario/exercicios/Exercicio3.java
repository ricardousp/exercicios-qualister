package br.com.qualister.cenario.exercicios;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.qualister.core.WDSAntiga;


public class Exercicio3 {
	@Test
	public void test(){
		WDSAntiga.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDSAntiga.get().findElement(By.linkText("Auto Completar")).click();
		WDSAntiga.get().switchTo().frame("paginas");	
		WDSAntiga.get().findElement(By.id("estado_autocomplete")).sendKeys("Rio");
		
		//WebDriverWait wait = new WebDriverWait(WDS.get(), 10);
		WDSAntiga.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Grande do Sul')]")));
		
		WDSAntiga.get().findElement(By.xpath("//li[contains(text(),'Grande do Sul')]")).click();
		WDSAntiga.get().findElement(By.id("cidade_autocomplete")).sendKeys("Porto");
		WDSAntiga.get().findElement(By.xpath("//li[contains(text(),'Alegre')]")).click();
		WDSAntiga.get().findElement(By.id("proximo")).click();
		
		//WDS.get().findElement(By.xpath("//li[contains(test(), 'Grande do Sul')]")).sendKeys("Rio");
		
		//WDS.get().findElement(By.id("cidade_autocomplete")).sendKeys("Porto");
	}
}






