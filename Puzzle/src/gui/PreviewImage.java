/**
 * 
 */
package gui;

import processing.core.PImage;
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
	
	/**
	 * Create Preview Image
	 *
	 */
	public PreviewImage (){
		
		testImage = Utility.getImage("data/5520293_1600x1200.jpg");
//		Size of zone to interact
		setSize(1024, 768);
	}
	
	public void draw() {

		strokeWeight(5);
		stroke(255);
		image(testImage,0,0);
		
	}
	
	public void touch() {
//		Image should only be dragged or rotated ...
//		TODO ... within WindowSize
		rnt();
	}
	
	
}
