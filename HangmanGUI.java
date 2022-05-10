package projeOdevi2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HangmanGUI extends JPanel implements KeyListener
{
	public static int WIDTH=800;
	public static int HEIGHT=600;
    private Font titleFont, regularFont, largeFont, giantFont;
    private Polygon[] gallows;
    private HangmanPuzzle puzzle;
    private String message="Type a letter";
    public static final int MAX_GUESSES=6;
    private int wins,losses;

    /**
     * GUI stands for Graphic User Interface, and is the 
     * front end of the HangmanPuzzle.
     */
    public HangmanGUI()
    {

    	//initialize variables here...
    	titleFont = new Font("Times", Font.BOLD, 18);
    	regularFont = new Font("Helvetica", Font.PLAIN, 12);
    	largeFont = new Font("Times", Font.BOLD, 60);

    	giantFont = new Font("Times", Font.BOLD, 80);

    	gallows =  new Polygon[7];// scaffold, head, body, 2 arms, 2 legs
    	gallows[0] = makeScaffold(52, 100, 12);
    	gallows[1] = makeHead(130, 150, 35);
    	gallows[2] = makeBody(155, 220, 5);
    	gallows[3] = makeArmDown(130, 240, 5);//left arm
    	gallows[4] = makeArmUp(200, 240, 5); //right arm
    	gallows[5] = makeArmUp(130, 370, 5);//left leg
    	gallows[6] = makeArmDown(200, 370, 5);//right leg
    	puzzle = new HangmanPuzzle();
    	wins=0;
    	losses=0;
    }
    /**
     * The main method starts the application and
     * so it ends on a window close.
     * 
     */
	public static void main(String[] args) {
		HangmanGUI app= new HangmanGUI();
		JFrame window = new JFrame("Hangman");
		window.addKeyListener(app);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(app);
		window.setVisible(true);		
	}
	/**
	 * The paint Component method is where to add drawing to
	 * the screen
	 * 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(),getHeight());
		g.setColor(Color.BLUE);
		g.setFont(titleFont);
		g.drawString("Hangman by Group X", 20, 20);
		g.setFont(regularFont);
		g.drawString("Version 1.0", 20, 40);
		if (1>0) {
			g.setFont(titleFont);
			g.setColor(Color.RED);
			g.drawString("You have tried: ", 400, 75);
			g.setFont(largeFont);
			g.drawString("", 400, 150);
		}

		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("  Wins: "+wins, 500, 20);
		g.drawString("Losses: "+losses, 500, 50);
		g.setFont(titleFont);
		g.drawString( " guesses remain", 300, 480);
		g.drawString(message, 300, 460);


		g.setFont(giantFont);
		g.setColor(Color.BLACK);
		g.drawString(puzzle.clue(), 300, 400);

		g.setColor(Color.BLUE);
		int parts = Math.min(MAX_GUESSES, 6);
		for (int i=0; i< 1+ parts; i++){
			g.fillPolygon(gallows[i]);
		}


	}
	/**
	 * The following Polygon returning methods help organizing 
	 * the code required to make the shapes of the game.
	 */
	public Polygon makeScaffold(int x,int y,int scale){
		Polygon result = new Polygon();
		result.addPoint(scale*0, scale*0);
		result.addPoint(scale*15, scale*0);
		result.addPoint(scale*15, scale*2);
		result.addPoint(scale*10, scale*2);
		result.addPoint(scale*10, scale*7);
		result.addPoint(scale*9, scale*7);
		result.addPoint(scale*9, scale*2);

		result.addPoint(scale*2, scale*2);
		result.addPoint(scale*2, scale*30);
		result.addPoint(scale*0, scale*30);
		result.translate(x, y);
		return result;
	}
	public Polygon makeHead(int x,int y,int scale){
		Polygon result = new Polygon();
		double angle=2*Math.PI/18.0;
		for (int i=0; i<18; i++){
			int x0=(int) (scale+Math.round(scale*Math.cos(angle*i)));
			int y0=(int) (scale+Math.round(scale*Math.sin(angle*i)));
			result.addPoint(x0, y0);
		}
		result.translate(x, y);
		return result;
	}
	public Polygon makeArmDown(int x, int y, int scale){
		Polygon result = new Polygon();
		result.addPoint(scale*-5, scale*-5 );
		result.addPoint(scale*5, scale*5 );
		result.addPoint(scale*5, scale*1 );
		result.addPoint(scale*-5, scale*-9 );
		result.translate(x, y);
		return result;
	}
	public Polygon makeArmUp(int x, int y, int scale){
		Polygon result = new Polygon();
		result.addPoint(scale*-5, scale*5 );
		result.addPoint(scale*5, scale*-5 );
		result.addPoint(scale*5, scale*-9 );
		result.addPoint(scale*-5, scale*1 );
		result.translate(x, y);
		return result;
	}
	public Polygon makeBody(int x, int y, int scale){
		Polygon result = new Polygon();
		result.addPoint(scale*0, scale*0 );
		result.addPoint(scale*0, scale*25 );
		result.addPoint(scale*4, scale*25);
		result.addPoint(scale*4, scale*0 );
		result.translate(x, y);
		return result;
	}

	/**
	 * These 3 methods need to be declares to implement the KeyListener Interface
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		String letter = ""+e.getKeyChar();
		

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
