package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.FoxDirection;
import edu.uoc.uocnejitos.model.FoxHead;
import edu.uoc.uocnejitos.model.FoxTail;
import edu.uoc.uocnejitos.model.Level;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Symbol;

class FoxHeadTest {

	private FoxHead head;
	private FoxTail tail;
	
	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public FoxHeadTest() {
		head = new FoxHead(new Coordinate(0,1),FoxDirection.LEFT);
		tail = new FoxTail(head);
	}

	@Test
	void testGetTail() {
		assertEquals(tail,head.getTail());
	}

	@Test
	void testMove() {
Level level;
		
		try {
			level = new Level("levels-tests/level1.txt");
			
			head = (FoxHead) level.getPiece(4,0); //horizontal head
			
			assertTrue(head.move(new Coordinate(4,2),level));
			assertTrue(head.move(new Coordinate(4,1),level));
			assertTrue(head.move(new Coordinate(4,3),level));
			assertFalse(head.move(new Coordinate(4,4),level));
			
			head = (FoxHead) level.getPiece(2,4); //vertical head
			assertTrue(head.move(new Coordinate(1,4),level));
			assertTrue(head.move(new Coordinate(2,4),level));			
			assertFalse(head.move(new Coordinate(3,4),level)); //the other fox is in this cell
			assertTrue(head.move(new Coordinate(0,4),level));
			assertFalse(head.move(new Coordinate(4,4),level));			
			assertTrue(head.move(new Coordinate(2,4),level));
			assertFalse(head.move(new Coordinate(2,4),level));
			
			assertFalse(head.move(new Coordinate(-2,4),level));
			assertFalse(head.move(new Coordinate(22,4),level));
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testMove failed");
		}
	}

	@Test
	void testIsValidMove() {
		try {
			Level level = new Level("levels-tests/level1.txt");
			head = (FoxHead) level.getPiece(4,0);
			
			assertTrue(head.isValidMove(new Coordinate(4,2),level)); //horizontal head			
			assertTrue(head.isValidMove(new Coordinate(4,3),level));
			assertTrue(head.isValidMove(new Coordinate(4,1),level));
			assertFalse(head.isValidMove(new Coordinate(4,4),level));
			
			head = (FoxHead) level.getPiece(2,4); //vertical head
			
			assertTrue(head.isValidMove(new Coordinate(1,4),level));			
			assertTrue(head.isValidMove(new Coordinate(3,4),level));
			assertFalse(head.isValidMove(new Coordinate(4,4),level));
			
			assertFalse(head.isValidMove(new Coordinate(-4,4),level));
			assertFalse(head.isValidMove(new Coordinate(4,-4),level));
			assertFalse(head.isValidMove(new Coordinate(4,34),level));
			
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testIsValidMove failed");
		}
	}

	@Test
	void testGetOtherHalf() {
		assertEquals(tail,head.getOtherHalf());
	}

	@Test
	void testGetDirection() {
		assertEquals(0,head.getCoord().getRow());
		assertEquals(1,head.getCoord().getColumn());
	}

	@Test
	void testGetCoord() {
		assertEquals(0,head.getCoord().getRow());
		assertEquals(1,head.getCoord().getColumn());
	}

	@Test
	void testSetCoordCoordinate() {
		head.setCoord(new Coordinate(4,32));
		assertEquals(4,head.getCoord().getRow());
		assertEquals(32,head.getCoord().getColumn());
	}

	@Test
	void testSetCoordIntInt() {
		head.setCoord(-18,6);
		assertEquals(-18,head.getCoord().getRow());
		assertEquals(6,head.getCoord().getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.FOX_HEAD_LEFT,head.getSymbol());
	}

	@Test
	void testSetSymbol() {
		head.setSymbol(Symbol.FOX_HEAD_RIGHT);
		assertEquals(Symbol.FOX_HEAD_RIGHT,head.getSymbol());
	}

	@Test
	void testEqualsObject() {
		FoxTail head2 = tail;
		assertTrue(tail==head2);
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.LEFT);
		FoxTail head3 = new FoxTail(head);
		assertFalse(tail==head3);
		
		assertFalse(tail==head3);
		assertTrue(tail.equals(head3));
		assertFalse(head2==head3);
		assertTrue(head2.equals(head3));
	}

	@Test
	void testToString() {
		assertEquals("<",head.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.RIGHT);		
		assertEquals(">",head.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.UP);		
		assertEquals("^",head.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.DOWN);		
		assertEquals("V",head.toString());	
	}
}
