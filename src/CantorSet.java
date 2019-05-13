import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class CantorSet extends JPanel implements ActionListener{

//		1. IFS Beispiel 7.1 (Cantorsche Menge)
		double f1[] = {0.333,      0};
		double f2[] = {0.333, 1000};
		
// 		2 . IFS Beispiel 7.8		
//		double f1[] = {0,      0};
//		double f2[] = {0.666, 500};		
		
		double  functions [][] = {f1, f2}; 
		
		static int WIDTH  = 1500;
		static int HEIGHT = 800;
		static boolean [][] outPut = new boolean[WIDTH][HEIGHT];
		static int ITERATIONS = 30;
		static int counter = 0;
		static boolean [] xValue = new boolean[WIDTH];
		
		
		Timer tm = new Timer(900,this);
		
		public BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		public void paintComponent(Graphics g) {
			for(int x = 0; x<WIDTH;x++) {
				for(int y = 0; y<HEIGHT;y++) {
						
					int Color = 0xFFFFFFFF;
						
					if(outPut[x][y] == true)
						Color = 0x999966;
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
		public void update() {
			boolean [] tmp = new boolean[WIDTH];			
			
			for(int i = 0; i<WIDTH;i++) {
				for(int k = 0 ; k<functions.length;k++) {
					if(xValue[i] == true) {
						int x = (int) Math.round((i * functions[k][0] + functions[k][1]));
						if(x>= 0 && x< tmp.length)
							tmp[x] = true;
					}
				}
			}
			for(int i = 0; i<WIDTH;i++) {
				xValue[i] = tmp[i];
				tmp[i] = false;
			}
		}
		public void delScreen() {
			for(int i = 0; i<outPut.length;i++) {
				for(int j = 0; j<outPut[0].length; j++) {
					outPut[i][j] = false;
				}
			}
		}
		public void CalculatePoints() {
			update();	// updatet die x Koordinaten
			delScreen();
			for(int j = 0; j< WIDTH;j++) {
				for(int i = 0; i<HEIGHT;i++) {
					if(xValue[j] == true) {
						outPut[j][i] = true;
					}
				}
			}
		}
		public static void initBsp1() {
			for(int i = 0;i< WIDTH;i++) {
				xValue[i] = true;
			}
			for(int i = 0; i<outPut.length;i++) {
				for(int j = 0; j<outPut[0].length; j++) {
					outPut[i][j] = true;
				}
			}
		}
		public static void initBsp2() {
			xValue[0] = true;
			for(int j = 0; j<outPut[0].length; j++) {
				outPut[0][j] = true;
			}
		}
		public static void main(String args[]) {
			initBsp1();
//			initBsp2();
			CantorSet s = new CantorSet();
			JFrame frame= new JFrame();   
			frame.setTitle("Cantor Set");
			frame.setResizable(true);
			frame.setSize(WIDTH+20, HEIGHT+47);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(s);
			
		}
}
