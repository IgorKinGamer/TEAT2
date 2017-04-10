package domain;

import static domain.Puzzle.Direction.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class Testes2
{
	@Test
	public void colocarPe�aNoTabuleiro()
	{
		SquareBoard tabuleiro = new SquareBoard(3);
		PuzzleGame jogo = criarJogo();
		List<Tile> pe�as = new ArrayList<>();
		for (int i = 1; i <= 8; i++)
			pe�as.add(new Tile(i));
		List<Position> posi��es = new ArrayList<>();
		for (int i = 1; i <= 3; i++)
			for (int j = 1; j <= 3; j++)
				//if (posi��es.size() < 8)
					posi��es.add(new Position(i, j));
		
		jogo.dbg_putTilesInTheBoard(tabuleiro, pe�as);
		
		assertEquals(new Tile(1), tabuleiro.getTile(posi��es.get(0)));
		assertEquals(new Tile(2), tabuleiro.getTile(posi��es.get(1)));
		assertEquals(new Tile(3), tabuleiro.getTile(posi��es.get(2)));
		assertEquals(new Tile(4), tabuleiro.getTile(posi��es.get(3)));
		assertEquals(new Tile(5), tabuleiro.getTile(posi��es.get(4)));
		assertEquals(new Tile(6), tabuleiro.getTile(posi��es.get(5)));
		assertEquals(new Tile(7), tabuleiro.getTile(posi��es.get(6)));
		assertEquals(new Tile(8), tabuleiro.getTile(posi��es.get(7)));
		assertEquals(null, tabuleiro.getTile(posi��es.get(8)));
	}
	
	@Test(expected = Exception.class)
	public void colocarNenhumaPe�aNoTabuleiro()
	{
		SquareBoard tabuleiro = new SquareBoard(3);
		PuzzleGame jogo = criarJogo();
		List<Tile> pe�as = Collections.emptyList();
		
		jogo.dbg_putTilesInTheBoard(tabuleiro, pe�as);
	}
	
	/////// moveEmptyCell /////
	@Test
	public void moverNull()
	{
		PuzzleGame jogo = criarJogo();
		boolean deuCerto = jogo.moveToEmptyCell(null);
		assertFalse(deuCerto);
	}
	
	// Entrando no moveDown(), ramo else
	@Test
	public void moverParaBaixo()
	{
		PuzzleGame jogo = criarJogoVazioNoCentro();
		boolean deuCerto = jogo.moveToEmptyCell(DOWN);
		assertTrue(deuCerto);
	}
	// Entrando no moveDown(), ramo if
	@Test
	public void falharMoverParaBaixo()
	{
		PuzzleGame jogo = criarJogoVazioEmCima();
		boolean deuCerto = jogo.moveToEmptyCell(DOWN);
		assertFalse(deuCerto);
	}
	
	@Test
	public void moverParaCima()
	{
		PuzzleGame jogo = criarJogoVazioNoCentro();
		boolean deuCerto = jogo.moveToEmptyCell(UP);
		assertTrue(deuCerto);
	}
	
	@Test
	public void moverParaDireita()
	{
		PuzzleGame jogo = criarJogoVazioNoCentro();
		boolean deuCerto = jogo.moveToEmptyCell(RIGHT);
		assertTrue(deuCerto);
	}
	
	@Test
	public void moverParaEsquerda()
	{
		PuzzleGame jogo = criarJogoVazioNoCentro();
		boolean deuCerto = jogo.moveToEmptyCell(LEFT);
		assertTrue(deuCerto);
	}
	
	
	////// Utilidades ///////
	private PuzzleGame criarJogo()
	{
		return new PuzzleGame(3, new EmbaralhadorFixo(null));
	}
	
	private PuzzleGame criarJogoVazioNoCentro()
	{
		return new PuzzleGame(3, new EmbaralhadorFixo(
				Arrays.asList(new Puzzle.Direction[] {DOWN, RIGHT})));
	}
	
	private PuzzleGame criarJogoVazioEmCima()
	{
		return new PuzzleGame(3, new EmbaralhadorFixo(
				Arrays.asList(new Puzzle.Direction[] {DOWN, DOWN})));
	}
}
