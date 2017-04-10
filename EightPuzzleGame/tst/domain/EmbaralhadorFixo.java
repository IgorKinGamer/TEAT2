package domain;

import java.util.*;

import domain.Puzzle.*;

public class EmbaralhadorFixo implements StrategyShufflePuzzle
{
	/*private static final Direction[] direções =
			new Direction[] {UP, DOWN, LEFT, RIGHT};*/
	private final List<Direction> movimentos;
	
	public EmbaralhadorFixo(List<Direction> movimentos)
	{
		if (movimentos == null)
			this.movimentos = Collections.emptyList();
		else
			this.movimentos = new LinkedList<>(movimentos);
	}
	
	@Override
	public void shuffle(Puzzle jogo)
	{
		for (Direction d : movimentos)
			jogo.moveToEmptyCell(d);
		/*for (int i = 0; i < movimentos; i++)
		{
			boolean mudou = false;
			do
				mudou = game.moveEmptyCell(direções[r.nextInt(4)]);
			while (!mudou);
		}*/
	}
}
