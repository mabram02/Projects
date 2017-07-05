package Scury;

import java.awt.Color;
import java.awt.Graphics;

public class Mouse extends OnScreenObject{
	private int xSpeed, ySpeed;
	public Mouse(int x, int y, int w, int h, Color c, int xSPD, int ySPD){
		super(x,y,w,h,c, null);
		this.setxSpeed(xSPD);
		this.setySpeed(ySPD);
	}
	public Mouse(){
		super(50,200,10,10, Color.WHITE, null);
		this.setxSpeed(0);
		this.setySpeed(0);
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public void MoveAndDraw(Graphics window){
		//draw a white Space at old Mouse location
		this.draw(window,Color.WHITE);
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
	    //draw the Mouse at its new location
		this.draw(window);
	}
	public boolean equals(Object obj){
		Mouse two = (Mouse) obj;
			if(super.equals(obj))
				if(xSpeed == two.getxSpeed() && ySpeed == two.getySpeed())
					return true;
			return false;
	}
	//toString
	public String toString(){
	    return super.toString() + " " + xSpeed + " " + ySpeed;
	    }
}
