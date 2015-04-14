package engine;

public class GameState {
	
	private SudokuGrid grid;
	private Solver solver;
	
	public GameState(SudokuGrid sGrid) {
		this.grid = sGrid;
		this.solver = new Solver();
	}
	
	public SudokuGrid getGrid() {
		return grid; // returns grid
		
	}
	
	public void Solve() {
		solver.Solve(grid);
	}
}
