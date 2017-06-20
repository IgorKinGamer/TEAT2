package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesSetupInline
{
	// Exerc�cio 1
	@Test
	public void bancoBancoDoBrasil()
	{
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		
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
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		
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
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
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
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
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
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		sistemaBanc�rio.depositar(maria, dezReais);
		
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
		// Configura��o inline
		SistemaBancario sistemaBanc�rio = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBanc�rio.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		sistemaBanc�rio.depositar(maria, quatroReais);
		
		// Exercita��o
		Operacao saqueFalho = sistemaBanc�rio.sacar(maria, seisReais);
		
		// Verifica��o
		assertEquals("Estado", EstadosDeOperacao.SALDO_INSUFICIENTE,
				saqueFalho.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
}