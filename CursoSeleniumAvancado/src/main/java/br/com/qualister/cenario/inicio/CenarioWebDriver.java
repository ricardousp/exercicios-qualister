package br.com.qualister.cenario.inicio;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CenarioWebDriver {
	// economia de codigo	
	WebDriver driver = null;
	WebDriverWait wait = null;

	@Before
	public void iniciar() {

		// driver = new HtmlUnitDriver();

		System.setProperty("webdriver.chrome.driver",
				"drivers/chromedriver.exe");
		driver = new ChromeDriver();

		// driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}

	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void testeWebDriver() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/navegacao/");
		//navegacao na pagina
		// driver.navigate().to("http://seleniumhq.org");
		// driver.navigate().to("http://www.google.com");
		// driver.navigate().back();//voltar uma pagina
		// driver.navigate().forward();//ir uma pagina para frente
		//
		// driver.navigate().refresh();
		// driver.navigate().back();
		// driver.navigate().forward();
		driver.navigate()
				.to("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/navegacao/contador.html");
		driver.navigate().to("http://google.com");
		assertEquals("Google", driver.getTitle());
	}

	//Localizar elemento
	@Test
	public void testeLocalizarElemento() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos/");

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email"))
				.sendKeys("dieny.sttefanye@gmail.com");
	}

	//localizar elemento atravez do webelement
	@Test
	public void exercicioLocalizarElemento() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos/");

		WebElement tituloExercicio = driver.findElement(By.tagName("h5"));
		WebElement campoEmail = driver.findElement(By.id("email"));
		WebElement campoSenha = driver.findElement(By.id("password"));
		WebElement checkboxLembrar = driver.findElement(By.id("lembrar"));
		WebElement botaoLogin = driver.findElement(By.id("submit"));
		WebElement botaoLimpar = driver.findElement(By.id("limpar"));
		WebElement linkVoltar = driver.findElement(By
				.linkText("Voltar para a lista de exerc�cios"));
	}

	//localizar por css avançado (nao e dinamico)
	@Test
	public void exercicioCSS() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/find_elements/");
		// for (int i = 0; i< 1000; i++) {
		driver.findElement(By.cssSelector(".divpai"));
		driver.findElement(By.cssSelector(".divpai > .divfilho"));
		driver.findElement(By.cssSelector(".divpai > .divfilho > .divneto"));
		driver.findElements(By.cssSelector(".divneto")).get(1);
		driver.findElement(By.cssSelector("input"));
		driver.findElement(By.cssSelector("#dataInicio"));
		// }
	}

	//localizar por css avançado (dinâmico)
	@Test
	public void exercicioCSSAvancado() {
		// try {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/css_xpath/");
		driver.findElement(By.cssSelector("input[name^='name']"));
		driver.findElement(By.cssSelector("input[id$='password']"));
		driver.findElement(By.cssSelector("input[id*='password-']"));
		// }
		// finally {
		// driver.quit();
		// }

	}

	//localizar por Xpath (dinâmico)
	@Test
	public void exercicioXpath() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/find_elements/");
		driver.findElement(By.xpath("//div[@class='divpai border']"));
		driver.findElement(By.xpath("//div[@class='divfilho border']"));
		driver.findElement(By.xpath("//div[@class='divfilho border']/div[2]"));
		// ou
		driver.findElement(By.xpath("//div[@class='divneto border']"));
		driver.findElement(By.xpath("//input[@id='dataInicio']"));
	}

	//localizar por Xpath avançado (dinâmico)
	@Test
	public void exercicioXpathAvancado() throws IOException {
		try{driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/css_xpath/");
		driver.findElement(By.xpath("//input[starts-with(@id,'name')]"));
		driver.findElement(By.xpath("//input[contains(@id,'-password')]"));
		//Screenshots
		File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("evidencias//01.jpg"));
		
		driver.findElement(By.xpath("//input[contains(@id,'-password-')]"));}
	    catch(Exception e){
	    	File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File("evidencias//02.jpg"));
	    }
	    }

	//prencher campos da tela, submeter e validar resultado
	@Test
	public void testeManipularElemento() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/css_xpath/");
		driver.findElement(By.xpath("//input[starts-with(@id,'name')]"))
				.sendKeys("dieny.sttefanye@gamil.com");
		;
		driver.findElement(By.xpath("//input[contains(@id,'-password')]"))
				.sendKeys("123456");
		;
		driver.findElement(By.xpath("//input[contains(@id,'-password-')]"))
				.sendKeys("123456");
		driver.findElement(By.id("send")).click();

		// assertEquals("Resultado",driver.findElement(By.tagName("h1")));
		// assertEquals("Resultado",driver.findElement(By.cssSelector(".center")));
		WebElement tituloTela = driver.findElement(By.cssSelector(".center"));
		assertEquals("Resultado", tituloTela.getText());
	}

	@Test
	public void testeManipularElementoHTML() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/elementos_html/");
		WebElement campoNome = driver.findElement(By.id("name1"));
		campoNome.sendKeys("Dieny");
		WebElement campoSobrenome = driver.findElement(By.name("lname"));
		campoSobrenome.sendKeys("Oliveira");
		WebElement radioSexo = driver.findElements(By.name("gender")).get(1);
		radioSexo.click();
		WebElement radioComida = driver.findElement(By
				.cssSelector("input[value='Pizza']"));
		radioComida.click();
		// WebElement campoOutraComida = driver.findElement(By.name("area"));
		WebElement comboNivelInstrucao = driver.findElement(By
				.name("education"));
		Select selecaoNivelInstrucao = new Select(comboNivelInstrucao);
		selecaoNivelInstrucao.selectByVisibleText("Graduado");
		selecaoNivelInstrucao.getFirstSelectedOption().getText();

		WebElement comboDia = driver.findElement(By.id("dia"));
		Select selecaoDia = new Select(comboDia);
		selecaoDia.selectByVisibleText("Noite");

		WebElement botaoEnviar = driver.findElement(By.name("send"));
		botaoEnviar.click();
		// assertEquals("Graduado",driver.getPageSource());
		Assert.assertTrue(driver.getPageSource().contains("Dieny Oliveira"));
		Assert.assertTrue(driver.getPageSource().contains("Graduado"));
		Assert.assertTrue(driver.getPageSource().contains("Mulher"));
		Assert.assertTrue(driver.getPageSource().contains("Pizza"));
		Assert.assertTrue(driver.getPageSource().contains("Noite"));
	}

	@Test
	public void testeEsperandoElementoUm() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/ajax_loading/");
		driver.findElement(By.id("username")).sendKeys("demo");
		driver.findElement(By.id("password")).sendKeys("demo");
		driver.findElement(By.id("login")).click();
		// Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
				.id("message"))));
		// ou
		Assert.assertEquals("Voce efetuou o login com sucesso!", driver
				.findElement(By.id("message")).getText());
	}

	@Test
	public void testeEsperandoElementoDois() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/ajax_show/");
		driver.findElement(By.name("nome_aluno")).sendKeys("Dieny Oliveira");
		driver.findElement(By.name("email_aluno")).sendKeys("dieny@gmail.com");
		Select comboIdade = new Select(driver.findElement(By.id("idade")));
		comboIdade.selectByVisibleText("Menor que 13 anos");
		driver.findElement(By.name("nome_pais")).sendKeys("Selma e Moises");
		driver.findElement(By.name("email_pais")).sendKeys("selma@gmail.com");
	}

	@Test
	public void testeEsperandoEC() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/ajax_show/");
		driver.findElement(By.name("nome_aluno")).sendKeys("Dieny Oliveira");
		driver.findElement(By.name("email_aluno")).sendKeys("dieny@gmail.com");
		Select comboIdade = new Select(driver.findElement(By.id("idade")));
		comboIdade.selectByVisibleText("Menor que 13 anos");

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("email")).getAttribute("style")
						.equals("display: block;");
			}
		});
		driver.findElement(By.name("nome_pais")).sendKeys("Selma e Moises");
		driver.findElement(By.name("email_pais")).sendKeys("selma@gmail.com");
	}

	@Test
	public void testeAlertaUm() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/alert_confirmation/inc.auth.php");
		driver.findElement(By.id("user")).sendKeys("xxx");
		driver.findElement(By.id("submit")).click();
		Assert.assertEquals("Preencha os dois campos!!!", driver.switchTo()
				.alert().getText());
		driver.switchTo().alert().accept();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("submit")).click();
	}

	@Test
	public void testeFrame() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/frames/");
		driver.switchTo().frame("content");
		driver.findElement(By.linkText("SeleniumHQ")).click();
		Assert.assertEquals("Selenium - Web Browser Automation",
				driver.getTitle());
		driver.navigate().back();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("menu");
		driver.findElement(By.linkText("P�gina Verde"));
	}

	@Test
	public void testeFrameHard() {
		driver.get("http://qualister.info/treinamentos/selenium/webdriver/exercicios/fundamentos/lista/frames/");
		driver.switchTo().frame("footer");
		driver.findElement(By.linkText("P�gina branca")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("content");
		Assert.assertEquals("P�gina branca",
				driver.findElement(By.tagName("h1")).getText());
		driver.findElement(By.linkText("Voltar para a p�gina origial")).click();
		Assert.assertEquals("Conteudo", driver.findElement(By.tagName("h1"))
				.getText());
	}

}
