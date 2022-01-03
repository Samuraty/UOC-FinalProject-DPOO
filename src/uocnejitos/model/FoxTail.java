package edu.uoc.uocnejitos.model;

public class FoxTail extends Fox implements Movable{

	public FoxTail(FoxHead foxhead) {
		super(new Coordinate (0,0), null, foxhead.getDirection());
			
		if(foxhead.getDirection() == FoxDirection.UP) {
			setSymbol(Symbol.FOX_TAIL_UP);
		} else if(foxhead.getDirection() == FoxDirection.DOWN) {
			setSymbol(Symbol.FOX_TAIL_DOWN);
		} else if(foxhead.getDirection() == FoxDirection.LEFT) {
			setSymbol(Symbol.FOX_TAIL_LEFT);
		} else if(foxhead.getDirection() == FoxDirection.RIGHT) {
			setSymbol(Symbol.FOX_TAIL_RIGHT);
		}	
		
		this.setOtherHalf(foxhead);
		this.setCoord(calculateCoord(foxhead.getCoord()));
	}
	
	public Coordinate calculateCoord(Coordinate coordHead) {
		int row, column;
				
		if(this.getOtherHalf().getDirection() == FoxDirection.RIGHT) {
			row = coordHead.getRow();
			column = coordHead.getColumn() - 1;
		} else if(this.getOtherHalf().getDirection() == FoxDirection.LEFT) {
			row = coordHead.getRow();
			column = coordHead.getColumn() + 1;
		} else if(this.getOtherHalf().getDirection() == FoxDirection.UP) {
			row = coordHead.getRow() + 1;
			column = coordHead.getColumn();
		} else {
			row = coordHead.getRow() - 1;
			column = coordHead.getColumn();
		}
		
		return new Coordinate(row, column);	
	}
	
	private Coordinate getHeadEndCoordinate(Coordinate tailDestination, int sizeBoard) {
		if(tailDestination.getRow() < 0 || tailDestination.getRow() >= sizeBoard || 
		   tailDestination.getColumn() < 0 || tailDestination.getColumn() >= sizeBoard) {
			return null;
		}
		
		Coordinate headDestination = null;

		if(this.getOtherHalf().getDirection() == FoxDirection.RIGHT) {
			headDestination = new Coordinate(tailDestination.getRow(), tailDestination.getColumn() + 1);
		} else if(this.getOtherHalf().getDirection() == FoxDirection.LEFT) {
			headDestination = new Coordinate(tailDestination.getRow(), tailDestination.getColumn() - 1);
		} else if(this.getOtherHalf().getDirection() == FoxDirection.UP) {
			headDestination = new Coordinate(tailDestination.getRow() - 1, tailDestination.getColumn());
		} else {
			headDestination = new Coordinate(tailDestination.getRow() + 1, tailDestination.getColumn());
		}
		
		return headDestination;		
	}
	
	public boolean isValidMove(Coordinate destination, Level level) {
		if (this.getHeadEndCoordinate(destination, level.getSize()) != null) {
			return this.getOtherHalf().isValidMove(this.getHeadEndCoordinate(destination, level.getSize()), level);
		} 
		return false;
	}
	
	public boolean move(Coordinate destination, Level level) {
		if(this.isValidMove(destination, level)) {
			return this.getOtherHalf().move(this.getHeadEndCoordinate(destination, level.getSize()), level);
		}
		return false;
	}
}
