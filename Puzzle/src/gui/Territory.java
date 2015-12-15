/**
 * 
 */
package gui;

import java.util.ArrayList;

import vialab.SMT.Zone;

/**
 * @author <a href="trauf@hrz.tu-chemnitz.de">Daniel Trauf</a>
 *<BR>
 *		History:<BR>
 *		<LI>[trauf][14.12.2015] Created</LI>
 *
 */
public class Territory extends Zone {
	protected static ArrayList<Piece> pieces;

	/**
	 * @param pieces
	 */
	public Territory(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	
}
