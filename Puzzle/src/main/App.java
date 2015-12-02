package main;

import processing.core.PApplet;
import util.AppInjector;
import vialab.SMT.SMT;
import vialab.SMT.TouchSource;

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
		
	}
	
	public void draw() {
		
		background(30);
	}
}
