package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Hole;
import edu.uoc.uocnejitos.model.Symbol;

class HoleTest {

	private Hole hole;
	
	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public HoleTest(){
		hole = new Hole(new Coordinate(0,1));
	}
	
	@Test
	void testGetCoord() {
		Coordinate coord = hole.getCoord();
		assertEquals(0,coord.getRow());
		assertEquals(1,coord.getColumn());

	}

	@Test
	void testSetCoordCoordinate() {
		hole.setCoord(new Coordinate(4,2));
		assertEquals(4,hole.getCoord().getRow());
		assertEquals(2,hole.getCoord().getColumn());
	}

	@Test
	void testSetCoordIntInt() {
		hole.setCoord(10,1);
		assertEquals(10,hole.getCoord().getRow());
		assertEquals(1,hole.getCoord().getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.HOLE,hole.getSymbol());
	}

	@Test
	void testSetSymbol() {
		hole.setSymbol(Symbol.GRASS);
		assertEquals(Symbol.GRASS,hole.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Hole hole2 = hole;
		assertTrue(hole==hole2);
		
		Hole hole3 = new Hole(new Coordinate(8,2));
		assertFalse(hole==hole3);
		
		hole3.setCoord(0,1);
		assertFalse(hole==hole3);
		assertTrue(hole.equals(hole3));
		assertFalse(hole2==hole3);
		assertTrue(hole2.equals(hole3));
	}

	@Test
	void testToString() {
		assertEquals("H",hole.toString());
	}
}