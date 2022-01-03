package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Move;
import edu.uoc.uocnejitos.model.MoveDirection;

class MoveTest {

	private Move move1, move2;
	
	@BeforeEach
	void testMoveIntIntIntInt() {
		move1 = new Move(0,1,2,3);
		move2 = new Move(5,6,7,8);
	}

	@Test
	void testMoveCoordinateCoordinate() {
		move1 = new Move(new Coordinate(0,1),new Coordinate(2,3));
		move2 = new Move(new Coordinate(5,6),new Coordinate(7,8));
	}

	@Test
	void testGetStart() {
		assertEquals(0,move1.getStart().getRow());
		assertEquals(1,move1.getStart().getColumn());
		
		assertEquals(5,move2.getStart().getRow());
		assertEquals(6,move2.getStart().getColumn());
	}

	@Test
	void testGetRowStart() {
		assertEquals(0,move1.getRowStart());
		assertEquals(5,move2.getRowStart());
	}

	@Test
	void testSetRowStart() {
		move1.setRowStart(10);
		assertEquals(10,move1.getRowStart());
		
		move2.setRowStart(11);
		assertEquals(11,move2.getRowStart());
	}

	@Test
	void testGetColumnStart() {
		assertEquals(1,move1.getColumnStart());		
		assertEquals(6,move2.getColumnStart());
	}

	@Test
	void testSetColumnStart() {
		move1.setColumnStart(13);
		assertEquals(13,move1.getColumnStart());
		
		move2.setColumnStart(14);
		assertEquals(14,move2.getColumnStart());
	}

	@Test
	void testGetEnd() {
		assertEquals(2,move1.getEnd().getRow());
		assertEquals(3,move1.getEnd().getColumn());
		
		assertEquals(7,move2.getEnd().getRow());
		assertEquals(8,move2.getEnd().getColumn());
	}

	@Test
	void testGetRowEnd() {
		assertEquals(2,move1.getRowEnd());		
		assertEquals(7,move2.getRowEnd());		
	}

	@Test
	void testSetRowEnd() {
		move1.setRowEnd(-16);
		assertEquals(-16,move1.getRowEnd());
		
		move2.setRowEnd(-17);
		assertEquals(-17,move2.getRowEnd());
	}

	@Test
	void testGetColumnEnd() {		
		assertEquals(3,move1.getColumnEnd());		
		assertEquals(8,move2.getColumnEnd());
	}

	@Test
	void testSetColumnEnd() {
		move1.setColumnEnd(16);
		assertEquals(16,move1.getColumnEnd());
		
		move2.setColumnEnd(17);
		assertEquals(17,move2.getColumnEnd());
	}

	@Test
	void testGetDirection() {
		assertEquals(MoveDirection.INVALID, move1.getDirection());
		assertEquals(MoveDirection.INVALID, move2.getDirection());
		
		move1.setRowEnd(0);
		assertEquals(MoveDirection.HORIZONTAL, move1.getDirection());
		
		move2.setColumnEnd(6);
		assertEquals(MoveDirection.VERTICAL, move2.getDirection());
		
		move1.setColumnEnd(1);
		assertEquals(MoveDirection.INVALID, move1.getDirection());
		
		move2.setColumnEnd(7);
		assertEquals(MoveDirection.INVALID, move2.getDirection());
		
		move2.setColumnStart(5);
		move2.setColumnEnd(6);
		assertEquals(MoveDirection.INVALID, move2.getDirection());
	}

	@Test
	void testGetHorizontalDistance() {
		assertEquals(2,move1.getHorizontalDistance());
		move1.setColumnEnd(-2);
		assertEquals(-3,move1.getHorizontalDistance());
		
		assertEquals(2,move2.getHorizontalDistance());
		move2.setColumnEnd(-2);
		assertEquals(-8,move2.getHorizontalDistance());
		
		move2.setColumnEnd(10);
		assertEquals(4,move2.getHorizontalDistance());
		
		move2.setColumnEnd(6);
		assertEquals(0,move2.getHorizontalDistance());
	}

	@Test
	void testGetVerticalDistance() {
		assertEquals(2,move1.getVerticalDistance());
		move1.setRowEnd(5);;
		assertEquals(5,move1.getVerticalDistance());
		
		assertEquals(2,move2.getVerticalDistance());
		move2.setRowEnd(4);
		assertEquals(-1,move2.getVerticalDistance());
		
		
		move2.setRowEnd(5);
		assertEquals(0,move2.getVerticalDistance());
	}

	@Test
	void testToString() {
		assertEquals("(0,1) --> (2,3) : INVALID",move1.toString());
		assertEquals("(5,6) --> (7,8) : INVALID",move2.toString());
		
		move1 = new Move(5,1,5,3);
		assertEquals("(5,1) --> (5,3) : HORIZONTAL",move1.toString());
		move1 = new Move(5,1,6,1);
		assertEquals("(5,1) --> (6,1) : VERTICAL",move1.toString());
	}
}