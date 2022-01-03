package edu.uoc.uocnejitos.model;

public class Move {
	private MoveDirection direction;
	private Coordinate coordStart;
	private Coordinate coordEnd;
	
	public Move(int xStart, int yStart, int xEnd, int yEnd) {
		this.coordStart = new Coordinate(xStart, yStart);
		this.coordEnd = new Coordinate(xEnd, yEnd);
	}
	
	public Move(Coordinate coordStart, Coordinate coordEnd) {
		this.coordStart = coordStart;
		this.coordEnd = coordEnd;
	}
	
	public int getColumnEnd() {
		return coordEnd.getColumn();
	}
	
	public int getColumnStart() {
		return coordStart.getColumn();
	}
	
	public Coordinate getStart() {
		return coordStart;
	}
	
	public MoveDirection getDirection() {
		if(this.coordStart.getColumn() != this.coordEnd.getColumn() && this.coordStart.getRow() == this.coordEnd.getRow()) {
			direction = MoveDirection.HORIZONTAL;
		} else if(this.coordStart.getColumn() == this.coordEnd.getColumn() && this.coordStart.getRow() != this.coordEnd.getRow()) {
			direction = MoveDirection.VERTICAL;
		} else {
			direction = MoveDirection.INVALID;
		}
		return direction;
	}
	
	public Coordinate getEnd() {
		return coordEnd;
	}
	
	public int getHorizontalDistance() {
		return this.coordEnd.getColumn() - this.coordStart.getColumn();
	}
	
	public int getRowEnd() {
		return coordEnd.getRow();
	}
	
	public int getRowStart() {
		return coordStart.getRow();
	}
	
	public int getVerticalDistance() {
		return this.coordEnd.getRow() - this.coordStart.getRow();
	}
	
	public void setColumnEnd(int columnEnd) {
		this.coordEnd.setColumn(columnEnd);
	}
	
	public void setColumnStart(int columnStart) {
		this.coordStart.setColumn(columnStart);
	}
	
	public void setEnd(Coordinate end) {
		this.coordEnd = end;
	}
	
	public void setRowEnd(int rowEnd) {
		this.coordEnd.setRow(rowEnd);
	}
	
	public void setRowStart(int rowStart) {
		this.coordStart.setRow(rowStart);
	}
	
	public void setStart(Coordinate start) {
		this.coordStart = start;
	}
	
	public String toString() {
		return "" + coordStart + " --> " + coordEnd + " : " + getDirection();
	}
}
