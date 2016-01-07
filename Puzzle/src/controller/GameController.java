/**
 * This file is part of the BMBF funded project <B>Nebeneinander wird Miteinander</B>
 * at Technische Universität Chemnitz.
 *
 * All Copyrights reserved @year.
 */
package controller;

import java.awt.Color;

import gui.Piece;
import gui.PreviewImage;
import model.PuzzleImage;
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
		int widthResized = pieceWidth+pieceWidth/10*3;
		int heightResized = pieceHeight+pieceHeight/10*3;
		
		PImage img = new PImage(pieceWidth, pieceHeight);
		
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
		
		
//		shape1.blend(shape6, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//		shape1.blend(shape7, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//		shape1.blend(shape8, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//		shape1.blend(shape9, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
		
		
		
		shape1.resize(pieceWidth, pieceHeight);
/**		TODO groeßere Teile hinzufuegen, die die Ausbuchtungen zur verfügung stellen
 * 		> also immer wenn es mindestens eine Ausbuchtung gibt, handelt es sich um ein größeres Teil
*/		
		
		for (int i=0; i<AppInjector.engine().getNumberOfPieces()[0]; i++){
			for (int j=0; j<AppInjector.engine().getNumberOfPieces()[1]; j++){
//				iterations for changing the position of section in source image
				for (int k=i*pieceWidth;k<i*pieceWidth+pieceWidth;k++){
					for (int l=j*pieceHeight; l<j*pieceHeight+pieceHeight;l++){

//						TODO Maske über andere Methode erzeugen und hier einfuegen
						
						if(j == 0 && k == 0 && l == 0 && i == 0)
							shape1.blend(shape6, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//						if(i == 0)
//							shape1.blend(shape9, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//						if(i == AppInjector.engine().getNumberOfPieces()[0]-1)
//							shape1.blend(shape7, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
//						if(j == AppInjector.engine().getNumberOfPieces()[1]-1)
//							shape1.blend(shape8, 0, 0, pieceWidth, pieceHeight, 0, 0, pieceWidth, pieceHeight, shape1.BLEND);
						
						img.loadPixels();
/*						loads the pixels of the puzzle image into piece image with: get(x, y, w, h)
 * 						x - upper left x coordinate of source image
 * 						y - upper left y coordinate of source image
 * 						w - width of expected section from source image
 * 						h - height of expected section from source image										
 */
						img = puzzleImage.getPuzzleImage().get(k-pieceWidth, l-pieceHeight, pieceWidth, pieceHeight);
						img.updatePixels();
//						img.resize(pieceWidth+pieceWidth/10*3, pieceHeight+pieceHeight/10*3);
//						if ((i ==0 && j==0) || i==3 || i == 5 || j == 2)	
							img.mask(shape1);
					}
				}
				
				
				Piece piece = new Piece(i, j, pieceWidth, pieceHeight, 0, true, null);
				piece.setImageSection(img);
				
				AppInjector.zoneManager().add(piece);		
			}
		}		
	}
	
	private PImage blendImages(ShapeType type) {
		PImage shape1 = Utility.getImage("data/shapes/shape01.png");
		PImage shape2 = Utility.getImage("data/shapes/shape02.png");
		PImage shape3 = Utility.getImage("data/shapes/shape03.png");
		PImage shape4 = Utility.getImage("data/shapes/shape04.png");
		PImage shape5 = Utility.getImage("data/shapes/shape05.png");
		PImage shape6 = Utility.getImage("data/shapes/shape06.png");
		PImage shape7 = Utility.getImage("data/shapes/shape07.png");
		PImage shape8 = Utility.getImage("data/shapes/shape08.png");
		PImage shape9 = Utility.getImage("data/shapes/shape09.png");
		
		switch (type){
		case UPPER_LEFT_CORNER: 
//			shape1.blend(ShapePartType, sx, sy, sw, sh, dx, dy, dw, dh, mode);
			break;
		}
		
		return null;
		
	}
	
}
