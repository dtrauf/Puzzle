/**
 * 
 */
package gui;

import java.awt.Color;

/**
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class GroupTerritory extends Territory {
	private double height;
	private double width;
	private int xPos;
	private int yPos;
	private Color c;
	private double[][]apexes;
	
	/**
	 * @param height
	 * @param width
	 * @param xPos
	 * @param yPos
	 * @param c
	 */
	public GroupTerritory(double height, double width, int xPos, int yPos, Color c) {
		this.height = height;
		this.width = width;
		this.xPos = xPos;
		this.yPos = yPos;
		this.c = c;
	}
	
	

}
