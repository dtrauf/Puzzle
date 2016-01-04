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
import processing.core.PImage;
import processing.core.PVector;
import util.AppInjector;
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
		
		PImage maskImg = Utility.getImage("data/puzzleteil.png");
		maskImg.resize(pieceWidth, pieceHeight);
		PImage img = new PImage(pieceWidth, pieceHeight);
		
		for (int i=0; i<AppInjector.engine().getNumberOfPieces()[0]; i++){
			for (int j=0; j<AppInjector.engine().getNumberOfPieces()[1]; j++){
				for (int k=i*pieceWidth;k<i*pieceWidth+pieceWidth;k++){
					for (int l=j*pieceHeight; l<j*pieceHeight+pieceHeight;l++){
						
						img.loadPixels();
						img = puzzleImage.getPuzzleImage().get(k, l, pieceWidth, pieceHeight);
						img.updatePixels();
						img.mask(maskImg);
					}
				}
				
				
				Piece piece = new Piece(i, j, pieceWidth, pieceHeight, 0, true, null);
				piece.setImageSection(img);
				
				AppInjector.zoneManager().add(piece);		
			}
		}
		
		
	}
	
}
