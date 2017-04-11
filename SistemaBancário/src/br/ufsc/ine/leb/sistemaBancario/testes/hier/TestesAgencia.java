package br.ufsc.ine.leb.sistemaBancario.testes.hier;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup(TestesBanco.class)
public class TestesAgencia
{
	@Fixture
	private SistemaBancario sistema;
	@Fixture
	private Banco bancoDoBrasil;
	
	private Agencia centro;
	
	@Before
	public void configurar()
	{
		centro = bancoDoBrasil.criarAgencia("Centro");
	}
	
	@Test
	public void agênciaCentro()
	{
		assertEquals("001", centro.obterIdentificador());
		assertEquals("Centro", centro.obterNome());
		assertEquals(bancoDoBrasil, centro.obterBanco());
	}
}
