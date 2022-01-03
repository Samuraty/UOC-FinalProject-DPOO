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

class FoxTailTest {

	private FoxHead head;
	private FoxTail tail;

	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public FoxTailTest() {
		head = new FoxHead(new Coordinate(0,1),FoxDirection.LEFT);
		tail = new FoxTail(head);
	}
	
	@Test
	void testCalculateCoord() {
		Coordinate coordHead = head.getCoord();				
		Coordinate coordTail = tail.calculateCoord(coordHead);
		
		//Horizontal - LEFT
		assertEquals(0,coordTail.getRow());
		assertEquals(2,coordTail.getColumn());
		
		coordHead.setColumn(4);
		coordTail = tail.calculateCoord(coordHead);
		assertEquals(0,coordTail.getRow());
		assertEquals(5,coordTail.getColumn());
		
		//Horizontal - RIGHT
		head = new FoxHead(new Coordinate(0,1),FoxDirection.RIGHT);
		tail = new FoxTail(head);
		coordHead = head.getCoord();
		coordTail = tail.calculateCoord(coordHead);
		assertEquals(0,coordTail.getRow());
		assertEquals(0,coordTail.getColumn());
		
		//Vertical - UP
		head = new FoxHead(new Coordinate(0,1),FoxDirection.UP);
		tail = new FoxTail(head);
		coordHead = head.getCoord();
		coordTail = tail.calculateCoord(coordHead);
		
		assertEquals(1,coordTail.getRow());
		assertEquals(1,coordTail.getColumn());
		
		//Vertical - DOWN
		head = new FoxHead(new Coordinate(1,3),FoxDirection.DOWN);
		tail = new FoxTail(head);
		coordHead = head.getCoord();
		coordTail = tail.calculateCoord(coordHead);
		
		assertEquals(0,coordTail.getRow());
		assertEquals(3,coordTail.getColumn());
	}

	@Test
	void testIsValidMove() {
		try {
			Level level = new Level("levels-tests/level1.txt");
			tail = (FoxTail) level.getPiece(4,1);
			
			assertTrue(tail.isValidMove(new Coordinate(4,2),level));			
			assertTrue(tail.isValidMove(new Coordinate(4,3),level));
			assertTrue(tail.isValidMove(new Coordinate(4,4),level));
			
			tail = (FoxTail) level.getPiece(3,4); //vertical tail
			
			assertFalse(tail.isValidMove(new Coordinate(3,4),level));			
			assertFalse(tail.isValidMove(new Coordinate(0,4),level));
			assertTrue(tail.isValidMove(new Coordinate(1,4),level));
			assertTrue(tail.isValidMove(new Coordinate(4,4),level));
			assertTrue(tail.isValidMove(new Coordinate(2,4),level));
			
			assertFalse(tail.isValidMove(new Coordinate(-2,4),level));
			assertFalse(tail.isValidMove(new Coordinate(2,-4),level));
			assertFalse(tail.isValidMove(new Coordinate(22,34),level));
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testIsValidMove failed");
		}
	}

	@Test
	void testMove() {
		Level level;
		
		try {
			level = new Level("levels-tests/level1.txt");
			
			tail = (FoxTail) level.getPiece(4,1); //horizontal tail
			
			assertTrue(tail.move(new Coordinate(4,2),level));
			assertTrue(tail.move(new Coordinate(4,1),level));
			assertTrue(tail.move(new Coordinate(4,4),level));
			
			tail = (FoxTail) level.getPiece(3,4); //vertical tail
			assertTrue(tail.move(new Coordinate(1,4),level));
			assertTrue(tail.move(new Coordinate(2,4),level));
			assertFalse(tail.move(new Coordinate(4,4),level)); //The other fox is in this cell			
			assertTrue(tail.move(new Coordinate(3,4),level));
			
			assertFalse(tail.move(new Coordinate(-4,4),level));
			assertFalse(tail.move(new Coordinate(4,-4),level));
			assertFalse(tail.move(new Coordinate(4,44),level));
			assertFalse(tail.move(new Coordinate(44,4),level));
			assertFalse(tail.move(new Coordinate(44,-4),level));
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testMove failed");
		}
	}

	@Test
	void testGetOtherHalf() {
		assertEquals(head,tail.getOtherHalf());
	}

	@Test
	void testGetDirection() {
		assertEquals(FoxDirection.LEFT,tail.getDirection());
	}

	@Test
	void testGetCoord() {
		assertEquals(0,tail.getCoord().getRow());
		assertEquals(2,tail.getCoord().getColumn());
	}

	@Test
	void testSetCoordCoordinate() {
		tail.setCoord(new Coordinate(24,32));
		assertEquals(24,tail.getCoord().getRow());
		assertEquals(32,tail.getCoord().getColumn());
	}

	@Test
	void testSetCoordIntInt() {
		tail.setCoord(-18,6);
		assertEquals(-18,tail.getCoord().getRow());
		assertEquals(6,tail.getCoord().getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.FOX_TAIL_LEFT,tail.getSymbol());
	}

	@Test
	void testSetSymbol() {
		tail.setSymbol(Symbol.FOX_TAIL_RIGHT);
		assertEquals(Symbol.FOX_TAIL_RIGHT,tail.getSymbol());
	}

	@Test
	void testEqualsObject() {
		FoxTail tail2 = tail;
		assertTrue(tail==tail2);
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.LEFT);
		FoxTail tail3 = new FoxTail(head);
		assertFalse(tail==tail3);
		
		assertFalse(tail==tail3);
		assertTrue(tail.equals(tail3));
		assertFalse(tail2==tail3);
		assertTrue(tail2.equals(tail3));
	}

	@Test
	void testToString() {
		assertEquals("⊣",tail.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.RIGHT);
		tail = new FoxTail(head);
		assertEquals("⊢",tail.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.UP);
		tail = new FoxTail(head);
		assertEquals("⊥",tail.toString());
		
		head = new FoxHead(new Coordinate(0,1),FoxDirection.DOWN);
		tail = new FoxTail(head);
		assertEquals("T",tail.toString());		
	}
}