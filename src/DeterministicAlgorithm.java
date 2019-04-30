import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DeterministicAlgorithm extends JPanel implements ActionListener{
	
//		IFS FOR A SIERPINSKI TRIANGLE 	
//		double f1 [] = {0.5, 0, 0, 0.5, 1, 1};
//		double f2 [] = {0.5, 0, 0, 0.5, 1, 400};
//		double f3 [] = {0.5, 0, 0, 0.5, 400, 400};
		
//		IFS Figure III.62
		double f1 [] = {0.5, 0, 0, 0.5 , WIDTH/2, HEIGHT/2};
		double f2 [] = {0.5, 0, 0, 0.5 ,   0,    0};

//		IFS FOR A FERN
//		double f1 [] = {0,        0,     0, 0.16, 0,    0};
//		double f2 [] = {0.85,  0.04, -0.04, 0.85, 0,  150};
//		double f3 [] = {0.2,  -0.26,  0.23, 0.22, 0,  150};
//		double f4 [] = {-0.15, 0.28,  0.26, 0.24, 0,  44};

//		IFS FOR A FRACTAL TREE
//		double f1 [] = {0,        0,     0,  0.5, 0, 0};
//		double f2 [] = {0.42, -0.42,  0.42, 0.42, 0, 500};
//		double f3 [] = {0.42,  0.42, -0.42, 0.42, 0, 200};
//		double f4 [] = {0.1,      0,     0,  0.1, 0, 300};
		
// 		TEST
//		double f1 [] = {0.14,     0.01,   0.0,   0.1, -0.08, -1.31};
//		double f2 [] = {0.43,     0.52, -0.45,   0.5,  1.49, -0.75};
//		double f3 [] = {0.45,    -0.49,  0.47,  0.47, -1.62, -0.74};
//		double f4 [] = {0.49,     0.00,  0.00,  0.51,  0.02,  1.62};
		
		double  functions [][] = {f1, f2}; 
		
		static int WIDTH  = 800;
		static int HEIGHT = 800;
		static boolean [][] table = new boolean[WIDTH][HEIGHT];
		static int ITERATIONS = 30;
		
		Timer tm = new Timer(300,this);
		
		private BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int x = 0; x<WIDTH;x++) {
				for(int y = 1; y<HEIGHT;y++) {
						
					int Color = 0xFFFFFFFF;
						
					if(table[x][HEIGHT - y] == true)
						Color = 0x000033;
					buffer.setRGB(x, y, Color);				
				}
			}
			g.drawImage(buffer, 0,0, WIDTH, HEIGHT, null);
			tm.start();
		} 
		@Override
		public void actionPerformed(ActionEvent e) {
			CalculatePoints();
			repaint();
		}
		public void CalculatePoints() {
		boolean [][] tmp = new boolean[WIDTH][HEIGHT];
			
		for(int i = 0; i<WIDTH; i++) {
			for(int j = 0; j<HEIGHT;j++) {
				if(table[i][j] == true) {
					for(int k = 0; k<functions.length; k++) {
						
						int tmpx = (int) (functions[k][0] * (i) + functions[k][1] * (j) + functions[k][4]) ;
						int tmpy = (int) (functions[k][2] * (i) + functions[k][3] * (j) + functions[k][5]) ;
						if(tmpx < WIDTH && tmpy < HEIGHT && tmpx >= 0 && tmpy >= 0) {							
							tmp[tmpx] [tmpy] = true;
						}
					}
				}
			}
		}
		for(int i = 0; i<WIDTH; i++) {
			for(int j = 0; j<HEIGHT; j++) {
				table[i][j] = tmp[i][j];
				tmp[i][j] = false;
			}
		}
	}

		public static void Triangle() {
		
			for(int i = 0; i<WIDTH;i++) {
				table[i][HEIGHT-1] = true;
				table[i][0] = true;
			}
		}	
	
		public static void WinkelHalbierende() {
			
			for(int i = 0; i< Math.min(WIDTH, HEIGHT); i++) {
				table[i] [i] = true;
			}
		}
		
		public static void Quadrat() {
			
			for(int i = WIDTH/2 - 60; i< WIDTH/2 + 30; i++) {
				for(int j = HEIGHT/2 - 60; j< HEIGHT/2 + 30; j++) {
					table[i][j] = true;
				}
			}
		}
		
		public static void MittelLinie() {
			
			for(int i = 0; i<HEIGHT;i++) {
				table[WIDTH/2][i] = true;
			}
		}	
		public static void setFalse() {
			for(int i = 0; i<table.length;i++) {
				for(int j = 0; j<table[0].length; j++) {
					table[i][j] = true;
				}
			}
		}
		
		public static void main(String args[]) {
			//table[500][500] = true;
			Triangle();
			//Quadrat();
			//MittelLinie();
			//setFalse();
			DeterministicAlgorithm s = new DeterministicAlgorithm();
			JFrame frame= new JFrame();        
			frame.setTitle("DeterministicAlgorithm");
			frame.setResizable(true);
			frame.setSize(WIDTH+20, HEIGHT+47);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(s);
			
		}
}
