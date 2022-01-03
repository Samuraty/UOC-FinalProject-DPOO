package edu.uoc.uocnejitos.model;

public class FoxHead extends Fox implements Movable{

	public FoxHead(Coordinate coord, FoxDirection direction) {
		super(coord, null, direction);
		
		if(direction == FoxDirection.UP) {
			setSymbol(Symbol.FOX_HEAD_UP);
		} else if(direction == FoxDirection.DOWN) {
			setSymbol(Symbol.FOX_HEAD_DOWN);
		} else if(direction == FoxDirection.LEFT) {
			setSymbol(Symbol.FOX_HEAD_LEFT);
		} else if(direction == FoxDirection.RIGHT) {
			setSymbol(Symbol.FOX_HEAD_RIGHT);
		}	
		
		FoxTail tail = new FoxTail(this);
		this.setOtherHalf(tail);
	}
		
	public FoxTail getTail() {
		return (FoxTail) this.getOtherHalf();
	}
	
	public boolean isValidMove(Coordinate destination, Level level) {
		
		try {
			Move move = new Move(this.getCoord(),destination);
			int xStart, xEnd, yStart, yEnd, forStart, forEnd;
			boolean empty = true; //measures if slots between Start and End are empty
			
			xStart = this.getCoord().getColumn();
			yStart = this.getCoord().getRow();
			xEnd = destination.getColumn();
			yEnd = destination.getRow();
			
			if(move.getDirection() == MoveDirection.HORIZONTAL) { //checks what is between Start and End for horizontal movement
				if (xStart <= xEnd) { //sorts Start and End in order to do a i++ for loop
					forStart = xStart;
					forEnd = xEnd;
				} else {
					forStart = xEnd;
					forEnd = xStart;					
				}
				
				for(int i = forStart + 1; i < forEnd; i++) {
					if(!(level.getPiece(yEnd, i) instanceof Grass) && (level.getPiece(yEnd, i) != this.getOtherHalf())) {
						empty = false; //if there are obstacles between Start and End it cannot be a valid movement
					} 
				}
			}
			

			if(move.getDirection() == MoveDirection.VERTICAL) { //check what is between Start and End for vertical movement
				if (yStart <= yEnd) { //sorts Start and End in order to do a i++ for loop
					forStart = yStart;
					forEnd = yEnd;
				} else {
					forStart = yEnd;
					forEnd = yStart;
				}

				for(int i = forStart + 1; i < forEnd; i++) {			
					if(!(level.getPiece(i, xEnd) instanceof Grass) && (level.getPiece(i, xEnd) != this.getOtherHalf())) {
						empty = false; //if there are obstacles between Start and End it cannot be a valid movement
					} 
				}
			}
			
			Coordinate destinationTail = ((FoxTail)(this.getOtherHalf())).calculateCoord(destination);					

			if((!(level.getPiece(destination) instanceof Grass) && (level.getPiece(destination) != this.getOtherHalf())) ||
			    (!(level.getPiece(destinationTail) instanceof Grass) && (level.getPiece(destinationTail) != this)) ||
				!empty ||
				(move.getDirection() == MoveDirection.HORIZONTAL && (this.getDirection() == FoxDirection.UP || this.getDirection() == FoxDirection.DOWN)) || 
				(move.getDirection() == MoveDirection.VERTICAL && (this.getDirection() == FoxDirection.RIGHT || this.getDirection() == FoxDirection.LEFT)) ||
				(yEnd < 0 || yEnd >= level.getSize() || xEnd < 0 || xEnd >= level.getSize())) {
				return false;
			}
			return true;
		} catch (LevelException e) {
			e.printStackTrace();
			return false;
		}
	}
			
	public boolean move(Coordinate destination, Level level) {
		if(destination == null || level == null || this.getCoord() == null || !isValidMove(destination,level)) {
			return false;
		} else {	
			try {				
				int row, column;
				Coordinate originHead = this.getCoord();
				Coordinate originTail = this.getOtherHalf().getCoord();
				
				Grass grassHead = new Grass(originHead);
				level.setPiece(originHead, grassHead); //put a Grass piece in initial head position 
				
				Grass grassTail = new Grass(originTail);
				level.setPiece(originTail, grassTail); //put a Grass piece in initial tail position 
		
				level.setPiece(destination, this); // move fox head to destination
				this.setCoord(destination);	
				
				if(this.getDirection() == FoxDirection.RIGHT) {
					row = destination.getRow();
					column = destination.getColumn() - 1;
				} else if(this.getDirection() == FoxDirection.LEFT) {
					row = destination.getRow();
					column = destination.getColumn() + 1;
				} else if(this.getDirection() == FoxDirection.UP) {
					row = destination.getRow() + 1;
					column = destination.getColumn();
				} else {
					row = destination.getRow() - 1;
					column = destination.getColumn();
				}
				
				Coordinate destinationTail = new Coordinate(row,column);
				
				level.setPiece(destinationTail, getTail()); // move fox tail to destination
				this.getTail().setCoord(destinationTail);

				return true;
			} catch (LevelException e) {
				e.printStackTrace();
				return false;
			}
			
		}
	}
	
}
