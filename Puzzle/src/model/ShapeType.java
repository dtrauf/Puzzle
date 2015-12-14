/**
 * 
 */
package model;

/**
 * @author <a href="trauf">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public enum ShapeType {
/*	
 * TODO decide how many types are provided
 ** 7 types for edges, corners, and 5 types of inner pieces OR 
 ** 13 types for 4 types of edges, 4 types of corners, and 5 types of inner pieces
 * TODO add path of the masking images in brackets 
*/	
	UPPER_LEFT_CORNER(""),
	UPPER_RIGHT_CORNER(""),
	LOWER_LEFT_CORNER(""),
	LOWER_RIGHT_CORNER(""),
	UPPER_EDGE(""),
	RIGHT_EDGE(""),
	LEFT_EDGE(""),
	LOWER_EDGE(""),
	ZERO_CONVEXITIES(""),
	ONE_CONVEXITIES(""),
	TWO_CONVEXITIES(""),
	THREE_CONVEXITIES(""),
	FOUR_CONVEXITIES("");
	
	private String typeImagePath;
	
	ShapeType(String typeImagePath) {
		this.typeImagePath = typeImagePath;
	}
	
	public String getTypeImagePath() {
		return typeImagePath;
	}
	
}
