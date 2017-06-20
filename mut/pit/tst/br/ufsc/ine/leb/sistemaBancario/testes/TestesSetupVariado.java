package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesSetupVariado
{
	SistemaBancario sistemaBanc�rio;
	
	@Before
	public void criarSistemaBanc�rio()
	{
		sistemaBanc�rio = new SistemaBancario();
	}
	
	// Exerc�cio 1
	@Test
	public void bancoBancoDoBrasil()
	{
		// Configura��o
		
		// Exercita��o
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		
		// Verifica��o
		assertEquals("Nome", "Banco Do Brasil", bancoDoBrasil.obterNome());
		assertEquals("Moeda", Moeda.BRL, bancoDoBrasil.obterMoeda());
	}
	
	// Exerc�cio 2
	@Test
	public void ag�nciaCentro()
	{
		// Configura��o
		Banco bancoDoBrasil = criarBancoBancoDoBrasil();
		
		// Exercita��o
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		
		// Verifica��o
		assertEquals("001", centro.obterIdentificador());
		assertEquals("Centro", centro.obterNome());
		assertEquals(bancoDoBrasil, centro.obterBanco());
	}
	
	// Exerc�cio 3
	@Test
	public void contaMaria()
	{
		// Configura��o
		Agencia centro = criarAgenciaCentroBancoDoBrasil();
		ValorMonetario saldoEsperado = new ValorMonetario(Moeda.BRL);
		
		// Exercita��o
		Conta maria = centro.criarConta("Maria");
		
		// Verifica��o
		assertEquals("0001-5", maria.obterIdentificador());
		assertEquals("Maria", maria.obterTitular());
		assertEquals(saldoEsperado, maria.calcularSaldo());
		assertEquals(centro, maria.obterAgencia());
	}
	
	// Exerc�cio 4
	@Test
	public void dep�sito10Reais()
	{
		// Configura��o
		Conta maria = criarContaMariaCentroBancoDoBrasil();
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		
		// Exercita��o
		Operacao dep�sito = sistemaBanc�rio.depositar(maria, dezReais);
		
		// Verifica��o
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, dep�sito.obterEstado());
		assertEquals("Saldo", dezReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exerc�cio 5
	@Test
	public void saque6Reais()
	{
		// Configura��o
		Conta maria = criarContaMariaCentroBancoDoBrasilComSaldo(10);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		
		// Exercita��o
		Operacao saque = sistemaBanc�rio.sacar(maria, seisReais);
		
		// Verifica��o
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, saque.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exerc�cio 6
	@Test
	public void saqueSemSaldo()
	{
		// Configura��o
		Conta maria = criarContaMariaCentroBancoDoBrasilComSaldo(4);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		
		// Exercita��o
		Operacao saqueFalho = sistemaBanc�rio.sacar(maria, seisReais);
		
		// Verifica��o
		assertEquals("Estado", EstadosDeOperacao.SALDO_INSUFICIENTE,
				saqueFalho.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Utilidades
	private Banco criarBancoBancoDoBrasil()
	{
		return sistemaBanc�rio.criarBanco("Banco Do Brasil", Moeda.BRL);
	}
	
	private Agencia criarAgenciaCentroBancoDoBrasil()
	{
		Banco bancoDoBrasil = criarBancoBancoDoBrasil();
		return bancoDoBrasil.criarAgencia("Centro");
	}
	
	private Conta criarContaMariaCentroBancoDoBrasil()
	{
		Agencia centro = criarAgenciaCentroBancoDoBrasil();
		return centro.criarConta("Maria");
	}
	
	private Conta criarContaMariaCentroBancoDoBrasilComSaldo(int saldoInicial)
	{
		Agencia centro = criarAgenciaCentroBancoDoBrasil();
		Conta maria = centro.criarConta("Maria");
		Dinheiro d = new Dinheiro(Moeda.BRL, saldoInicial, 0);
		sistemaBanc�rio.depositar(maria, d);
		return maria;
	}
}