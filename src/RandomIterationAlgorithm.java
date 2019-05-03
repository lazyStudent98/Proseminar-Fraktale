import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class RandomIterationAlgorithm extends JPanel implements ActionListener{
		
//	IFS FOR A FERN
	double f1 [] = {0,        0,     0, 0.16, 0,    0,  1};
	double f2 [] = {0.85,  0.04, -0.04, 0.85, 0,  1.6, 85};
	double f3 [] = {0.2,  -0.26,  0.23, 0.22, 0,  1.6,  7};
	double f4 [] = {-0.15, 0.28,  0.26, 0.24, 0, 0.44,  7};
	
	double  functions [][] = {f1, f2, f3}; 
	
	
	static int WIDTH  = 800;
	static int HEIGHT = 800;
	static boolean [][] table = new boolean[WIDTH][HEIGHT];
	static int ITERATIONS = 4000;	
	Timer tm = new Timer(300,this);
	private BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	double[] xValues = new double[ITERATIONS];
	double[] yValues = new double[ITERATIONS];

	public static void main(String[] args) {

		RandomIterationAlgorithm s = new RandomIterationAlgorithm();
		JFrame frame= new JFrame();        
		frame.setTitle("RandomIterationAlgorithm");
		frame.setResizable(true);
		frame.setSize(WIDTH+20, HEIGHT+47);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(s);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(buffer, 0,0, WIDTH, HEIGHT, null);
		tm.start();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CalculatePoints(1);
		repaint();
		
	}

	public static void CalculatePoints(int k) {
		Random r = new Random();
		int random = r.nextInt(100);
		
		
	}

}
