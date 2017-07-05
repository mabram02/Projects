package Scury;
import java.awt.*;
//This Class Serves as the Base Class for Every Object that Will be Interacted With on Screen
public class OnScreenObject implements Location {
	//==============Variables====================
	private int xPos, yPos, width, height;
	private Color color;
	private String str;
	
	/*========Constructors=============*/
	public OnScreenObject(int x, int y, int w, int h, Color c, String str){
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height =  h;
		this.color = c;
		this.str = str;
	}	
	public OnScreenObject(){
		setX(0);
		setY(0);
		setHeight(0);
		setWidth(0);
		setColor(Color.BLUE);
	}
	/*================Getters===========*/
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Color getColor() {
		return color;
	}
	public String getStr(){
		return str;
	}
	/*=============Setters==============*/
	public void setX(int x) {
		this.xPos = x;
	}
	public void setY(int y) {
		this.yPos = y;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setStr(String str){
		this.str = str;
	}
	public void setPos(int x, int y){
		xPos = x;
		yPos = y;		
	}
		
	/*=============Paint Methods======================*/
	
	public void drawText(Graphics window){
		window.setColor(color);
		Font myFont = new Font("Serif", Font.BOLD, 55);
		window.setFont(myFont);
		window.drawString(getStr(), getX(), getY());
	}
	public void draw(Graphics window){
		window.setColor(color);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public void draw(Graphics window, Color col){
		window.setColor(col);
	    window.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	/*==========Equivalence Detector==============*/
	public boolean equals(Object obj){
		OnScreenObject two = (OnScreenObject) obj;
		if(xPos == two.getX() && yPos == two.getY())
			if(width == two.getWidth() && height == two.getHeight())
				if(color == two.getColor())
					return true;
			return false;
		}
	/*==========toString===========*/
	public String toString(){
	   	return xPos + " " + yPos + " " + width + " " + height + " " + color.toString();
	   }
}
