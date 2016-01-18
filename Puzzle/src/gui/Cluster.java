/**
 * 
 */
package gui;

import vialab.SMT.Zone;

/**
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class Cluster extends Zone {

	private int xPos;
	private int yPos;
	private int priority;
	private double angle;
	
	/**
	 * @param xPos
	 * @param yPos
	 * @param priority
	 * @param angle
	 */
	public Cluster(int xPos, int yPos, int priority, double angle) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.priority = priority;
		this.angle = angle;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean addPiece(Piece piece) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
}
