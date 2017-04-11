package br.ufsc.ine.leb.sistemaBancario.testes.hier;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesBanco
{
	private SistemaBancario sistema;
	private Banco bancoDoBrasil;
	
	@Before
	public void criarSistemaBancário()
	{
		sistema = new SistemaBancario();
		bancoDoBrasil = sistema.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
	}
	
	@Test
	public void bancoBancoDoBrasil()
	{
		assertEquals("Nome", "Banco Do Brasil", bancoDoBrasil.obterNome());
		assertEquals("Moeda", Moeda.BRL, bancoDoBrasil.obterMoeda());
	}
}
