package main;

import gui.Piece;
import processing.core.PApplet;
import util.AppInjector;
import util.Constants;
import vialab.SMT.SMT;
import vialab.SMT.TouchSource;
import vialab.SMT.Zone;

public class App extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private PImage testImage;

	public static void main(String args[]) {
		
		PApplet.main(new String[] { "main.App" });
	}
	
	/**
	 * Runs the app in full screen and removes the OS frame around it.
	 */
	public boolean sketchFullScreen() {
		return true;
	}
	
	public void setup() {
		
		AppInjector.initFor(this);
		
		size(displayWidth, displayHeight, SMT.RENDERER);
		SMT.init( this, TouchSource.AUTOMATIC);
		
//		Show a test image
		AppInjector.controller().addPreviewImage();
		
//		create all pieces
		AppInjector.controller().createAllPieces();
		
//		show the current settings
		String name = AppInjector.zoneManager().getZones().get(1).getName();
		System.out.println("Display Resolution:\t" + AppInjector.application().displayWidth + " x " + AppInjector.application().displayHeight);
		System.out.println("Image Resolution:\t" + AppInjector.engine().getOptimalProperties().getResWidth() + " x " + AppInjector.engine().getOptimalProperties().getResHeight());
		System.out.println("Number of Pieces:\t" + AppInjector.zoneManager().getZoneCountFor(Piece.class) + " in " + AppInjector.engine().getNumberOfPieces()[0] + " columns and " + AppInjector.engine().getNumberOfPieces()[1] + " rows");
	}
	
	public void draw() {
		
		background(40);
	}
}
