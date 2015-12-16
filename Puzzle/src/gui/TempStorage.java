/**
 * 
 */
package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author <a href="trauf">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class TempStorage extends Territory{
	
	private ArrayList<Vector> vertices;
	
	private int xPos;
	private int yPos;
	private Color c;

	/**
	 * @param xPos
	 * @param yPos
	 * @param c
	 * @param vertices
	 */
	public TempStorage(int xPos, int yPos, Color c, ArrayList<Vector> vertices) {
		super(pieces);
		this.xPos = xPos;
		this.yPos = yPos;
		this.c = c;
		this.vertices = vertices;
	}
	
	

}
