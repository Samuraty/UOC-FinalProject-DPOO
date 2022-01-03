package edu.uoc.uocnejitos.model;

public abstract class Piece {
	private Coordinate coord;
	private Symbol symbol;
	
	protected Piece(Coordinate coord, Symbol symbol) {
		this.coord = coord;
		this.symbol = symbol;
	}
	
	public boolean equals(Object obj) {
		if(this.coord.getRow() == ((Piece) obj).getCoord().getRow() && this.coord.getColumn() == ((Piece) obj).getCoord().getColumn() && this.symbol == ((Piece) obj).getSymbol()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Coordinate getCoord() {
		return coord;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public void setCoord(int row, int column) {
		this.coord.setRow(row);
		this.coord.setColumn(column);
	}
	
	public void setCoord(Coordinate coord) {
		this.coord = coord;	
	}
	
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return String.valueOf(symbol.getAscii());
	}
	
}
