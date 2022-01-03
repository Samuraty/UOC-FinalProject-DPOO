package edu.uoc.uocnejitos.model;

public class Bunny extends Piece implements Movable{

	public Bunny(Coordinate coord) {
		super(coord, Symbol.BUNNY_BROWN);
	}
	
	public Bunny(Coordinate coord, Symbol symbol) {
		super(coord, symbol);
	}
	
	public boolean isInHole() {
		if(this.getSymbol() == Symbol.BUNNY_BROWN_HOLE || this.getSymbol() == Symbol.BUNNY_GRAY_HOLE || this.getSymbol() == Symbol.BUNNY_WHITE_HOLE) {
			return true;
		}
		return false;
	}
		
	public boolean isValidMove(Coordinate destination, Level level) {
		try {	
			Move move = new Move(this.getCoord(),destination);			
			int xStart, xEnd, yStart, yEnd, forStart, forEnd;
			boolean emptyOrHole = false; //measures if there are empty slots or holes between Start and End
			int countObstacle = 0; //number of obstacles between Start and End
			
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
					if(!level.isObstacle(yEnd, i) || level.getPiece(yEnd, i) instanceof Hole) {
						emptyOrHole = true;  //if there are holes or there are no obstacles between Start and End it cannot be a valid movement
					} else if(level.isObstacle(yEnd, i)) { //if there are obstacles between Start and End we count them
						countObstacle += 1;
					}
				}
			}
			
			if(move.getDirection() == MoveDirection.VERTICAL) { //check what is between Start and End for vertical movement
				if (yStart <= yEnd) { //sort Start and End in order to do a i++ for loop
					forStart = yStart;
					forEnd = yEnd;
				} else {
					forStart = yEnd;
					forEnd = yStart;					
				}
				for(int i = forStart + 1; i < forEnd; i++) {
					if(!level.isObstacle(i, xEnd) || level.getPiece(i, xEnd) instanceof Hole) { 
						emptyOrHole = true; //if there are holes or there are no obstacles between Start and End it cannot be a valid movement
					} else if(level.isObstacle(i, xEnd)) { //if there are obstacles between Start and End we count them
						countObstacle += 1;
					}
				}
			}
			
			if(level.isObstacle(destination) ||
			   move.getDirection() == MoveDirection.INVALID ||
			   emptyOrHole || 
			   countObstacle == 0 || 
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
				Coordinate origin = this.getCoord(); //coordinate where bunny starts jumping
				boolean holeStart = false; //true when bunny starts from a hole
								
				if(this.getSymbol() == Symbol.BUNNY_BROWN_HOLE) {
					this.setSymbol(Symbol.BUNNY_BROWN);
					holeStart = true;
				} else if(this.getSymbol() == Symbol.BUNNY_GRAY_HOLE) {
					this.setSymbol(Symbol.BUNNY_GRAY);
					holeStart = true;
				} else if(this.getSymbol() == Symbol.BUNNY_WHITE_HOLE) {
					this.setSymbol(Symbol.BUNNY_WHITE);
					holeStart = true;
				}
				 
				if(level.getPiece(destination) instanceof Hole) {					
					if(this.getSymbol() == Symbol.BUNNY_BROWN) {
						this.setSymbol(Symbol.BUNNY_BROWN_HOLE);
					} else if(this.getSymbol() == Symbol.BUNNY_GRAY) {
						this.setSymbol(Symbol.BUNNY_GRAY_HOLE);
					} else if(this.getSymbol() == Symbol.BUNNY_WHITE) {
						this.setSymbol(Symbol.BUNNY_WHITE_HOLE);
					}
				} 
				
				if(holeStart) {
					Hole hole = new Hole(origin);
					level.setPiece(origin, hole); //put a Hole piece in origin if bunny starts jumping from a hole
				} else {
					Grass grass = new Grass(origin);
					level.setPiece(origin, grass); //put a Grass piece in origin if bunny does not start jumping from a hole
				}

				level.setPiece(destination, this);
				this.setCoord(destination); //move Bunny to destination

				return true;
			} catch (LevelException e) {
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
}
