/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import util.AppInjector;
import processing.core.PImage;
import processing.core.PVector;
import util.Constants;
import vialab.SMT.SMT;
import vialab.SMT.Touch;
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
	private double zoom;			//level of magnification
	private int priority;			//level depends on number of comprehended pieces ... 1 for 1 piece, 2 for 2 pieces,..., 5 for 5 pieces, 6 for 6-10 pieces, 7 for 11-25 pieces, 8 for 26-100 pieces, 9 for more than 100
	private int shape;				//type of shape
	private boolean addable;		//can be added to a container
	private int[] convexities;		//which convexities are set - 0 for upper, 1 for right, 2 for lower, 3 for left
	
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
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
		this.zoom = 1;
		this.priority = 1;
		this.shape = shape;
		this.addable = addable;
		this.junctures = junctures;
		this.convexities = new int[4];
		for (int i=0; i<4; i++)
			this.convexities[i] = 0;
		setSize((int)(width+width*Constants.PIECE_SIZE_FACTOR), (int)(height+height*Constants.PIECE_SIZE_FACTOR));
//		TODO delete next line and offer random placement elsewhere
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
	
	public int getXY(String xy) {
		if (xy == "x")
			return this.x;
		else return this.y;
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
	 * @return the addable
	 */
	public boolean isAddable() {
		return addable;
	}
	
	

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	
	/**
	 * @return the convexity
	 */
	public boolean isConvexitySet(int i) {
		if (convexities[i] == 1)
			return true;
		else return false;
	}


	/**
	 * @param convexity the convexity to set
	 */
	public void setConvexity(int i) {
		this.convexities[i] = 1;
	}
	
public boolean detectCollision(Zone collidingZone) {
		
		// Using A,B,C,D as denominators for corners.
		//
		// A-----B   A = [ zone.getX() , zone.getY() ]
		// |     |   B = [ zone.getX() + zone.getWidth() , zone.getY() ]
		// |     |   C = [ zone.getX() + zone.getWidth() , zone.getY() + zone.getHeight() ]
		// D-----C   D = [ zone.getX() , zone.getY() + zone.getY() + zone.getHeight() ]
		
		float max_this = (-1) * Float.MAX_VALUE;
		float min_coll = Float.MAX_VALUE;
		
		float theta = this.getRotationAngle();
		float x = this.getWidth();
		float y = this.getHeight();
		ArrayList<PVector> vertexList_this = new ArrayList<PVector>();
		ArrayList<PVector> vertexList_coll = new ArrayList<PVector>();
		ArrayList<PVector> axisList = new ArrayList<PVector>();
		
		PVector O1 = new PVector(this.getX(), this.getY());									// Origin of our Card; topleft corner, dependent on global coordinates
		PVector A = new PVector(0,0);														// The UL corner in local coordinates
		
		PVector B = new PVector(x * (float) Math.cos(theta) - A.x * (float) Math.sin(theta)	// The UR corner in local coordinates, rotated by theta
								, A.x * (float) Math.cos(theta) + x * (float) Math.sin(theta)
							   );
		
		PVector C = new PVector(x * (float) Math.cos(theta) - y * (float) Math.sin(theta)	// The LR corner in local coordinates, rotated by theta
				, y * (float) Math.cos(theta) + x * (float) Math.sin(theta)
			   );
		
		PVector D = new PVector(A.x * (float) Math.cos(theta) - y * (float) Math.sin(theta)	// The LL corner in local coordinates, rotated by theta
				, y * (float) Math.cos(theta) + A.x * (float) Math.sin(theta)
				   );
		
		// After rotating the boundary box we have to move it to the location of the Card itself;
		// i.e. transform local to global coordinates
		A.add(O1);
		B.add(O1);
		C.add(O1);
		D.add(O1);
		
		theta = collidingZone.getRotationAngle();
		x = collidingZone.getWidth();
		y = collidingZone.getHeight();
		
		PVector O2 = new PVector(collidingZone.getX(), collidingZone.getY());
		
		PVector E = new PVector(0,0);
		PVector F = new PVector(x * (float) Math.cos(theta) - E.x * (float) Math.sin(theta)	// The UR corner in local coordinates, rotated by theta
				, E.x * (float) Math.cos(theta) + x * (float) Math.sin(theta));
		
		PVector G = new PVector(x * (float) Math.cos(theta) - y * (float) Math.sin(theta)	// The LR corner in local coordinates, rotated by theta
				, y * (float) Math.cos(theta) + x * (float) Math.sin(theta)
			   );
		
		PVector H = new PVector(E.x * (float) Math.cos(theta) - y * (float) Math.sin(theta)	// The LL corner in local coordinates, rotated by theta
				, y * (float) Math.cos(theta) + E.x * (float) Math.sin(theta)
				   );
		
		E.add(O2);
		F.add(O2);
		G.add(O2);
		H.add(O2);
		
		vertexList_this.add(A);
		vertexList_this.add(B);
		vertexList_this.add(C);
		vertexList_this.add(D);
		
		vertexList_coll.add(E);
		vertexList_coll.add(F);
		vertexList_coll.add(G);
		vertexList_coll.add(H);
		
		axisList = getAxes(vertexList_this);
		axisList.addAll(getAxes(vertexList_coll));
		
		// For every combination of Vertex and Axis do
		
		for(PVector axis : axisList) {
			PVector projection_this = getProjection(vertexList_this, axis);
			PVector projection_coll = getProjection(vertexList_coll, axis);
			
			float pt_min = Math.min(projection_this.x, projection_this.y);
			float pt_max = Math.max(projection_this.x, projection_this.y);
			float pc_min = Math.min(projection_coll.x, projection_coll.y);
			float pc_max = Math.max(projection_coll.x, projection_coll.y);

			if(pt_min > pc_max || pc_min > pt_max) {
				return false;
			}
		}
		
		// If we get to here then there is a collision on every axis
		// which means our zones collide.
		
		return true;
		
	}
	
	private ArrayList<PVector> getAxes(ArrayList<PVector> nodes) {
		ArrayList<PVector> result = new ArrayList<PVector>();
		
		for(int i=0; i<nodes.size(); i++) {
			PVector node1 = nodes.get(i);
			PVector node2 = nodes.get(i+1 == nodes.size() ? 0 : i+1);
			PVector edge = PVector.sub(node1, node2);
			result.add(new PVector(edge.y, (-1) * edge.x)); // obtaining the normal
		}
		
		return result;
	}
	
	private PVector getProjection(ArrayList<PVector> vertices, PVector axis) {
		
		PVector projection;
		float projection_scalar;
		float min = Float.MAX_VALUE;
		float max = -(Float.MAX_VALUE);
		
		for(PVector v : vertices) {
			
			projection_scalar = PVector.dot(v, axis);
			
			if(projection_scalar < min) {
				min = projection_scalar;
			}
			if(projection_scalar > max) {
				max = projection_scalar;
			}	
		}
		
		projection = new PVector(min, max);
		return projection;
		
	}
	
	/**
	 * Returns the distance from the center of colliding zones.
	 * @param collidingZone The Zone which collides (or not) with this Card Zone
	 * @return the absolute Distance (positive) between zone centers
	 * 			or -1 if no collision happened
	 */
	public float collisionDistance(Zone collidingZone) {
		
		PVector center = this.getCentre();
		PVector coll_center = collidingZone.getCentre();
		
		if(!this.detectCollision(collidingZone)) { // If we don't collide, we're done.
			return -1;
		}
		
		return PVector.dist(center, coll_center);
		
	}


	public void draw() {
//		rect(0, 0, width, height);
		image(imageSection, 0, 0);
	}
	
	public void touch() {
		rnt();
	}
	
	@Override
	public void touchUp(Touch touch) {

		ArrayList<Zone> cluster = AppInjector.zoneManager().getZonesByClass(Piece.class);
		Map<Float, ArrayList<Piece>> distances = new HashMap<Float, ArrayList<Piece> >();
		ArrayList<Piece> list;
		Float dist;

		for(Zone slot : cluster) {
			
			dist = collisionDistance(slot);
			if(dist > 0) {
				
				list = distances.get(dist);
				
				if(list == null) {
					list = new ArrayList<Piece>();
				}
//				adds neighbors
				if((((Piece) slot).getXY("x") == this.getXY("x")-1 && ((Piece) slot).getXY("y") == this.getXY("y")) 			//left
						|| (((Piece) slot).getXY("x") == this.getXY("x") && ((Piece) slot).getXY("y")-1 == this.getXY("y"))		//upper
						|| (((Piece) slot).getXY("x") == this.getXY("x")+1 && ((Piece) slot).getXY("y") == this.getXY("y"))		//right
						|| (((Piece) slot).getXY("x") == this.getXY("x") && ((Piece) slot).getXY("y")+1 == this.getXY("y"))) {	//lower
				list.add((Piece) slot);
				distances.put(dist, list);
				}
			}
		}
		
		for(Float distance : distances.keySet()) {
			list = distances.get(distance);
			for(Piece fittingCluster : list) {
				if(fittingCluster.addPiece(this)) {
					super.touchUp(touch);
					return;
				}
			}
		}
		super.touchUp(touch);

	}


	private boolean addPiece(Piece piece) {
		
		int pieceWidth = AppInjector.engine().getOptimalProperties().getResWidth()/AppInjector.engine().getNumberOfPieces()[0];
		int pieceHeight = AppInjector.engine().getOptimalProperties().getResHeight()/AppInjector.engine().getNumberOfPieces()[1];
		
//		TODO set right Location depending on Rotation of this piece
		if(Math.abs(this.getRotationAngle()-piece.getRotationAngle()) < 30 * Math.PI / 180 || Math.abs(this.getRotationAngle()-piece.getRotationAngle()) > 330 * Math.PI / 180) {
			if(this.getXY("x") == piece.getXY("x")-1 && (this.getXY("y") == piece.getXY("y"))) {			//left
				piece.setLocation(this.getX()+pieceWidth, this.getY());
			}
			else if(this.getXY("x") == piece.getXY("x") && (this.getXY("y")-1 == piece.getXY("y"))) {		//upper
				piece.setLocation(this.getX(), this.getY()-pieceHeight);
			}
			else if(this.getXY("x") == piece.getXY("x")+1 && (this.getXY("y") == piece.getXY("y"))) {		//right
				piece.setLocation(this.getX()-pieceWidth, this.getY());
			}
			else if(this.getXY("x") == piece.getXY("x") && (this.getXY("y")+1 == piece.getXY("y"))) {		//lower
				piece.setLocation(this.getX(), this.getY()+pieceHeight);
			}
			
//			piece.rotateAbout(this.getRotationAngle(), CENTER);
		}
		else return false;
		
		return true;
	}
	
}
