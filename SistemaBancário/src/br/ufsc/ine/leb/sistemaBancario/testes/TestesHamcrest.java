package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TestesHamcrest
{
	/////// EXERCÍCIO 3: Matcher para conta ///////
	
	@Test
	public void combinarConta()
	{
		// Configuração inline
		SistemaBancario sistemaBancário = new SistemaBancario();
		Banco bancoDoBrasil = sistemaBancário.criarBanco(
				"Banco Do Brasil", Moeda.BRL);
		Agencia centro = bancoDoBrasil.criarAgencia("Centro");
		
		// Exercitação
		Conta maria = centro.criarConta("Maria");
		
		// Verificação
		assertThat(maria, new EhConta("0001-5", "Maria", "Banco Do Brasil", "Centro"));
	}
	
	/////// EXERCÍCIO 2: Reescrever testes com matchers do Hamcrest ///////
	
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
		assertThat(bancoDoBrasil.obterNome(), equalTo("Banco Do Brasil"));
		assertThat(bancoDoBrasil.obterMoeda(), equalTo(Moeda.BRL));
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
		assertThat(centro.obterIdentificador(), equalTo("001"));
		assertThat(centro.obterNome(), equalTo("Centro"));
		assertThat(centro.obterBanco(), equalTo(bancoDoBrasil));
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
		assertThat(maria.obterIdentificador(), equalTo("0001-5"));
		assertThat(maria.obterTitular(), equalTo("Maria"));
		assertThat(maria.calcularSaldo(), equalTo(saldoEsperado));
		assertThat(maria.obterAgencia(), equalTo(centro));
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
		assertThat("Estado", depósito.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat("Saldo", maria.calcularSaldo().obterQuantia(), equalTo(dezReais));
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
		assertThat("Estado", saque.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat("Saldo", maria.calcularSaldo().obterQuantia(), equalTo(quatroReais));
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
		assertThat("Estado", saqueFalho.obterEstado(),
				equalTo(EstadosDeOperacao.SALDO_INSUFICIENTE));
		assertThat("Saldo", maria.calcularSaldo().obterQuantia(),
				equalTo(quatroReais));
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
