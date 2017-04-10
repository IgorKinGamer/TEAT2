package domain;

import static domain.Puzzle.Direction.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class Testes
{
	/////// putTilesInTheBoard ///////
	@Test
	public void colocarPe�asNoTabuleiro()
	{
		SquareBoard tabuleiro = new SquareBoard(3);
		PuzzleGame jogo = criarJogo();
		List<Tile> pe�as = new ArrayList<>();
		for (int i = 1; i <= 8; i++)
			pe�as.add(new Tile(i));
		List<Position> posi��es = new ArrayList<>();
		for (int i = 1; i <= 3; i++)
			for (int j = 1; j <= 3; j++)
				if (posi��es.size() < 8)
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
	}
	
	
	/////// moveEmptyCell /////
	@Test
	public void moverCasaVaziaParaBaixo()
	{
		PuzzleGame jogo = criarJogo();
		boolean deuCerto = jogo.moveToEmptyCell(DOWN);
		assertTrue(deuCerto);
	}
	
	@Test
	public void moverCasaVaziaParaCima()
	{
		PuzzleGame jogo = criarJogo();
		boolean deuCerto = jogo.moveToEmptyCell(UP);
		assertTrue(deuCerto);
	}
	
	@Test
	public void moverCasaVaziaParaDireita()
	{
		PuzzleGame jogo = criarJogo();
		boolean deuCerto = jogo.moveToEmptyCell(RIGHT);
		assertTrue(deuCerto);
	}
	
	@Test
	public void moverCasaVaziaParaEsquerda()
	{
		PuzzleGame jogo = criarJogo();
		boolean deuCerto = jogo.moveToEmptyCell(LEFT);
		assertTrue(deuCerto);
	}
	
	
	/////// Utilidades ///////
	private PuzzleGame criarJogo()
	{
		return new PuzzleGame(3, new EmbaralhadorFixo(null));
	}
}
