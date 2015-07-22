package br.com.qualister.cenario.exercicios;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import br.com.qualister.core.CustomRunner;

@RunWith(CustomRunner.class)
public class Exercicio1 {

	@Test
	public void ctAlerta() {
		// O metodo driver.navigate().to() eh a mesma coisa do metodo get()
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Teclas de atalho")).click();
		WDS.get().findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		/* 
		 * SwitchTo - Muda de contexto do corpo da pegina para caixa de mensagem
		 * do alerta (Pode mudar para uma janela de popup)
		 * Fala para o Selenium deixar de enxergar o corpo (body) para enxergar
		 * a caixa de mensagem do alerta
		 */
		Alert alerta = WDS.get().switchTo().alert();
		assertEquals("O alerta apareceu!", alerta.getText());
		alerta.accept(); // Clica no botao de OK

		// driver.switchTo().alert().accept(); - Clica no botao de OK
		// driver.switchTo().alert().dismiss(); - Clica no botao Cancelar	
	}

	@Test
	public void ctConfirmacao() {
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Teclas de atalho")).click();
		WDS.get().findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
		Alert alerta = WDS.get().switchTo().alert();
		assertEquals("Esta é a confirmação!", alerta.getText());
		alerta.dismiss();	
	}
	
	@Test
	public void ctPrompt() {
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Teclas de atalho")).click();	
		WDS.get().findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.ALT, "p"));
		Alert alerta = WDS.get().switchTo().alert();
		assertEquals("Digite seu nome:", alerta.getText());
		WDS.get().switchTo().alert().sendKeys("Ricardo");
		WDS.get().switchTo().alert().accept();
	}
	
	@Test
	//@PreCondicao(nome="ctPrompt") - Se ctPrompt nao foi executado executa ele, caso contrario nao precisa executar de novo
	public void ctJanela() {
		WDS.get().navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/avancado/");
		WDS.get().findElement(By.linkText("Teclas de atalho")).click();
		WDS.get().findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.ALT, "j"));
		String[] handles = new String[5];
		WDS.get().getWindowHandles().toArray(handles);
		String handleElegivel = "";
		String handlePadrao = WDS.get().getWindowHandle();
		for (String handle : handles) {
			if (handle != null) {
				handleElegivel = handle;
			}
		}
		WDS.get().switchTo().window(handleElegivel);
		WDS.get().close();
		WDS.get().switchTo().window(handlePadrao);
	}

	// @After
	// public void tearDown(){
	// driver.close();
	// }

}
