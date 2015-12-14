/**
 * 
 */
package gui;

import java.awt.Point;

import vialab.SMT.Zone;


/**
 * Pieces of the jigsaw game.
 * 
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class Piece extends Zone {

	private int x;					//column position of the piece in the matrix
	private int y;					//row position of the piece in the matrix
	private int xPos;
	private int yPos;
	private int imageSection;		//section of the image of the whole puzzle
	private double angle;			//angle of the piece, initiate with 0
	private double zoom;			//level of magnification
	private int priority;			//level depends on number of comprehended pieces ... 1 for 1 piece, 2 for 2 pieces,..., 5 for 5 pieces, 6 for 6-10 pieces, 7 for 11-25 pieces, 8 for 26-100 pieces, 9 for more than 100
	private int shape;				//type of shape, one of 7 or one of 13
	private boolean addable;		//can be added to a container
	private int[][] junctures;		//coordinates of points for connecting pieces
	
	/**
	 * @param x
	 * @param y
	 * @param imageSection
	 * @param shape
	 * @param addable
	 * @param junctures
	 */
	public Piece(int x, int y, int imageSection, int shape, boolean addable,
			int[][] junctures) {

		this.x = x;
		this.y = y;
		this.imageSection = imageSection;
		this.angle = 0;
		this.zoom = 1;
		this.priority = 1;
		this.shape = shape;
		this.addable = addable;
		this.junctures = junctures;
//		TODO use the AppInjector to get the size of the pieces depending on the number of pieces and calculate the measures in the Utility class
//		setSize();
	}
	
	
	/**
	 * @param xPos the x position to set
	 * @param yPos the y position to set
	 */
	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the shape
	 */
	public int getShape() {
		return shape;
	}

	/**
	 * @param shape the shape to set
	 */
	public void setShape(int shape) {
		this.shape = shape;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	/**
	 * @return the addable
	 */
	public boolean isAddable() {
		return addable;
	}

	/**
	 * @return the junctures
	 */
	public int[][] getJunctures() {
		return junctures;
	}
	
	/**
	 * @return one juncture
	 */
	public Point getJuncture(int a, int b) {
		Point p = new Point((junctures[a][b]),(junctures[a+2][b+2]));
		return p;
	}

	/**
	 * @param imageSection the imageSection to set
	 */
	public void setImageSection(int imageSection) {
		this.imageSection = imageSection;
	}
	
	public void touch() {
		rnt();
	}
	
}
