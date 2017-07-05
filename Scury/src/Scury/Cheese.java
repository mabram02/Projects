package Scury;
import java.awt.Color;
import java.awt.Graphics;
//This Class Represents the Mouse Which the user Will Controll
public class Cheese extends OnScreenObject {
	public Cheese(int x, int y, int w, int h, Color c){
		super(x,y,w,h,c, null);
	}
	public Cheese(){
		super(50,200,10,10, Color.GRAY, null);
	}
	public void Draw(Graphics window){
		this.draw(window);
	}
}