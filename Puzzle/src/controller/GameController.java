/**
 * This file is part of the BMBF funded project <B>Nebeneinander wird Miteinander</B>
 * at Technische Universität Chemnitz.
 *
 * All Copyrights reserved @year.
 */
package controller;

import java.util.ArrayList;
import java.util.Random;

import gui.Piece;
import gui.PreviewImage;
import model.PuzzleImage;
import model.ShapePartType;
import model.ShapeType;
import processing.core.PImage;
import processing.core.PVector;
import util.AppInjector;
import util.Constants;
import util.io.Utility;
import vialab.SMT.Zone;

/**
 * The game controller creates the link between model and visualization. Mainly it is responsible for creating GUI elements dictated by the GameEngine. <BR>
 * Whereas the ZoneManager adds, deletes and retrieves Zones from SMT the GameController manages and assigns game-relevant information to such Zones,
 * such as placement, orientation and more.
 * 
 * @author <a href="mailto:storz@informatik.tu-chemnitz.de">storz</a>
 * @version 0.1 <BR>
 * <BR>
 *          History:<BR>
 *          <LI>[storz][31.08.2015] Created</LI>
 */
public class GameController {
	
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	/**
	 * Add a new card at the specified center position with specified rotation angle.
	 * @param cardContent - card information
	 * @param centerPos - where the card center will be placed
	 * @param rotAngle - rotation angle in rad
	 */
	public void addNewCard(Zone zoneToBringIntoPlay, PVector centerPos, float rotAngle) {
		//
//		Zone itemCard = new Zone((int)centerPos.x-GameOptions.CARD_WIDTH/2,(int)centerPos.y-GameOptions.CARD_HEIGHT/2,zoneToBringIntoPlay);
//		itemCard.rotate(rotAngle);
//		AppInjector.zoneManager().add(itemCard);
		
	}

	/**
	 * Use this remove function if a zone is removed due to a game event such as death, destruction, evolution or else.
	 * Aside from removing the Zone from SMT (which should be done via ZoneManager) additional events may trigger here,
	 * e.g. notifying the AI of the death of its units, checking win/lose conditions, etc.
	 * @param stencilContent
	 */
	public void removeZone(Zone zoneToRemoveFromPlay) {

		if (zoneToRemoveFromPlay!=null) {
			AppInjector.zoneManager().remove(zoneToRemoveFromPlay);
		}
		
	}
	
	/**
	 * Add the Preview Image at the scene at the right side with an angle of 90 degree
	 */	
	public void addPreviewImage() {
//		TODO get size of Image from method in Utility class
		PreviewImage pImage = new PreviewImage();

		pImage.translate(AppInjector.application().displayWidth-768,AppInjector.application().displayHeight/4);
		pImage.rotateAbout(4.7123889803847f, Zone.CENTER);
		pImage.scale(0.4f);
		AppInjector.zoneManager().add(pImage);
		
	}
	
	public void createAllPieces() {
		PuzzleImage puzzleImage = new PuzzleImage(AppInjector.engine().getOptimalProperties().getRatioWidth(), AppInjector.engine().getOptimalProperties().getRatioHeight(), AppInjector.engine().getOptimalProperties().getImagePath());

		AppInjector.engine().getNumberOfPieces();
//		get the number of pieces per row and column from the GameEngine - 0 for width, 1 for height
		int pieceWidth = AppInjector.engine().getOptimalProperties().getResWidth()/AppInjector.engine().getNumberOfPieces()[0];
		int pieceHeight = AppInjector.engine().getOptimalProperties().getResHeight()/AppInjector.engine().getNumberOfPieces()[1];
		
//		blending of shape images for masking image
		PImage shape1 = Utility.getImage("data/shapes/shape01.png");
		PImage shape2 = Utility.getImage("data/shapes/shape02.png");
		PImage shape3 = Utility.getImage("data/shapes/shape03.png");
		PImage shape4 = Utility.getImage("data/shapes/shape04.png");
		PImage shape5 = Utility.getImage("data/shapes/shape05.png");
		PImage shape6 = Utility.getImage("data/shapes/shape06.png");
		PImage shape7 = Utility.getImage("data/shapes/shape07.png");
		PImage shape8 = Utility.getImage("data/shapes/shape08.png");
		PImage shape9 = Utility.getImage("data/shapes/shape09.png");
		shape1.resize(pieceWidth, pieceHeight);
		shape2.resize(pieceWidth, pieceHeight);
		shape3.resize(pieceWidth, pieceHeight);
		shape4.resize(pieceWidth, pieceHeight);
		shape5.resize(pieceWidth, pieceHeight);
		shape6.resize(pieceWidth, pieceHeight);
		shape7.resize(pieceWidth, pieceHeight);
		shape8.resize(pieceWidth, pieceHeight);
		shape9.resize(pieceWidth, pieceHeight);		
		
		for (int i=0; i<AppInjector.engine().getNumberOfPieces()[0]; i++){
			for (int j=0; j<AppInjector.engine().getNumberOfPieces()[1]; j++){
				PImage img = new PImage((int)(pieceWidth+pieceWidth*Constants.PIECE_SIZE_FACTOR), (int)(pieceHeight+pieceHeight*Constants.PIECE_SIZE_FACTOR));
								
				img.loadPixels();
/*				loads the pixels of the puzzle image into piece image with: get(x, y, w, h)
 * 				x - upper left x coordinate of source image
 * 				y - upper left y coordinate of source image
 * 				w - width of expected section from source image
 * 				h - height of expected section from source image										
 */
				img = puzzleImage.getPuzzleImage().get((int)(i*pieceWidth-pieceWidth*Constants.PIECE_SIZE_FACTOR/2), (int)(j*pieceHeight-pieceHeight*Constants.PIECE_SIZE_FACTOR/2), (int)(pieceWidth+pieceWidth*Constants.PIECE_SIZE_FACTOR), (int)(pieceHeight+pieceHeight*Constants.PIECE_SIZE_FACTOR));
				img.updatePixels();
				
//				creates a new piece
				Piece piece = new Piece(i, j, pieceWidth, pieceHeight, 0, true, null);
				pieces.add(piece);
				
//				conditions for corners
				if(piece.getXY("x") == 0 && piece.getXY("y") == 0){
					img.mask(blendImages(ShapeType.UPPER_LEFT_CORNER, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("x") == AppInjector.engine().getNumberOfPieces()[0]-1 && piece.getXY("y") == 0) {
					img.mask(blendImages(ShapeType.UPPER_RIGHT_CORNER, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("x") == AppInjector.engine().getNumberOfPieces()[0]-1 && piece.getXY("y") == AppInjector.engine().getNumberOfPieces()[1]-1) {
					img.mask(blendImages(ShapeType.LOWER_RIGHT_CORNER, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("x") == 0 && piece.getXY("y") == AppInjector.engine().getNumberOfPieces()[1]-1) {
					img.mask(blendImages(ShapeType.LOWER_LEFT_CORNER, i, j, pieceWidth, pieceHeight, 0));
				}
				
//				conditions for edges
				else if(piece.getXY("y") == 0){
					img.mask(blendImages(ShapeType.UPPER_EDGE, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("x") == AppInjector.engine().getNumberOfPieces()[0]-1){
					img.mask(blendImages(ShapeType.RIGHT_EDGE, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("y") == AppInjector.engine().getNumberOfPieces()[1]-1){
					img.mask(blendImages(ShapeType.LOWER_EDGE, i, j, pieceWidth, pieceHeight, 0));
				}
				else if(piece.getXY("x") == 0){
					img.mask(blendImages(ShapeType.LEFT_EDGE,  i, j, pieceWidth, pieceHeight, 0));
				}
				
//				inner pieces
				else {
					img.mask(blendImages(ShapeType.INNER_PIECE, i, j, pieceWidth, pieceHeight, 0));
				}
				
				piece.setImageSection(img);
				
				AppInjector.zoneManager().add(piece);				
			}
		}		
	}
	
	private PImage blendImages(ShapeType type, int x, int y, int width, int height, float offset) {
		PImage blendImage = new PImage(width, height);
		PImage clearImage = new PImage(width, height);
		offset = Constants.PIECE_SIZE_FACTOR;
		
//		"overwrites" the image in cache to reset the mask
		blendImage.loadPixels();
		clearImage.loadPixels();
		for (int i = 0; i < width*height; i++) {
			blendImage.pixels[i] = clearImage.pixels[i];
		}		
		blendImage.updatePixels();
		clearImage.updatePixels();
		
		Random r1 = new Random();
		Random r2 = new Random();
		blendImage.blend(this.getShapeImage(ShapePartType.FOUR_GAPS), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
		
		switch (type){
		case UPPER_LEFT_CORNER: 	
			blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
//			fill gaps and add convexity if random number says so
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if (r2.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
			break;
		case UPPER_RIGHT_CORNER: 	
			blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
//			set left convexity if there is a gap on the right side of the left neighbor
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case LOWER_RIGHT_CORNER: 	
			blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
//			set upper convexity if there is a gap on the lower side of the upper neighbor
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case LOWER_LEFT_CORNER: 	
			blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case UPPER_EDGE:			
			blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if (r2.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case RIGHT_EDGE:			
			blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case LEFT_EDGE:			
			blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if (r2.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		case LOWER_EDGE:			
			blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		default:
			if (r1.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.RIGHT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.RIGHT);
			}
			if (r2.nextInt(2) == 1){
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LOWER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).setConvexity(Constants.LOWER);
			}
			if(!pieces.get(y+(AppInjector.engine().getNumberOfPieces()[1]-1)*(x-1)+(x-1)).isConvexitySet(Constants.RIGHT)){
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.LEFT_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			if(!pieces.get((y-1)+(AppInjector.engine().getNumberOfPieces()[1]-1)*x+x).isConvexitySet(Constants.LOWER)){
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_GAP_FILLED), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
				blendImage.blend(this.getShapeImage(ShapePartType.UPPER_CONVEXITY), 0, 0, width, height, 0, 0, width, height, blendImage.BLEND);
			}
			break;
		}

		blendImage.resize((int)(width+width*Constants.PIECE_SIZE_FACTOR), (int)(height+height*Constants.PIECE_SIZE_FACTOR));
		return blendImage;
		
	}
	
	/**
	 * @return the pieces
	 */
	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public PImage getShapeImage(ShapePartType type) {
		PImage shape = null;
		
		switch(type) {
		case FOUR_GAPS:
			shape = Utility.getImage(ShapePartType.FOUR_GAPS.getTypeImagePath());
			break;
		case UPPER_CONVEXITY:
			shape = Utility.getImage(ShapePartType.UPPER_CONVEXITY.getTypeImagePath());
			break;
		case RIGHT_CONVEXITY:
			shape = Utility.getImage(ShapePartType.RIGHT_CONVEXITY.getTypeImagePath());
			break;
		case LOWER_CONVEXITY:
			shape = Utility.getImage(ShapePartType.LOWER_CONVEXITY.getTypeImagePath());
			break;
		case LEFT_CONVEXITY:
			shape = Utility.getImage(ShapePartType.LEFT_CONVEXITY.getTypeImagePath());
			break;
		case UPPER_GAP_FILLED:
			shape = Utility.getImage(ShapePartType.UPPER_GAP_FILLED.getTypeImagePath());
			break;
		case RIGHT_GAP_FILLED:
			shape = Utility.getImage(ShapePartType.RIGHT_GAP_FILLED.getTypeImagePath());
			break;
		case LOWER_GAP_FILLED:
			shape = Utility.getImage(ShapePartType.LOWER_GAP_FILLED.getTypeImagePath());
			break;
		case LEFT_GAP_FILLED:
			shape = Utility.getImage(ShapePartType.LEFT_GAP_FILLED.getTypeImagePath());
			break;
		default: 
			shape = Utility.getImage(ShapePartType.FOUR_GAPS.getTypeImagePath());
			break;
		}
		
		return shape;
	}
	
}
