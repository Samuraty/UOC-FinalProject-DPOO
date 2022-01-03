package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;

class CoordinateTest {

	Coordinate coord;
	
	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public CoordinateTest() {
		coord = new Coordinate(3,2);
	}

	@Test
	void testGetRow() {
		assertEquals(3,coord.getRow());
	}

	@Test
	void testSetRow() {
		coord.setRow(4);
		assertEquals(4,coord.getRow());
		coord.setRow(-34);
		assertEquals(-34,coord.getRow());
	}

	@Test
	void testGetColumn() {
		assertEquals(2,coord.getColumn());
	}

	@Test
	void testSetColumn() {
		coord.setColumn(5);
		assertEquals(5,coord.getColumn());
		coord.setColumn(-15);
		assertEquals(-15,coord.getColumn());
	}

	@Test
	void testEqualsObject() {
		assertTrue(coord.equals(new Coordinate(3,2)));
		assertFalse(coord.equals(new Coordinate(2,3)));
	}

	@Test
	void testCompareTo() {
		assertTrue(coord.compareTo(new Coordinate(3,2)) == 0);
		assertTrue(coord.compareTo(new Coordinate(4,2)) < 0);
		assertTrue(coord.compareTo(new Coordinate(1,2)) > 0);
		
		assertTrue(coord.compareTo(new Coordinate(3,3)) < 0);
		assertTrue(coord.compareTo(new Coordinate(3,0)) > 0);
	}

	@Test
	void testToString() {
		assertTrue(coord.toString().equals("(3,2)"));
	}
}