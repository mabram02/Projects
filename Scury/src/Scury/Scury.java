package Scury;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Scury extends JComponent implements KeyListener, Runnable, CollisionDetection{
	public Mouse mouse;
	public Cheese sheeze;
	private int increment;
	private boolean[] keys;
	private BufferedImage back;
	private Wall[] wall = new Wall[6];
	private MissionObjective mission;
	public Scury(){
		mouse = new Mouse(10,10,20,20,Color.RED,0,0);
		mission = new MissionObjective("Get to the Cheese (Use ASDW to Move)", 250, 75, 10, 10, Color.BLUE);
		wall[0] = new Wall(65,0,145,100,Color.BLACK);
		wall[1] = new Wall(65,280,145,410,Color.BLACK);
		wall[2] = new Wall(65,760,145,740,Color.BLACK);
		wall[3] = new Wall(680, 100, 100, 300, Color.BLACK);
		wall[4] = new Wall(780, 300, 100, 300, Color.BLACK);
		wall[5] = new Wall(580, 600, 100, 300, Color.BLACK);
		sheeze = new Cheese(700,700,100,100, Color.yellow);
		keys = new boolean[4];
    	setBackground(Color.WHITE);
		setVisible(true);
		new Thread(this).start();
		addKeyListener(this);	
	}
/*============Animation==================*/
	 public void update(Graphics window)
	   {
		 paint(window);
		 window.setColor(Color.blue);
		 window.drawString("Get to the Cheese", 550, 50);
	   }
	public void paint(Graphics window){
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;
		//take a snap shop of the current screen and save it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));
		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		mission.drawText(graphToBack);
		mouse.MoveAndDraw(graphToBack);
		sheeze.Draw(graphToBack);
		wall[0].Draw(graphToBack);
		wall[1].Draw(graphToBack);
		wall[2].Draw(graphToBack);
		wall[3].Draw(graphToBack);
		wall[4].Draw(graphToBack);
		wall[5].Draw(graphToBack);
		if(CheeseCollision(mouse,sheeze) == true){
			Font myFont = new Font("Serif", Font.BOLD, 160);
			window.setFont(myFont);
			window.drawString("You Got the Cheese!", 50, 500);
			window.dispose();
			keys[0] = false;
			keys[1] = false;
			keys[2] = false;
			keys[3] = false;
			
		}
		//see if the mouse hits the left wall or right wall
		if((mouse.getX() + mouse.getxSpeed()) < 0 || (mouse.getX() + mouse.getWidth()) > 1450){
			if(mouse.getX() + mouse.getxSpeed() < 0){
				keys[0] = false;
			}
			else{
				keys[2] = false;
			}
		}
		//see if the mouse hits the bottom or top wall
		if(((mouse.getY() + mouse.getHeight() + mouse.getySpeed()) > 920) || ((mouse.getY() + mouse.getySpeed()) < 0)){
			mouse.setX(mouse.getX());
			if((mouse.getY() + mouse.getHeight() + mouse.getySpeed()) > 920){
				keys[1] = false;
				mouse.setY(920-mouse.getHeight());
			}
			else{
				keys[3] = false;
				mouse.setY(0);
			}
		}
		//see if the mouse hits one of the drawn walls
		for(increment = 0; increment < 6; increment++){
			if(WallCollision(mouse,wall[increment])){
				if(WallCollisionTop(mouse,wall[increment])){
					mouse.setySpeed(-1);
				}
				if(WallCollisionBottom(mouse,wall[increment])){
					mouse.setySpeed(1);
				}
				if(WallCollisionLeft(mouse,wall[increment])){
					keys[2] = false;
					}
				if(WallCollisionRight(mouse,wall[increment])){
					keys[0] = false;
					}
				}
			}
		//character movement
		if(keys[0] == true){
			//move mouse Left
			if(mouse.getX() + mouse.getxSpeed() > 0)
				mouse.setxSpeed(-1);
				mouse.MoveAndDraw(graphToBack);
		}
		if(keys[0] == false){
			//move mouse Left
			if(mouse.getX() + mouse.getxSpeed() > 0)
				mouse.MoveAndDraw(graphToBack);
				mouse.setxSpeed(0);
		}
		if(keys[1] == true){
			//move mouse down
			if(mouse.getY() <= 1000)
				mouse.MoveAndDraw(graphToBack);
				mouse.setySpeed(1);
		}
		if(keys[1] == false){
			//move mouse down
			if(mouse.getY() <= 1000)
				mouse.MoveAndDraw(graphToBack);
				mouse.setySpeed(0);
		}
		if(keys[2] == true){
			//move mouse Right
			if((mouse.getX() + mouse.getWidth() + mouse.getxSpeed()) <= 1500)
				mouse.MoveAndDraw(graphToBack);
				mouse.setxSpeed(1);
		}
		if(keys[1] == false){
			//move mouse down
			if(mouse.getY() <= 1000)
				mouse.MoveAndDraw(graphToBack);
				mouse.setxSpeed(0);
		}
		if(keys[3] == true){
			//move mouse up
			if(mouse.getY() + mouse.getySpeed() >= 0)
				mouse.MoveAndDraw(graphToBack);
			mouse.setySpeed(-1);
		}
		if(keys[1] == false){
			//move mouse down
			if(mouse.getY() <= 1000)
				mouse.MoveAndDraw(graphToBack);
				mouse.setySpeed(0);
		}
		twoDGraph.drawImage(back, null, 0, 0);
	}
/*============Key Catches================*/
	public void run() {
		try{
			while(true){
	   		   Thread.currentThread();
	   		   Thread.sleep(8);
	           repaint();
	        }
			
	     }
	   	catch(Exception e){}
	}
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(toUpperCase(e.getKeyChar())){
			case 'A' : keys[0]=true; break;
			case 'S' : keys[1]=true; break;
			case 'D' : keys[2]=true; break;
			case 'W' : keys[3]=true; break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(toUpperCase(e.getKeyChar())){
			case 'A' : keys[0]=false; break;
			case 'S' : keys[1]=false; break;
			case 'D' : keys[2]=false; break;
			case 'W' : keys[3]=false; break;
		}
	}
/*==========Logic=============*/
	public boolean WallCollision(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Wall w = (Wall)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		//Checks X Bounds
		if((w.getX()< mWidthTop && mWidthTop < wThickness)||(w.getX()< mWidthBottom && mWidthBottom < wThickness)){
			//Checks Y Bounds
			if((w.getY()< mHeightTop && mHeightTop < wLength)||(w.getY()< mHeightBottom && mHeightBottom < wLength))	
				return true;
		}
		return false;
	}
	public boolean WallCollisionLeft(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Wall w = (Wall)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		if(w.getX()< mWidthBottom && mWidthBottom < wThickness)
			return true;
		return false;
	}
	public boolean WallCollisionRight(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Wall w = (Wall)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		if(w.getX()< mWidthTop && mWidthTop < wThickness)
			return true;
		return false;
	}
	public boolean WallCollisionTop(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Wall w = (Wall)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		if(WallCollision(m,w))
			if(w.getY()< mHeightBottom && mHeightBottom < wLength)
			return true;
		return false;
	}
	public boolean WallCollisionBottom(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Wall w = (Wall)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		if(WallCollision(m,w))
			if(w.getY()< mHeightTop && mHeightTop < wLength)
			return true;
		return false;
	}
	public boolean CheeseCollision(Object obj, Object jbo) {
		Mouse m = (Mouse)obj;
		Cheese w = (Cheese)jbo;
		int mWidthTop = m.getX() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mWidthBottom = m.getX() + m.getWidth() + m.getxSpeed();//Farthest point on the mouse's x direction
		int mHeightTop = m.getY() + m.getySpeed();//Farthest point on the mouse's y direction
		int mHeightBottom = m.getY() + m.getHeight() + m.getySpeed();//Farthest point on the mouse's y direction
		int wLength = w.getY() + w.getHeight();//Farthest point on the wall's x direction
		int wThickness = w.getX() + w.getWidth();//Farthest point on the wall's y direction
		//Checks X Bounds
		if((w.getX()< mWidthTop && mWidthTop < wThickness)||(w.getX()< mWidthBottom && mWidthBottom < wThickness)){
			//Checks Y Bounds
			if((w.getY()< mHeightTop && mHeightTop < wLength)||(w.getY()< mHeightBottom && mHeightBottom < wLength))	
				return true;
		}
		return false;
	}
	
	
}
