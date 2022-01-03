package edu.uoc.uocnejitos.model;

public abstract class Fox extends Piece implements Movable {
	
	private FoxDirection direction;
	private Fox otherHalf;
	
	protected Fox(Coordinate coord, Symbol symbol, FoxDirection direction) {
		super(coord, symbol);
		this.setDirection(direction);
	}
	
	public FoxDirection getDirection() {
		return direction;
	}
	
	public Fox getOtherHalf() {
		return otherHalf;
	}
	
	private void setDirection(FoxDirection direction) {
		this.direction = direction;
	}
	
	protected void setOtherHalf(Fox otherHalf) {
		this.otherHalf = otherHalf;
	}
}
