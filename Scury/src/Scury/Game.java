package Scury;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
@SuppressWarnings("serial")
public class Game extends JFrame{
	private static final int WIDTH = 1500;
	private static final int HEIGHT = 1000;

	public Game(){
		super("Scury");
		setSize(WIDTH,HEIGHT);
		Scury game = new Scury();

		((Component)game).setFocusable(true);
		getContentPane().add(game);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void changePanel(JPanel panel){
		removeAll();
		add(panel);
		revalidate();
		repaint();
	}
	public static void main(String args[]){
		Game run = new Game();
	}
}

