package engine;

import java.util.Observable;


public class SudokuGrid extends Observable {
	
	private static int SIZE = 3;
	
	private int[][][][] sudoku; // the first pair represents major Coords, the 2nd pair minor coords.
	
	public SudokuGrid(int[][] source) {
		this.sudoku = new int[SIZE][SIZE][SIZE][SIZE]; //Makes 9 3x3 squares
		for(int xMain=0; xMain<SIZE; xMain++) {
			for(int yMain=0; yMain<SIZE; yMain++) {
				for(int x=0; x<SIZE; x++) {
					for(int y=0; y<SIZE; y++) {
						this.sudoku[xMain][yMain][x][y] = source[y+(yMain*3)][x+(xMain*3)]; 
					}
				}
			}
		}
	}
	
	
	public void setNumber (int xMain, int yMain, int x, int y, int number) {
		this.sudoku[xMain][yMain][x][y] = number;
		this.notifyObservers();
	}
	
	public int getNumber (int xMain, int yMain, int x, int y) {
		return this.sudoku[xMain][yMain][x][y];
	}

}