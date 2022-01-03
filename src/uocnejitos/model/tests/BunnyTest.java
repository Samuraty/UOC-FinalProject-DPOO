package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Bunny;
import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Level;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Symbol;

class BunnyTest {

	private Bunny bunny;

	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public BunnyTest() {
		bunny = new Bunny(new Coordinate(0,1));
	}
	
	@Test
	void testBunnyCoordinateSymbol() {
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_BROWN);
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_BROWN_HOLE);
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_WHITE);
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_WHITE_HOLE);
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_GRAY);
		bunny = new Bunny(new Coordinate(0,1),Symbol.BUNNY_GRAY_HOLE);		
	}

	@Test
	void testIsInHole() {
		assertFalse(bunny.isInHole());
		bunny.setSymbol(Symbol.BUNNY_BROWN_HOLE);
		assertTrue(bunny.isInHole());
	}

	@Test
	void testMove() {
		Level level;
		
		try {
			level = new Level("levels-tests/level1.txt");
			
			bunny = (Bunny) level.getPiece(2,1);
			
			assertTrue(bunny.move(new Coordinate(0,1),level));
			assertEquals(Symbol.BUNNY_BROWN_HOLE,bunny.getSymbol());
			assertEquals(Symbol.GRASS,level.getPiece(2,1).getSymbol());
			assertTrue((new Coordinate(2,1)).equals(level.getPiece(2,1).getCoord()));
			assertTrue((new Coordinate(0,1)).equals(level.getPiece(0,1).getCoord()));
			
			assertTrue(bunny.move(new Coordinate(2,1),level));
			assertEquals(Symbol.BUNNY_BROWN,bunny.getSymbol());
			assertEquals(Symbol.HOLE,level.getPiece(0,1).getSymbol());
			assertTrue((new Coordinate(2,1)).equals(level.getPiece(2,1).getCoord()));
			assertTrue((new Coordinate(0,1)).equals(level.getPiece(0,1).getCoord()));
			
			assertFalse(bunny.move(new Coordinate(3,1),level));
			assertFalse(bunny.move(new Coordinate(4,1),level));
			assertFalse(bunny.move(new Coordinate(1,1),level));			
			assertFalse(bunny.move(new Coordinate(1,0),level));
			assertFalse(bunny.move(new Coordinate(0,0),level));
			assertFalse(bunny.move(new Coordinate(1,0),level));			
			assertTrue(bunny.move(new Coordinate(2,3),level));
			assertFalse(bunny.move(null,level));
			assertFalse(bunny.move(new Coordinate(2,3),null));
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testMove failed");
		}
	}

	@Test
	void testIsValidMove() {
		try {
			Level level = new Level("levels-tests/level1.txt");
			bunny = (Bunny) level.getPiece(2,1);
			
			assertTrue(bunny.isValidMove(new Coordinate(0,1),level));			
			assertFalse(bunny.isValidMove(new Coordinate(3,1),level));
			assertFalse(bunny.isValidMove(new Coordinate(4,1),level));
			assertFalse(bunny.isValidMove(new Coordinate(1,1),level));			
			assertFalse(bunny.isValidMove(new Coordinate(1,0),level));
			assertFalse(bunny.isValidMove(new Coordinate(0,0),level));
			assertFalse(bunny.isValidMove(new Coordinate(1,0),level));			
			assertTrue(bunny.isValidMove(new Coordinate(2,3),level));
			
			assertFalse(bunny.isValidMove(new Coordinate(-2,3),level));
			assertFalse(bunny.isValidMove(new Coordinate(2,-3),level));
			assertFalse(bunny.isValidMove(new Coordinate(22,3),level));
			assertFalse(bunny.isValidMove(new Coordinate(22,33),level));
			assertFalse(bunny.isValidMove(new Coordinate(2,33),level));
			
			bunny = (Bunny) level.getPiece(2,1);
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testIsValidMove failed");
		}
	}

	@Test
	void testGetCoord() {
		Coordinate coord = bunny.getCoord();
		assertEquals(0,coord.getRow());
		assertEquals(1,coord.getColumn());
	}

	@Test
	void testSetCoordCoordinate() {
		bunny.setCoord(new Coordinate(4,2));
		assertEquals(4,bunny.getCoord().getRow());
		assertEquals(2,bunny.getCoord().getColumn());

	}

	@Test
	void testSetCoordIntInt() {
		bunny.setCoord(-8,-6);
		assertEquals(-8,bunny.getCoord().getRow());
		assertEquals(-6,bunny.getCoord().getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.BUNNY_BROWN,bunny.getSymbol());
	}

	@Test
	void testSetSymbol() {
		bunny.setSymbol(Symbol.BUNNY_GRAY_HOLE);
		assertEquals(Symbol.BUNNY_GRAY_HOLE,bunny.getSymbol());		
	}

	@Test
	void testEqualsObject() {
		Bunny bunny2 = bunny;
		assertTrue(bunny==bunny2);
		
		Bunny bunny3 = new Bunny(new Coordinate(8,2));
		assertFalse(bunny==bunny3);
		
		bunny3.setCoord(0,1);
		assertFalse(bunny==bunny3);
		assertTrue(bunny.equals(bunny3));
		assertFalse(bunny2==bunny3);
		assertTrue(bunny2.equals(bunny3));
	}

	@Test
	void testToString() {
		assertEquals("b",bunny.toString());
	}
}