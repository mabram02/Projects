package Scury;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends OnScreenObject {
	public Wall(int x, int y, int w, int h, Color c){
		super(x,y,w,h,c, null);
	}
	public Wall(){
		super(0,0,0,0,Color.WHITE, null);
	}
	public void Draw(Graphics window){
		this.draw(window);
	}
}
