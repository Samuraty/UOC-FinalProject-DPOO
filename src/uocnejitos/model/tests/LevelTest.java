package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Hole;
import edu.uoc.uocnejitos.model.Level;
import edu.uoc.uocnejitos.model.LevelDifficulty;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Piece;
import edu.uoc.uocnejitos.model.Symbol;


class LevelTest {

	private Level level;
	
	/*
	 * This is the constructor of this class. It is always called before each test.
	 * Hence, you should think that it has @beforeEach annotation.
	 */
	public LevelTest() {
		try {
			level = new Level("levels-tests/level1.txt");			
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testLevel failed");
		}
	}
	
	@Test
	void testLevel() {
		try {
			level = new Level("levels-tests/level6.txt");
			level = new Level("levels-tests/level7.txt");
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testLevel failed");
		}
		LevelException ex = assertThrows(LevelException.class, () -> new Level("levels-tests/level3.txt"));
		assertEquals(LevelException.ERROR_NO_BUNNIES,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> new Level("levels-tests/level4.txt"));
		assertEquals(LevelException.ERROR_NO_HOLES,ex.getMessage());	
		
		ex = assertThrows(LevelException.class, () -> new Level("levels-tests/level5.txt"));
		assertEquals(LevelException.ERROR_MORE_BUNNIES_THAN_HOLES,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> new Level("levels-tests/level8.txt"));
		assertEquals(LevelException.ERROR_SIZE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> new Level("levels-tests/level9.txt"));
		assertEquals(LevelException.ERROR_MIN_MOVES,ex.getMessage());		
	}

	@Test
	void testGetSize() {
		assertEquals(5,level.getSize());
	}

	@Test
	void testGetDifficulty() {
		assertEquals(LevelDifficulty.STARTER,level.getDifficulty());
		try {
			level = new Level("levels-tests/level2.txt");
			assertEquals(LevelDifficulty.WIZARD,level.getDifficulty());
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testGetDifficulty failed");
		}
	}

	@Test
	void testGetMinMoves() {
		assertEquals(1,level.getMinMoves());
		try {
			level = new Level("levels-tests/level2.txt");
			assertEquals(16,level.getMinMoves());
		} catch (FileNotFoundException | IllegalArgumentException | LevelException e) {			
			e.printStackTrace();
			fail("testGetMinMoves failed");
		}
	}

	@Test
	void testIsObstacleCoordinate() {
		assertTrue(level.isObstacle(new Coordinate(0,0))); //bunny
		assertTrue(level.isObstacle(new Coordinate(1,1))); //mushroom
		assertTrue(level.isObstacle(new Coordinate(4,0))); //fox head
		assertTrue(level.isObstacle(new Coordinate(4,1))); //fox tail
		assertFalse(level.isObstacle(new Coordinate(3,1))); //hole
		assertFalse(level.isObstacle(new Coordinate(2,0))); //grass
	}

	@Test
	void testIsObstacleIntInt() {
		assertTrue(level.isObstacle(0,0)); //bunny
		assertTrue(level.isObstacle(1,1)); //mushroom
		assertTrue(level.isObstacle(4,0)); //fox head
		assertTrue(level.isObstacle(4,1)); //fox tail
		assertFalse(level.isObstacle(3,1)); //hole
		assertFalse(level.isObstacle(2,0)); //grass
	}

	@Test
	void testGetBoard1D() {
		StringBuilder str = new StringBuilder();
	    
	    level.getBoard1D().stream().forEach(c -> {
	    	str.append(c.toString());
	    });
	    
	    assertEquals("BH###"+"#M###"+"#bMH^"+"#H##"+"⊥"+"<"+"⊣"+"###",str.toString());
	}

	@Test
	void testGetBoard2D() {
		char[][] level1 = {
		                     {'B','H','#','#','#'},
		                     {'#','M','#','#','#'},
		                     {'#','b','M','H','^'},
		                     {'#','H','#','#','⊥'},
		                     {'<','⊣','#','#','#'}
						  };
			
		Piece[][] board = level.getBoard2D();
		
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				assertTrue(board[i][j].getSymbol().getAscii() == level1[i][j]);
			}
		}	
	}

	@Test
	void testGetPieceCoordinate() {
		try {
			assertTrue("B".equals(level.getPiece(new Coordinate(0,0)).toString())); //brown bunny in a hole
			assertTrue("M".equals(level.getPiece(new Coordinate(1,1)).toString())); //mushroom
			assertTrue("<".equals(level.getPiece(new Coordinate(4,0)).toString())); //fox head
			assertTrue(level.getPiece(new Coordinate(4,1)).toString().equals("⊣")); //fox tail
			assertTrue("H".equals(level.getPiece(new Coordinate(3,1)).toString())); //hole
			assertTrue("#".equals(level.getPiece(new Coordinate(2,0)).toString())); //grass
		} catch (LevelException e) {	
			e.printStackTrace();
			fail("testGetPieceIntInt failed");
		}
		
		LevelException ex = assertThrows(LevelException.class, () -> level.getPiece(new Coordinate(-1,0)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(new Coordinate(0,-1)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(new Coordinate(level.getSize(),0)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(new Coordinate(0,level.getSize())));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());		
	}

	@Test
	void testGetPieceIntInt() {
		try {
			assertTrue("B".equals(level.getPiece(0,0).toString())); //brown bunny in a hole
			assertTrue("M".equals(level.getPiece(1,1).toString())); //mushroom
			assertTrue("<".equals(level.getPiece(4,0).toString())); //fox head
			assertTrue(level.getPiece(4,1).toString().equals("⊣")); //fox tail
			assertTrue("H".equals(level.getPiece(3,1).toString())); //hole
			assertTrue("#".equals(level.getPiece(2,0).toString())); //grass
		} catch (LevelException e) {	
			e.printStackTrace();
			fail("testGetPieceIntInt failed");
		}
		
		LevelException ex = assertThrows(LevelException.class, () -> level.getPiece(-1,0));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(0,-1));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(level.getSize(),0));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.getPiece(0,level.getSize()));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());		
	}

	@Test
	void testSetPiece() {
		Hole hole = null;
		Coordinate coord = new Coordinate(0,0);
		try{
			hole = new Hole(coord);
			level.setPiece(coord,hole);
			assertEquals(hole,level.getPiece(0,0));
			assertEquals(hole,level.getPiece(coord));
			assertEquals(0,hole.getCoord().getRow());
			assertEquals(0,hole.getCoord().getColumn());
			assertEquals(coord,hole.getCoord());
		} catch (LevelException e) {			
			e.printStackTrace();
			fail("testSetPiece failed");
		}
		
		
		LevelException ex = assertThrows(LevelException.class, () -> level.setPiece(new Coordinate(-1,0), new Hole(coord)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.setPiece(new Coordinate(0,-1),new Hole(coord)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.setPiece(new Coordinate(level.getSize(),0),new Hole(coord)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());
		
		ex = assertThrows(LevelException.class, () -> level.setPiece(new Coordinate(0,level.getSize()),new Hole(coord)));
		assertEquals(LevelException.ERROR_COORDINATE,ex.getMessage());		
	}

	@Test
	void testIsFinished() {
		assertFalse(level.isFinished());		
		try {
			level.getPiece(2, 1).setSymbol(Symbol.BUNNY_BROWN_HOLE);			
			assertTrue(level.isFinished());
		} catch (LevelException e) {
			e.printStackTrace();
			fail("testIsFinished failed");
		}
	}

	@Test
	void testToString() {
		assertEquals("12345a|BH###"+"b|#M###"+"c|#bMH^"+"d|#H##"+"⊥"+"e|<"+"⊣"+"###",level.toString().replaceAll("\n|\r\n", "").trim());
		assertEquals('\n',level.toString().charAt(level.toString().length()-1));
	}
}