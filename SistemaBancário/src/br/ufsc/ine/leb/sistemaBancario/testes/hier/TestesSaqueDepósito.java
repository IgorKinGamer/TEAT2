package br.ufsc.ine.leb.sistemaBancario.testes.hier;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup(TestesConta.class)
public class TestesSaqueDep�sito
{
	@Fixture
	private SistemaBancario sistema;
	@Fixture
	private Conta maria;
	
	private Dinheiro dezReais;
	private Dinheiro quatroReais;
	private Dinheiro seisReais;
	
	@Before
	public void configurar()
	{
		dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		seisReais = new Dinheiro(Moeda.BRL, 6, 0);
	}
	
	@Test
	public void dep�sito10Reais()
	{
		Operacao dep�sito = sistema.depositar(maria, dezReais);
		
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, dep�sito.obterEstado());
		assertEquals("Saldo", dezReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exerc�cio 5
	@Test
	public void saque6Reais()
	{
		sistema.depositar(maria, dezReais);
		
		Operacao saque = sistema.sacar(maria, seisReais);
		
		assertEquals("Estado", EstadosDeOperacao.SUCESSO, saque.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
	
	// Exerc�cio 6
	@Test
	public void saqueSemSaldo()
	{
		sistema.depositar(maria, quatroReais);
		
		Operacao saqueFalho = sistema.sacar(maria, seisReais);
		
		// Verifica��o
		assertEquals("Estado", EstadosDeOperacao.SALDO_INSUFICIENTE,
				saqueFalho.obterEstado());
		assertEquals("Saldo", quatroReais, maria.calcularSaldo().obterQuantia());
	}
}
