/**
 * 
 */
package gui;

import java.awt.Color;

/**
 * @author <a href="trauf">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class TempStorage extends Territory{
	private int xPos;
	private int yPos;
	private Color c;
	private int[][]vertices;
	/**
	 * @param xPos
	 * @param yPos
	 * @param c
	 * @param vertices
	 */
	public TempStorage(int xPos, int yPos, Color c, int[][] vertices) {
		super(pieces);
		this.xPos = xPos;
		this.yPos = yPos;
		this.c = c;
		this.vertices = vertices;
	}
	
	

}
