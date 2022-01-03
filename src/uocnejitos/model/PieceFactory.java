package edu.uoc.uocnejitos.model;

/** 
 * Piece Simple Factory class.
 * @author David García Solórzano
 * @version 1.0  
 */
public abstract class PieceFactory {
	
	/**
	 * 
	 * @param coord Coordinate in which the piece is.	 
	 * @param symbol Value of the enumeration called Symbol that corresponds to the piece.
	 * @return Piece object that is related to the "symbol".	 
	 */
	public static Piece getPiece(Coordinate coord, Symbol symbol){
		
		switch(symbol) {
			case BUNNY_WHITE:
			case BUNNY_WHITE_HOLE:
			case BUNNY_GRAY:
			case BUNNY_GRAY_HOLE:
			case BUNNY_BROWN:
			case BUNNY_BROWN_HOLE:
					return new Bunny(coord,symbol);
			case MUSHROOM:
					return new Mushroom(coord);
			case HOLE:
					return new Hole(coord);			
			default: // Grass and others
					return new Grass(coord);
		}		
	}
}