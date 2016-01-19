/**
 * 
 */
package gui;

import java.awt.Color;
import java.util.Vector;

import processing.core.PShape;

/**
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class PersonalTerritory extends Territory {
	
	private float width;
	private float height;
	private Color c;
	private int xPos;
	private int yPos;
//	TODO define data type of mode
	private int mode;			//modes: docked at the edge of the display or flexible on display
	private PShape shape;
	
	
	
	/**
	 * @param width
	 * @param height
	 * @param c
	 * @param xPos
	 * @param yPos
	 * @param mode
	 */
	public PersonalTerritory(float width, float height, int xPos, int yPos, int mode) {
		super(pieces);
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.mode = mode;
		setSize((int)width, (int)height);
		
		
//		shape = createShape(ARC, xPos, yPos, width, height, 0, Math.PI, CHORD);
		
//		Syntax	
//
//		arc(a, b, c, d, start, stop)
//		arc(a, b, c, d, start, stop, mode)
//
//		Parameters	
//		a 	float: x-coordinate of the arc's ellipse
//		b 	float: y-coordinate of the arc's ellipse
//		c 	float: width of the arc's ellipse by default
//		d 	float: height of the arc's ellipse by default
//		start 	float: angle to start the arc, specified in radians
//		stop 	float: angle to stop the arc, specified in radians
		

	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public Vector getPosition() {
		Vector p = new Vector(xPos,yPos);
		return p;
	}
	
	public void setPosition(int xPos, int yPos) {
		this.setX(xPos);
		this.setY(yPos);
	}
	
	public void draw() {
		arc(xPos, yPos, width, height, 0, (float) Math.PI, CHORD);
	}

}

