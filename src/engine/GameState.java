package engine;

public class GameState {
	
	private SudokuGrid grid;
	
	public GameState(SudokuGrid sGrid) {
		this.grid = sGrid;
	}
	
	public SudokuGrid getGrid() {
		return grid;
		
	}
}
