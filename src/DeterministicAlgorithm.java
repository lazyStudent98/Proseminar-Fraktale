import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DeterministicAlgorithm extends JPanel implements ActionListener{
	
//		IFS FOR A SIERPINSKI TRIANGLE 	
//		double f1 [] = {0.5, 0, 0, 0.5, 1, 1};
//		double f2 [] = {0.5, 0, 0, 0.5, 1, 400};
//		double f3 [] = {0.5, 0, 0, 0.5, 400, 400};
	
// 		IFS CREEPY STUFF
//		double f1 [] = {0, 0.5, 0.5, 0, 1, 1};
//		double f2 [] = {0, 0.5, 0.5, 0, 1, 400};
//		double f3 [] = {0, 0.5, 0.5, 0, 400, 400};	
		
//		IFS Figure III.62
//		double f1 [] = {0.5, 0, 0, 0.5 , WIDTH/2, HEIGHT/2};
//		double f2 [] = {0.5, 0, 0, 0.5 ,   0,    0};

//		IFS FOR A FERN
		double f1 [] = {0,        0,     0, 0.16, 0,    0};
		double f2 [] = {0.85,  0.04, -0.04, 0.85, 0,  110};
		double f3 [] = {0.2,  -0.26,  0.23, 0.22, 0,  110};
		double f4 [] = {-0.15, 0.28,  0.26, 0.24, 0,  44};
		
// 		TEST
//		double f1 [] = {0.14,     0.01,   0.0,   0.1, 0, 400};
//		double f2 [] = {0.43,     0.52, -0.45,   0.5,  0, 220};
//		double f3 [] = {0.45,    -0.49,  0.47,  0.47, 0, 220};
//		double f4 [] = {0.49,     0.00,  0.00,  0.51,  0,  400};
		
		double  functions [][] = {f1 ,f2,f3,f4}; 
		
		static int WIDTH  = 800;
		static int HEIGHT = 800;
		static boolean [][] outPut = new boolean[WIDTH][HEIGHT];
		static int ITERATIONS = 30;
		static int counter = 0;
		
		Timer tm = new Timer(300,this);
		
		public BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		public void paintComponent(Graphics g) {
			for(int x = 0; x<WIDTH;x++) {
				for(int y = 1; y<HEIGHT;y++) {
						
					int Color = 0xFFFFFFFF;
						
					if(outPut[x][HEIGHT - y] == true)
						Color = 0xFFA500;
					buffer.setRGB(x, y, Color);		
				}
			}
			g.drawImage(buffer, 0,0, WIDTH, HEIGHT, null);
			tm.start();
			if(counter >= ITERATIONS)
				tm.stop();
		} 
		@Override
		public void actionPerformed(ActionEvent e) {
			CalculatePoints();
			repaint();
			counter++;
			System.out.println("Iteration : " + counter);
			
		}
		public void CalculatePoints() {			
		boolean [][] tmp = new boolean[WIDTH][HEIGHT];
			
		for(int i = 0; i<WIDTH; i++) {
			for(int j = 0; j<HEIGHT;j++) {
				if(outPut[i][j] == true) {
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
				outPut[i][j] = tmp[i][j];
				tmp[i][j] = false;
			}
		}
	}
		public static void UntenObenLinie() {
		
			for(int i = 0; i<WIDTH;i++) {
				outPut[i][HEIGHT-1] = true;
				outPut[i][0] = true;
			}
		}	
		public static void winkelHalbierende() {
			
			for(int i = 0; i< Math.min(WIDTH, HEIGHT); i++) {
				outPut[i] [i] = true;
			}
		}		
		public static void quadratOben() {
			
			for(int i = 0; i< 60; i++) {
				for(int j = 700; j< 760; j++) {
					outPut[i][j] = true;
				}
			}
		}
		public static void quadrat() {
			for(int i = WIDTH/2 - 60; i< WIDTH/2 + 30; i++) {
				for(int j = HEIGHT/2 - 60; j< HEIGHT/2 + 30; j++) {
					outPut[i][j] = true;
				}
			}
		}		
		public static void mittelLinie() {
			
			for(int i = 0; i<HEIGHT;i++) {
				outPut[WIDTH/2][i] = true;
			}
		}	
		public static void ganzerScreen() {
			for(int i = 0; i<outPut.length;i++) {
				for(int j = 0; j<outPut[0].length; j++) {
					outPut[i][j] = true;
				}
			}
		}
		public static void kreisImMittelpunkt(int mittelpunktx,int mittelpunkty, int radius) {
			for(int i = 0; i<WIDTH;i++) {
				for(int j = 0; j<HEIGHT;j++) {
					if((int) Math.sqrt((i-mittelpunktx)*(i-mittelpunktx) + (j-mittelpunkty)*(j-mittelpunkty)) == radius)
						outPut[i][j] = true;
				}
			}
		}
		public static void phi() {
			UntenObenLinie();
			mittelLinie();
			kreisImMittelpunkt(399, 399, 300);
		}
		
		public static void main(String args[]) {
//			outPut[40][500] = true;
//			outPut[0][0] = true;
//			outPut[WIDTH-1][HEIGHT-1] = true;
//			UntenObenLinie();
//			winkelHalbierende();
//			quadratOben();
//			quadrat();
//		    mittelLinie();
//			ganzerScreen();
			kreisImMittelpunkt(399, 399, 300);
//			phi();
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
