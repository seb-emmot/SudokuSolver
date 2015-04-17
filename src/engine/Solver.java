package engine;

/*	Solves a sudoku using a brute force algorithm (Sebastian. 2015)
 *  
 *  Loops through numbers 1 through 9 trying to isolate postions and only place a number when posible.
 *  
 */

public class Solver {
	
	public static final int SIZE = 3;
	
	private SudokuGrid sg;
	private long computations = 0;
	
	private int POSx;
	private int POSy;
	
	private boolean LastSpot = false;
	
	private int lapseCounter;

	
	public Solver() {
		this.lapseCounter = 0;
	}
	
	public int Solve(SudokuGrid sg) {
		this.sg = sg;
		boolean haveBeenModified = true;
		int i= 0;
		while(haveBeenModified) {
				System.out.println("lapse");
				this.lapseCounter++;
				haveBeenModified = false;
				
				for(int numberToCheck = 1; numberToCheck <=9; numberToCheck++) {  //checks all positions for 1 number, then continues
					
					for(int xMain=0; xMain<SIZE; xMain++) {
						for(int yMain=0; yMain<SIZE; yMain++) {
							if(canBePlaced(numberToCheck, xMain, yMain)) {
								haveBeenModified = true;
								sg.setNumber(xMain, yMain, POSx, POSy, numberToCheck);
								System.out.println("Number added"+": "+numberToCheck+" - "+xMain+":"+yMain+" : "+POSx+":"+POSy);
							}
						}
					}
					
				}
			
			int numToPlace = 0;
			int numberOfNumberThatCanBePlaced = 0;
			
			for(int xMain=0; xMain<SIZE; xMain++) { //checks all numbers for 1 position, then continues.
				for(int yMain=0; yMain<SIZE; yMain++) {
					for(int x=0; x<SIZE; x++) {
						for(int y=0; y<SIZE; y++) {
							for(int numberToCheck = 1; numberToCheck <=9; numberToCheck++) {
								if(canBePlacedSubSquare(numberToCheck, xMain, yMain, x, y)) {
									numToPlace = numberToCheck;
									numberOfNumberThatCanBePlaced++;
								}
							}
							if(numberOfNumberThatCanBePlaced == 1) {
								System.out.println("F2 placed : "+numToPlace+" - "+xMain+":"+yMain+" : "+x+":"+y);
								sg.setNumber(xMain, yMain, x, y, numToPlace);
								haveBeenModified = true;
								numberOfNumberThatCanBePlaced = 0;
							}
							else {
								numberOfNumberThatCanBePlaced = 0;
							}
						}
					}
				}
			}
			i++;
		}

		System.out.println("total computations : "+this.computations);
		System.out.println("lapse : "+this.lapseCounter);
		System.out.println("End");
		return 0;
	}
	/*
	 * Checks if a number can be placed in the specified "subsquare".
	 * - does the number already exist in subsquare?
	 * - do the number collide with same number in X or Y (in other squares)
	 * - is there only 1 spot left in X/Y or subsquare?
	 * 		if this is the case, set this.Lastspot to true.
	 */
	private boolean canBePlaced(int numberToCheck, int xMain, int yMain) {
		int possiblePositions = 0;
		
		if(doesNumberExist(numberToCheck, xMain, yMain) == false) { //checks if the current number exists in current "subsquare"	
			for(int x=0; x<SIZE; x++) {
				for(int y=0; y<SIZE; y++) {
					if((sg.getNumber(xMain, yMain, x, y) == 0) && (isPlacementPossibleInXandY(numberToCheck, xMain, yMain, x, y) == true)) { //does it collide with any number in X and Y (total area)
						possiblePositions++;
						POSx = x;
						POSy = y;
						if(this.LastSpot == true) {
							this.LastSpot = false;
							return true;
						}
						if(possiblePositions > 1) {
							return false;
						}
					}
				}
			}
		}
		if(possiblePositions == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Checks if specified singular position can hold numberToCheck
	 * - does the number already exist in subsquare?
	 */
	private boolean canBePlacedSubSquare(int numberToCheck, int xMain, int yMain, int x, int y) {
		
		if(doesNumberExist(numberToCheck, xMain, yMain) == false) { //checks if the current number exists in current "subsquare"	
			if((sg.getNumber(xMain, yMain, x, y) == 0) && (isPlacementPossibleInXandY(numberToCheck, xMain, yMain, x, y) == true)) { //does it collide with any number in X and Y (total area)
				this.LastSpot = false;
				return true;
			}
		}
		return false;
	}
	
	private boolean isPlacementPossibleInXandY(int number, int xMainP, int yMainP, int xP, int yP) { // P = placement 
		
		int numbersOnRowX = 0;
		int numbersOnRowY = 0;
		
		for(int xMain=0; xMain<SIZE; xMain++) {
			for(int yMain=0; yMain<SIZE; yMain++) {
				computations++;
				if(xMain == xMainP) { //checks X row
					for(int y=0; y<SIZE; y++) {
						computations++;
						if(sg.getNumber(xMainP, yMain, xP, y) == number) {
							return false;
						}
						if(sg.getNumber(xMainP, yMain, xP, y) > 0) {
							numbersOnRowX++;
						}
					}
				}
				if(yMain == yMainP) { //checks Y
					for(int x=0; x<SIZE; x++) {
						computations++;
						if(sg.getNumber(xMain, yMainP, x, yP) == number) {
							return false;
						}
						if(sg.getNumber(xMain, yMainP, x, yP) > 0) {
							numbersOnRowY++;
						}
					}
				}
			}
		}
		if((numbersOnRowY == 8) || (numbersOnRowX == 8)) {
			this.LastSpot = true;
		}
		return true;
	}
	
	private boolean doesNumberExist(int numberToCheck, int xMain, int yMain) {
		int numberOfNumbers = 0;
		for(int x=0; x<SIZE; x++) {
			for(int y=0; y<SIZE; y++) {
				computations++;
				if(sg.getNumber(xMain, yMain, x, y) > 0) {
					computations++;
					if(sg.getNumber(xMain, yMain, x, y) == numberToCheck) {
						return true;
					}
					else {
					numberOfNumbers++;
					}
				}
			}
		}
		if(numberOfNumbers == 8) {
			this.LastSpot = true;
		}
		return false;
	}
	
	
	
}


