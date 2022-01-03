package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Mushroom;
import edu.uoc.uocnejitos.model.Symbol;

class MushroomTest {
	
	private Mushroom mushroom;

	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public MushroomTest() {
		mushroom = new Mushroom(new Coordinate(0,1));
	}
	
	@Test
	void testGetCoord() {
		Coordinate coord = mushroom.getCoord();
		assertEquals(0,coord.getRow());
		assertEquals(1,coord.getColumn());
	}

	@Test
	void testSetCoordCoordinate() {
		mushroom.setCoord(new Coordinate(2,4));
		assertEquals(2,mushroom.getCoord().getRow());
		assertEquals(4,mushroom.getCoord().getColumn());
	}

	@Test
	void testSetCoordIntInt() {
		mushroom.setCoord(8,6);
		assertEquals(8,mushroom.getCoord().getRow());
		assertEquals(6,mushroom.getCoord().getColumn());
	}

	@Test
	void testGetSymbol() {
		assertEquals(Symbol.MUSHROOM,mushroom.getSymbol());
	}

	@Test
	void testSetSymbol() {
		mushroom.setSymbol(Symbol.BUNNY_BROWN);
		assertEquals(Symbol.BUNNY_BROWN,mushroom.getSymbol());
	}

	@Test
	void testEqualsObject() {
		Mushroom mushroom2 = mushroom;
		assertTrue(mushroom==mushroom2);
		
		Mushroom mushroom3 = new Mushroom(new Coordinate(8,2));
		assertFalse(mushroom==mushroom3);
		
		mushroom3.setCoord(0,1);
		assertFalse(mushroom==mushroom3);
		assertTrue(mushroom.equals(mushroom3));
		assertFalse(mushroom2==mushroom3);
		assertTrue(mushroom2.equals(mushroom3));
		
	}

	@Test
	void testToString() {
		assertEquals("M",mushroom.toString());
	}

}
