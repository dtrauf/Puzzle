/**
 * 
 */
package model;

import processing.core.PImage;
import util.io.Utility;

/**
 * Calculation stuff relying on whole puzzle image. Loads an image which is not displayed.
 * 
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][17.12.2015] Created</LI>
 *
 */
public class PuzzleImage{
	
//	width of resolution of the puzzle image
	private int resWidth;
//	height of resolution of the puzzle image
	private int resHeight;
	
//	path of the image of the puzzle
	private String imagePath;
	
	private PImage puzzleImage;

	/**
	 * @param resWidth
	 * @param resHeight
	 * @param imagePath
	 */
	public PuzzleImage(int resWidth, int resHeight, String imagePath) {
		this.resWidth = resWidth;
		this.resHeight = resHeight;
		this.imagePath = imagePath;
		this.puzzleImage = Utility.getImage(imagePath);
	}
		
	/**
	 * @return the puzzleImage
	 */
	public PImage getPuzzleImage() {
		return puzzleImage;
	}

	/**
	 * @return the resWidth
	 */
	public int getResWidth() {
		return resWidth;
	}

	/**
	 * @return the resHeight
	 */
	public int getResHeight() {
		return resHeight;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

}
