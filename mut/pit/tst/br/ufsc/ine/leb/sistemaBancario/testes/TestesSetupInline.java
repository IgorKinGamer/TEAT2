package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesSetupInline
{
	// Exercício 1
	@Test
	public void bancoBancoDoBrasil()
	{
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		
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
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		
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
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
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
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
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
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		sistemaBancário.depositar(maria, dezReais);
		
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
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		Conta maria = centro.criarConta("Maria");
		
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		sistemaBancário.depositar(maria, quatroReais);
		
		// Exercitação
		Operacao saqueFalho = sistemaBancário.sacar(maria, seisReais);
		
		// Verificação
		assertEquals("Estado", EstadosDeOperacao.SALDO_INSUFICIENTE,
				saqueFalho.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
}
