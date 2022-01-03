package edu.uoc.uocnejitos.model;

public class Coordinate {

	private int row;
	private int column;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int compareTo(Coordinate other) {
		if (this == other) {
			return 0;
		} else if (this.row > other.getRow()) {
			return 1;
		} else if (this.row < other.getRow()) {
			return -1;
		} else if (this.column > other.getColumn()) {
			return 1;
		} else if (this.column < other.getColumn()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.row == ((Coordinate) obj).getRow() && this.column == ((Coordinate) obj).getColumn()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + column + ")";
	}
	
}
