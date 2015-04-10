package engine;

import java.util.Observable;


public class SudokuGrid extends Observable {
	
	private static int SIZE = 3;
	
	private int[][][] sudoku;
	
	public SudokuGrid() {
		this.sudoku = new int[9][SIZE][SIZE]; //Makes 9 3x3 squares
	}
	
	
	public void setNumber (int subsquare, int x, int y, int number) {
		this.sudoku[subsquare][x][y] = number;
	}

}