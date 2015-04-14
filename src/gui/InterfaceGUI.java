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
	private JPanel numberPanel;
	
	
	public InterfaceGUI(GameState TheGS) {
		
		int PADDING = 10;
		
		this.gs = TheGS;
		
		frame = new JFrame("Sudoku Solver v.0.1");
		mainPanel = new JPanel();
		numberPanel = new NumberPanel(gs.getGrid());
		
		frame.add(mainPanel);
		frame.setContentPane(mainPanel);
		mainPanel.add(numberPanel);
		
		frame.pack();
		frame.setLocation(0, 0);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
	}
	
	
	public class NumberPanel extends JPanel {		
		
		private SudokuGrid sg;
		private static final int SIZE = 3;
		private static final int PIXEL_SIZE = 20;
		
		public NumberPanel(SudokuGrid sg) {
			this.sg = sg;
			Dimension d = new Dimension(500, 500);
			this.setMinimumSize(d);
			this.setPreferredSize(d);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawGrid(g);
			drawOverlay(g);
		}
		
		private void drawGrid(Graphics g) {
			System.out.println("FUCK OFF");
			g.setColor(Color.BLACK);
			for(int xMain=0; xMain<SIZE; xMain++) {
				for(int yMain=0; yMain<SIZE; yMain++) {
					for(int x=0; x<SIZE; x++) {
						for(int y=0; y<SIZE; y++) {
							g.drawRect(((xMain*PIXEL_SIZE*3)+(x*PIXEL_SIZE)), ((yMain*PIXEL_SIZE*3)+(y*PIXEL_SIZE)), PIXEL_SIZE, PIXEL_SIZE);
							//System.out.println(sg.getNumber(xMain, yMain, x, y));
							g.drawString(Integer.toString(sg.getNumber(xMain, yMain, x, y)), ((xMain*PIXEL_SIZE*3)+(x*PIXEL_SIZE)+5), ((yMain*PIXEL_SIZE*3)+(y*PIXEL_SIZE)+15));
						}
					}
				}
			}
		}
		
		private void drawOverlay(Graphics g) {
			g.setColor(Color.BLACK);
			for(int xMain=0; xMain<=SIZE; xMain++) {
				g.drawLine((xMain*PIXEL_SIZE*SIZE)+1, 0, (xMain*PIXEL_SIZE*SIZE)+1, SIZE*PIXEL_SIZE*SIZE);
				g.drawLine((xMain*PIXEL_SIZE*SIZE)-1, 0, (xMain*PIXEL_SIZE*SIZE)-1, SIZE*PIXEL_SIZE*SIZE);
			}
			for(int yMain=0; yMain<=SIZE; yMain++) {
				g.drawLine(0, (yMain*PIXEL_SIZE*SIZE)+1, SIZE*PIXEL_SIZE*SIZE, (yMain*PIXEL_SIZE*SIZE)+1);
				g.drawLine(0, (yMain*PIXEL_SIZE*SIZE)-1, SIZE*PIXEL_SIZE*SIZE, (yMain*PIXEL_SIZE*SIZE)-1);

			}
		}
	}

}


