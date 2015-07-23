package br.com.qualister.cenario.inicio;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTeste2 {
	

	@Test
	public void meuPrimeiroTeste() {
		
		assertEquals(1,1);
	}

	@Test
	public void meuSegundoTeste() {
		String fruta = "Pera";
		assertEquals("Esperava -se uam Pera", fruta, "pera");
	}
	
	@Test
	public void meuTerceiroTeste() {
				assertTrue(2+2==4);
	}
	
	@Test
	public void meuQuartoTeste() {
				assertTrue("Falha catastr�fica. Este sistema se auto - destruir� em 5 segundos", 2+3 ==4);
	}

}
