package br.ufsc.ine.leb.sistemaBancario.testes.hier;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup(TestesAgencia.class)
public class TestesConta
{
	@Fixture
	private SistemaBancario sistema;
	@Fixture
	private Agencia centro;
	
	private Conta maria;
	
	@Before
	public void configurar()
	{
		maria = centro.criarConta("Maria");
	}
	
	@Test
	public void contaMaria()
	{
		ValorMonetario saldoEsperado = new ValorMonetario(Moeda.BRL);
		
		assertEquals("0001-5", maria.obterIdentificador());
		assertEquals("Maria", maria.obterTitular());
		assertEquals(saldoEsperado, maria.calcularSaldo());
		assertEquals(centro, maria.obterAgencia());
	}
}
