/**
 * 
 */
package gui;

import main.App;
import processing.core.PImage;
import util.AppInjector;
import util.io.Utility;
import vialab.SMT.Zone;

/**
 * The PreviewImage is for visualizing the goal of the game. <BR>
 * 
 * @author <a href="mailto:trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 * @version 0.1 <BR>
 * <BR>
 *          History:<BR>
 *          <LI>[trauf][02.12.2015] Created</LI>
 */


public class PreviewImage extends Zone{
	
	private PImage testImage;
	private PImage newImage;
	
	/**
	 * Create Preview Image
	 *
	 */
	public PreviewImage (){
		
		this.testImage = Utility.getImage("data/5520293_1600x1200.jpg");		// puzzle image
		this.newImage = Utility.getImage("data/pixels.jpg");					// small image for image section of piece
//		PImage maskImage = Utility.getImage("data/mask3.jpg");					// masking image
		
//		load the pixels of both images
		testImage.loadPixels(); 
		newImage.loadPixels();
		
//		writes pixels of puzzle image into small image pixels
		for (int i = 0; i < 100*100; i++) {
			newImage.pixels[i] = testImage.pixels[i+50000];
		}
		newImage.updatePixels();
		
//		masking the image
//		testImage.mask(maskImage);
		
//		Size of zone to interact
		setSize(1024, 768);
	}
	
	public void draw() {

		strokeWeight(5);
		stroke(255);
		image(testImage,0,0);
		image(newImage,1500,20);
		
	}
	
	public void touch() {
//		Image should only be dragged or rotated ...
//		TODO ... within WindowSize, "corners"
		rnt();
	}
	
	public void widget() {
//		TODO turn image into widget
	}

	
}
