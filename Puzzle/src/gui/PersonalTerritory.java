/**
 * 
 */
package gui;

import java.awt.Color;
import java.util.Vector;

/**
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class PersonalTerritory extends Territory {
	
	private double width;
	private double height;
	private Color c;
	private int xPos;
	private int yPos;
//	TODO define data type of mode
	private int mode;			//modes: docked at the edge of the display or flexible on display
	
	
	
	/**
	 * @param width
	 * @param height
	 * @param c
	 * @param xPos
	 * @param yPos
	 * @param mode
	 */
	public PersonalTerritory(double width, double height, Color c, int xPos, int yPos, int mode) {
		super(pieces);
		this.width = width;
		this.height = height;
		this.c = c;
		this.xPos = xPos;
		this.yPos = yPos;
		this.mode = mode;

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

}

