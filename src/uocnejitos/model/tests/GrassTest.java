package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Grass;
import edu.uoc.uocnejitos.model.Symbol;

class GrassTest {

	private Grass grass;
	
	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public GrassTest() {
		grass = new Grass(new Coordinate(0,1));
	}
	
	@Test
	void testGetCoord() {
		Coordinate coord = grass.getCoord();
		assertEquals(0,coord.getRow());
		assertEquals(1,coord.getColumn());
	}

	@Test
	void testSetCoordCoordinate() {
		grass.setCoord(new Coordinate(4,2));
		assertEquals(4,grass.getCoord().getRow());
		assertEquals(2,grass.getCoord().getColumn());
	}

	@Test
	void testSetCoordIntInt() {	
		grass.setCoord(11,12);
		assertEquals(11,grass.getCoord().getRow());
		assertEquals(12,grass.getCoord().getColumn());		
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.GRASS,grass.getSymbol());
	}

	@Test
	void testSetSymbol() {
		grass.setSymbol(Symbol.HOLE);
		assertEquals(Symbol.HOLE,grass.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Grass grass2 = grass;
		assertTrue(grass==grass2);
		
		Grass grass3 = new Grass(new Coordinate(8,2));
		assertFalse(grass==grass3);
		
		grass3.setCoord(0,1);
		assertFalse(grass==grass3);	
		assertTrue(grass.equals(grass3));
		assertFalse(grass2==grass3);
		assertTrue(grass2.equals(grass3));
	}

	@Test
	void testToString() {
		assertEquals("#",grass.toString());
	}
}