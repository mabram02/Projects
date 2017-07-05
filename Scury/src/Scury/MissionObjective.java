package Scury;

import java.awt.Color;
import java.awt.Graphics;

public class MissionObjective extends OnScreenObject{
	private String str;
	private static int Width = 1500, Height = 1000;
	private int x, y;
	public MissionObjective(String str, int x, int y, int w, int h, Color c){
		super(x,y,w,h,c, str);
		setX(0);
		setY(0);
		setHeight(Height);
		setWidth(Width);
		setColor(Color.BLACK);
		this.str = str;
		this.x = x;
		this.y = y;
		this.str = str;
	}
	public MissionObjective(){
		setStr("No Objective");
		setX(0);
		setY(0);
	}
	public void MissionComplete(Graphics window){
		drawText(window);
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void Draw(Graphics window){
		window.setColor(Color.blue);
		window.drawString(str,x,y);
	}
}
