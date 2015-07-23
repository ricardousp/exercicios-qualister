package br.com.qualister.cenario.inicio;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTeste {

	@Test
	public void meuPrimeiroTeste() {
		assertEquals("O resultado dve possuis apenas 1 registro",1,1);	
	}
	
	@Test
	public void meuSegundoTeste() {
		String fruta = "Pera";
		
		assertTrue("Fruta esta errada",fruta.equals("Pera"));
	}
	
	@Test
	public void meuTerceiroTeste() {
		String fruta = "pera";
		assertEquals("Esperava-se uma pera",fruta,"Pera");
	}
	@Test
	public void meuQuartoTeste() {
		assertTrue(2+2==4);
	}
	@Test
	public void meuQuintoTeste() {
		assertTrue("Espera-se que 2+2 seja igual a 4!",2+2==7);
	}
}
