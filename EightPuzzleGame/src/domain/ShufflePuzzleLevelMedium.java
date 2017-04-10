package domain;

import java.util.Random;

import domain.Puzzle.Direction;

public class ShufflePuzzleLevelMedium implements StrategyShufflePuzzle{
	
	private final int numberOfShuffles = 10;
	
	public ShufflePuzzleLevelMedium() {
		
	}
	
	public void shuffle (Puzzle game){
		Random generateRandom = new Random();
		for (int i=0; i<this.numberOfShuffles; i++){
			boolean changed = false;			
			do {
				int direction = generateRandom.nextInt(4);
				if (direction == 0)
					changed = game.moveToEmptyCell(Direction.UP);
				if (direction == 1)
					changed = game.moveToEmptyCell(Direction.DOWN);
				if (direction == 2)
					changed = game.moveToEmptyCell(Direction.LEFT);
				if (direction == 3)
					changed = game.moveToEmptyCell(Direction.RIGHT);				
			} while (!changed);
		}
	}

}
