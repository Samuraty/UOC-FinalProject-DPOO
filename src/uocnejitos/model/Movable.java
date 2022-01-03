package edu.uoc.uocnejitos.model;

public interface Movable {

	public boolean isValidMove(Coordinate destination, Level level);
	
	public boolean move(Coordinate destination, Level level);
	
}