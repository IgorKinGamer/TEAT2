package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesSetupVariado
{
	SistemaBancario sistemaBancário;
	
	@Before
	public void criarSistemaBancário()
	{
		sistemaBancário = new SistemaBancario();
	}
	
	// Exercício 1
	@Test
	public void bancoBancoDoBrasil()
	{
		// Configuração
		
		// Exercitação
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		
		// Verificação
		assertEquals("Nome", "Banco Do Brasil", bancoDoBrasil.obterNome());
		assertEquals("Moeda", Moeda.BRL, bancoDoBrasil.obterMoeda());
	}
	
	// Exercício 2
	@Test
	public void agênciaCentro()
	{
		// Configuração
		Banco bancoDoBrasil = criarBancoBancoDoBrasil();
		
		// Exercitação
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		
		// Verificação
		assertEquals("001", centro.obterIdentificador());
		assertEquals("Centro", centro.obterNome());
		assertEquals(bancoDoBrasil, centro.obterBanco());
	}
	
	// Exercício 3
	@Test
	public void contaMaria()
	{
		// Configuração
		Agencia centro = criarAgenciaCentroBancoDoBrasil();
		ValorMonetario saldoEsperado = new ValorMonetario(Moeda.BRL);
		
		// Exercitação
		Conta maria = centro.criarConta("Maria");
		
		// Verificação
		assertEquals("0001-5", maria.obterIdentificador());
		assertEquals("Maria", maria.obterTitular());
		assertEquals(saldoEsperado, maria.calcularSaldo());
		assertEquals(centro, maria.obterAgencia());
	}
	
	// Exercício 4
	@Test
	public void depósito10Reais()
	{
		// Configuração
		Conta maria = criarContaMariaCentroBancoDoBrasil();
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		
		// Exercitação
		Operacao depósito = sistemaBancário.depositar(maria, dezReais);
		
		// Verificação
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, depósito.obterEstado());
		assertEquals("Saldo", dezReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exercício 5
	@Test
	public void saque6Reais()
	{
		// Configuração
		Conta maria = criarContaMariaCentroBancoDoBrasilComSaldo(10);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		
		// Exercitação
		Operacao saque = sistemaBancário.sacar(maria, seisReais);
		
		// Verificação
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, saque.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exercício 6
	@Test
	public void saqueSemSaldo()
	{
		// Configuração
		Conta maria = criarContaMariaCentroBancoDoBrasilComSaldo(4);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		
		// Exercitação
		Operacao saqueFalho = sistemaBancário.sacar(maria, seisReais);
		
		// Verificação
		assertEquals("Estado", EstadosDeOperacao.SALDO_INSUFICIENTE,
				saqueFalho.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Utilidades
	private Banco criarBancoBancoDoBrasil()
	{
		return sistemaBancário.criarBanco("Banco Do Brasil", Moeda.BRL);
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
		sistemaBancário.depositar(maria, d);
		return maria;
	}
}
