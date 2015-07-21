package br.com.qualister.cenario.exercicios;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.assertEquals;

public class Exercicio2 {

	@Test
	public void validaAcessoMenuAvancado() {
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Menu deslizante")).click();
		Actions acao = new Actions(WDS.get());
		// Movendo o mouse para Treinamentos Qualister
		acao.moveToElement(WDS.get().findElement(By.linkText("Treinamentos Qualister"))).build().perform(); // Navega ate o menu Treinamentos Qualister
		acao.moveToElement(WDS.get().findElement(By.partialLinkText("Automacao"))).build().perform(); // Navega ate o menu Automacao
		acao.click(WDS.get().findElement(By.partialLinkText("Avancado"))).build().perform(); // Clica no link Avancado do menu drop down
		// WDS.get().findElement(By.partialLinkText("Avancado")).click(); // Clica no link
		// Muda o contexto para o frame
		WDS.get().switchTo().frame("contentFrame");		
		assertEquals("CURSOS > AUTOMACAO DE TESTES FUNCIONAIS COM SELENIUM - AVANCADO", WDS.get().findElement(By.tagName("h3")).getText());				
	}
	
	@Test
	public void validaAcessoMenuFundamentos(){
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Menu deslizante")).click();
		Actions acao = new Actions(WDS.get());
		// Movendo o mouse para Treinamentos Qualister
		acao.moveToElement(WDS.get().findElement(By.linkText("Treinamentos Qualister"))).build().perform();
		acao.moveToElement(WDS.get().findElement(By.partialLinkText("Automacao"))).build().perform();
		acao.click(WDS.get().findElement(By.partialLinkText("Fundamentos"))).build().perform();
		WDS.get().switchTo().frame("contentFrame");		
		assertEquals("CURSOS > AUTOMACAO DE TESTES FUNCIONAIS COM SELENIUM - FUNDAMENTOS", WDS.get().findElement(By.tagName("h3")).getText());		
	}

}
