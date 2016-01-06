/**
 * 
 */
package model;

/**
 * @author <a href="trauf">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][06.01.2016] Created</LI>
 *
 */
public enum ShapePartType {
	
	FOUR_GAPS("data/shapes/shape01.png"),
	UPPER_CONVEXITY("data/shapes/shape02.png"),
	RIGHT_CONVEXITY("data/shapes/shape03.png"),
	LOWER_CONVEXITY("data/shapes/shape04.png"),
	LEFT_CONVEXITY("data/shapes/shape05.png"),
	UPPER_GAP_FILLLED("data/shapes/shape06.png"),
	RIGHT_GAP_FILLLED("data/shapes/shape07.png"),
	LOWER_GAP_FILLLED("data/shapes/shape08.png"),
	LEFT_GAP_FILLLED("data/shapes/shape09.png");	
	
	
	private String typeImagePath;
	
	ShapePartType(String typeImagePath) {
		this.typeImagePath = typeImagePath;
	}
	
	public String getTypeImagePath() {
		return typeImagePath;
	}

}
