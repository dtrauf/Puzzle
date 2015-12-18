/**
 * 
 */
package model;

/**
 * @author <a href="trauf">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][18.12.2015] Created</LI>
 *
 */
public class ImageProperties {
	
	private int ratioWidth;
	private int ratioHeight;
	private int resWidth;
	private int resHeight;
	private String imagePath;
	
	/**
	 * @param ratioWidth
	 * @param ratioHeight
	 * @param resWidth
	 * @param resHeight
	 * @param imagePath
	 */
	public ImageProperties(int ratioWidth, int ratioHeight, int resWidth, int resHeight, String imagePath) {
		this.ratioWidth = ratioWidth;
		this.ratioHeight = ratioHeight;
		this.resWidth = resWidth;
		this.resHeight = resHeight;
		this.imagePath = imagePath;
	}

	/**
	 * @return the ratioWidth
	 */
	public int getRatioWidth() {
		return ratioWidth;
	}

	/**
	 * @param ratioWidth the ratioWidth to set
	 */
	public void setRatioWidth(int ratioWidth) {
		this.ratioWidth = ratioWidth;
	}

	/**
	 * @return the ratioHeight
	 */
	public int getRatioHeight() {
		return ratioHeight;
	}

	/**
	 * @param ratioHeight the ratioHeight to set
	 */
	public void setRatioHeight(int ratioHeight) {
		this.ratioHeight = ratioHeight;
	}

	/**
	 * @return the resWidth
	 */
	public int getResWidth() {
		return resWidth;
	}

	/**
	 * @param resWidth the resWidth to set
	 */
	public void setResWidth(int resWidth) {
		this.resWidth = resWidth;
	}

	/**
	 * @return the resHeight
	 */
	public int getResHeight() {
		return resHeight;
	}

	/**
	 * @param resHeight the resHeight to set
	 */
	public void setResHeight(int resHeight) {
		this.resHeight = resHeight;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	

}
