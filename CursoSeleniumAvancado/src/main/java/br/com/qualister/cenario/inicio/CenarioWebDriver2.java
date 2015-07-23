package br.com.qualister.cenario.inicio;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.ExceptionConstants;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CenarioWebDriver2{
// economia de codigo	
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	@Before
	public void iniciar(){
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		
	}
	
	@After
	public void finalizar(){
		driver.quit();
	}
	

	@Test
	public void testeWebDriver() {
		//driver.get("http://seleniumhq.org");
		driver.navigate().to("http://seleniumhq.org");
		driver.navigate().to("http://www.google.com");
		driver.navigate().back();
		driver.navigate().forward();
	}


@Test
//navegacao na pagina
public void testeWebDriver2() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/navegacao/");
	driver.navigate().to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/navegacao/contador.html");
	driver.navigate().refresh();
	driver.navigate().back();
	driver.navigate().forward();
	driver.quit();
}

@Test
//Localizar elemento
public void testeWebDriver3() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos/");
	driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys("dricarozetti@gmail.com");
	driver.findElement(By.id("password")).sendKeys("dri00000");
	
	
}

@Test
//localizar elemento atravez do webelement
public void testeWebDriver4() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos");
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos/");
	WebElement login = driver.findElement(By.id("email"));
	WebElement senha = driver.findElement(By.id("password"));
	WebElement lembrarsenha = driver.findElement(By.id("lembrar"));
	WebElement entrar = driver.findElement(By.id("submit"));
	WebElement limpar = driver.findElement(By.id("limpar"));
	WebElement titulo = driver.findElement(By.tagName("h5"));
	login.clear();
	login.sendKeys("dricarozetti@gmail.com");
	senha.sendKeys("dri00000");
	entrar.click();
}

@Test
//localizar por css avan�ado (nao e dinamico)
public void testeWebDriver5() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/find_elements/");
	driver.findElement(By.cssSelector(".divpai"));
	driver.findElement(By.cssSelector(".divfilho"));
	driver.findElement(By.cssSelector(".divneto"));
	driver.findElements(By.cssSelector(".divneto")).get(1);
	driver.findElement(By.cssSelector("#dataInicio"));

}

@Test
//localizar por css avan�ado (din�mico)
public void testeWebDriver6() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/css_xpath/");
	driver.findElement(By.cssSelector("input[id^='name']"));
	driver.findElement(By.cssSelector("input[id$='password']"));
	driver.findElement(By.cssSelector("input[id*='password']"));
	}

@Test
//localizar por Xpath (din�mico)
public void testeWebDriver7() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/find_elements");
	driver.findElement(By.xpath("//input[@class='divpai']"));
	driver.findElement(By.xpath("//div[@class='divfilho border']div[2]"));
	driver.findElement(By.xpath("//input[@class='divneto']"));
	driver.findElement(By.xpath("//input[@class='divneto']"));
	driver.findElement(By.cssSelector("#dataInicio"));
}

@Test
//localizar por Xpath avan�ado (din�mico)
public void testeWebDriver8() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/css_xpath/");
	driver.findElement(By.xpath("//input[starts-with(@id,'name')]")).sendKeys("dricarozetti@gmail.com");
	driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("123456");
	driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("123456");
	//driver.findElement(By.id(("send")
}


@Test
//prencher campos da tela, submeter e validar resultado
public void testeWebDriver9() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos_html/");
	driver.findElement(By.id("name1")).sendKeys("Adriana");
	driver.findElement(By.id("name2")).sendKeys("Rozetti");
	driver.findElements(By.name("gender")).get(1).click();
	driver.findElement(By.id("value1")).click();
	driver.findElement(By.id("txtarea")).sendKeys("Mexicana");
	
	//selecionar elemento no combo
	WebElement elementoNivelInstrucao = driver.findElement(By.name("education"));
	Select selecaoNivelInstrucao = new Select (elementoNivelInstrucao);
	selecaoNivelInstrucao.selectByVisibleText("Pós-Graduação / MBA");
	
	//selecionar elemento na caixa sele��o
		WebElement elementoPeriodo = driver.findElement(By.name("melhorpartedia[]"));
		Select selecaoPeriodo = new Select (elementoPeriodo);
		selecaoPeriodo.selectByValue("Noite");
	
	//ir para outra pagina ap�s o envio de dados
	driver.findElement(By.xpath("//input[@value='Enviar dados']")).click();
	
	// validar as informa��es inseridas
	assertTrue(driver.getPageSource().contains("P�s-Gradua��o / MBA"));
		
}

@Test
//wait com ajax (Explicita)
public void testeWebDriver10() throws IOException {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/ajax_loading/");
	driver.findElement(By.name("username")).sendKeys("demo");
	driver.findElement(By.name("password")).sendKeys("demo");
	driver.findElement(By.id("login")).click();
	//assertTrue(driver.getPageSource().contains("Voce efetuou o login com sucesso!"));
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("message"))));
	File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(arquivo,  new File("evidencias//01.jpg"));
	assertEquals("Voce efetuou o login com sucesso!", driver.findElement(By.id("message")).getText());
	
}

@Test
//wait com ajax (Explicita)
public void testeWebDriver11() {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/ajax_show/");
	driver.findElement(By.name("nome_aluno")).sendKeys("Maria");
	driver.findElement(By.name("email_aluno")).sendKeys("maria@maria.com");
	WebElement idade = driver.findElement(By.name("idade_aluno"));
	Select selecaoidade = new Select (idade);
	selecaoidade.selectByVisibleText("Menor que 13 anos");
	//quando tiver campos que carregam apos a sele��o de um combo usar a senten�a abaixo, o campo passado dever� ser ultimo exibido assuim garante q todos os campos anterios foram exibidos.
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("email_pais"))));
	driver.findElement(By.name("nome_pais")).sendKeys("Joao");
	driver.findElement(By.name("email_pais")).sendKeys("pais@pais.com");
	driver.findElement(By.linkText("Voltar para a lista de exerc�cios")).click();
	

}	

@Test
//alertas de tela
public void testeWebDriver12() throws InterruptedException {
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/alert_confirmation/inc.auth.php");
	driver.findElement(By.id("user")).sendKeys("Adriana");
	driver.findElement(By.id("submit")).click();
	assertEquals("Preencha os dois campos!!!", driver.switchTo().alert().getText());
	Thread.sleep(5000);
	driver.switchTo().alert().accept();//Para o bot�o Ok do alerta.. se fosse cancelar seria dismiss
	driver.findElement(By.id("password")).sendKeys("abc");
	driver.findElement(By.id("submit")).click();
	//informe senha
	//clique em login novamente
	
}	

@Test
//frames html - localiza o elemento, clica seta para a esqueda, ele vai fechando ate chegar no FRAME esse � o que precisamos
public void testeWebDriver13(){
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/frames/");
	driver.switchTo().frame("content");
	driver.findElement(By.linkText("SeleniumHQ")).click();
	assertEquals("Selenium - Web Browser Automation", driver.getTitle());
	driver.navigate().back();
	driver.switchTo().frame("menu");
	driver.findElement(By.linkText("P�gina Verde")).click();
}


@Test
//frames html - localiza o elemento, clica seta para a esqueda, ele vai fechando ate chegar no FRAME esse � o que precisamos
public void testeWebDriver14(){
	driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/frames/");
	driver.switchTo().frame("footer");
	driver.findElement(By.linkText("P�gina branca")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("content");
	assertEquals("P�gina branca", driver.findElement(By.tagName("h1")).getText());
	driver.findElement(By.linkText("Voltar para a p�gina origial")).click();
	driver.switchTo().defaultContent();// volta para a pagina inicial
	driver.switchTo().frame("content");//acha o frame ao qual o campo pertence
	assertEquals("Conteudo", driver.findElement(By.tagName("h1")).getText());

}
}



