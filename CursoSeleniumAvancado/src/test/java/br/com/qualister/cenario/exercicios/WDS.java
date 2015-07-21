package br.com.qualister.cenario.exercicios;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// WebDriver Singleton
// Criou um Singleton para nao precisar sair trocando o driver em todos os meus testes
public class WDS {

	private static WebDriver driver = null;
	
	// Delay de 2000 milisegundos = 2 segundos
	// public static long DELAY = 2000;
	public static long DELAY = 0;
	
	public static WebDriver get(){
		if(driver == null){
			driver = new CustomFirefoxDriver();
			
			// Espera meio segundo se nao encontrou o elemento espera mais meio segundo, ai ja se passou 1s, se nao encontrou espera mais 
			// meio segundo ate completar os 10 segundos
			// A diferenca basica entre o Implicit Wait e o Explicit Wait
			// O Implicit Wait serve para o findElements de todo o sistema
			// O Explicit Wait serve para partes complexas especificas do sistema
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	
}
