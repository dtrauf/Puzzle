/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.Vector;

import processing.core.PImage;
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

	private ArrayList<Vector> junctures;		//coordinates of points for connecting pieces
	
	private int x;					//column position of the piece in the matrix
	private int y;					//row position of the piece in the matrix
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private PImage imageSection;		//section of the image of the whole puzzle
	private double angle;			//angle of the piece, initiate with 0
	private double zoom;			//level of magnification
	private int priority;			//level depends on number of comprehended pieces ... 1 for 1 piece, 2 for 2 pieces,..., 5 for 5 pieces, 6 for 6-10 pieces, 7 for 11-25 pieces, 8 for 26-100 pieces, 9 for more than 100
	private int shape;				//type of shape, one of 7 or one of 13
	private boolean addable;		//can be added to a container
	
	
	/**
	 * @param x
	 * @param y
	 * @param imageSection
	 * @param shape
	 * @param addable
	 * @param junctures
	 */
	public Piece(int x, int y, int width, int height, int shape, boolean addable,
			ArrayList<Vector> junctures) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.zoom = 1;
		this.priority = 1;
		this.shape = shape;
		this.addable = addable;
		this.junctures = junctures;
//		TODO use the AppInjector to get the size of the pieces depending on the number of pieces and calculate the measures in the Utility class
		setSize(width, height);
		translate(width*x, height*y);
	}
	
	
	/**
	 * @param xPos the x position to set
	 * @param yPos the y position to set
	 */
	public void setPosition(int xPos, int yPos) {
		this.setX(xPos);
		this.setY(yPos);
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
	public ArrayList<Vector> getJunctures() {
		return junctures;
	}

	/**
	 * @param imageSection the imageSection to set
	 */
	public void setImageSection(PImage imageSection) {
		this.imageSection = imageSection;
	}
	
	public void draw() {
//		rect(0, 0, width, height);
		image(imageSection, 0, 0);
	}
	
	public void touch() {
		rnt();
	}
	
}
