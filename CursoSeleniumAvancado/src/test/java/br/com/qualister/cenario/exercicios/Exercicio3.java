package br.com.qualister.cenario.exercicios;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Exercicio3 {
	@Test
	public void test(){
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Auto Completar")).click();
		WDS.get().switchTo().frame("paginas");	
		WDS.get().findElement(By.id("estado_autocomplete")).sendKeys("Rio");
		
		//WebDriverWait wait = new WebDriverWait(WDS.get(), 10);
		WDS.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Grande do Sul')]")));
		
		WDS.get().findElement(By.xpath("//li[contains(text(),'Grande do Sul')]")).click();
		WDS.get().findElement(By.id("cidade_autocomplete")).sendKeys("Porto");
		WDS.get().findElement(By.xpath("//li[contains(text(),'Alegre')]")).click();
		WDS.get().findElement(By.id("proximo")).click();
		
		//WDS.get().findElement(By.xpath("//li[contains(test(), 'Grande do Sul')]")).sendKeys("Rio");
		
		//WDS.get().findElement(By.id("cidade_autocomplete")).sendKeys("Porto");
	}
}






