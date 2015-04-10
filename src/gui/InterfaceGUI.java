package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import engine.GameState;
import engine.SudokuGrid;

public class InterfaceGUI implements Observer {
	
	private GameState gs;

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	private Display displayPanel;
	
	private JButton newGameButton;
	
	
	public InterfaceGUI(GameState TheGS) {
		
		int PADDING = 10;
		
		this.gs = TheGS;
		
		frame = new JFrame("Sudoku Solver v.0.1");
		mainPanel = new JPanel();
		buttonsPanel = new JPanel();
		displayPanel = new Display(gs.getGrid());
		newGameButton = new JButton("New");
		
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
		mainPanel.add(displayPanel);
		mainPanel.add(buttonsPanel);
		
		buttonsPanel.add(newGameButton);
		

	    
	    
		frame.pack();
		frame.setLocation(0, 0);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		displayPanel.repaint();    
	    
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
	}
	
	
	public class Display extends JPanel {

		private static final long serialVersionUID = 1L;

		private static final int PADDING = 20;

		private SudokuGrid grid;		

		public Display(SudokuGrid sg) {
			this.grid = sg;
			this.setSize(400, 400);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawSubSquare(g);
		}
		
		private void drawSubSquare(Graphics g) {
			int padderX = 0;
			int padderY = 0;
			for(int numOfSub=0; numOfSub<9; numOfSub++) { //loops through all 3x3 "subsquares
				switch(numOfSub) {
				case 0:
					padderX = 0;
					padderY = 0;
					break;
				case 1:
					padderX = 0;
					padderY = 1;
					break;
				case 2:
					padderX = 0;
					padderY = 2;
					break;
				case 3:
					padderX = 1;
					padderY = 0;
					break;
				case 4:
					padderX = 1;
					padderY = 1;
					break;
				case 5:
					padderX = 1;
					padderY = 2;
					break;
				case 6:
					padderX = 2;
					padderY = 0;
					break;
				case 7:
					padderX = 2;
					padderY = 1;
					break;
				case 8:
					padderX = 2;
					padderY = 2;
					break;
				default:
					break;
				}
				for(int posX=0; posX<3; posX++) { //loops through all 9 positions in each subsquare
					for(int posY=0; posY<3; posY++) {
						g.setColor(Color.BLACK);
						g.drawRect(((padderX*PADDING*3)+(posX*PADDING)), ((padderY*PADDING*3)+(posY*PADDING)), 20, 20);

					}
				}
			}
		}
	}

}


