package main;

import engine.GameState;
import engine.SudokuGrid;
import gui.InterfaceGUI;

public class Engine {
	public static void main(String[] args) {
		System.out.println("hello");
		SudokuGrid sudokuGrid = new SudokuGrid();
		GameState gs = new GameState(sudokuGrid);
		new InterfaceGUI(gs);
	}
}
