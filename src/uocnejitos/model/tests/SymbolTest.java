package edu.uoc.uocnejitos.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uoc.uocnejitos.model.Symbol;

class SymbolTest {

	@Test
	void testGetName() {
		assertEquals(Symbol.BUNNY_BROWN, Symbol.getName('b'));
		assertEquals(Symbol.BUNNY_BROWN_HOLE, Symbol.getName('B'));
		assertEquals(Symbol.BUNNY_GRAY, Symbol.getName('g'));
		assertEquals(Symbol.BUNNY_GRAY_HOLE, Symbol.getName('G'));
		assertEquals(Symbol.BUNNY_WHITE, Symbol.getName('w'));
		assertEquals(Symbol.BUNNY_WHITE_HOLE, Symbol.getName('W'));
		assertEquals(Symbol.FOX_HEAD_DOWN, Symbol.getName('V'));
		assertEquals(Symbol.FOX_HEAD_LEFT, Symbol.getName('<'));
		assertEquals(Symbol.FOX_HEAD_RIGHT, Symbol.getName('>'));
		assertEquals(Symbol.FOX_HEAD_UP, Symbol.getName('^'));
		assertEquals(Symbol.FOX_TAIL_DOWN, Symbol.getName('T'));
		assertEquals(Symbol.FOX_TAIL_LEFT, Symbol.getName('⊣'));
		assertEquals(Symbol.FOX_TAIL_RIGHT, Symbol.getName('⊢'));
		assertEquals(Symbol.FOX_TAIL_UP, Symbol.getName('⊥'));
		assertEquals(Symbol.GRASS, Symbol.getName('#'));
		assertEquals(Symbol.HOLE, Symbol.getName('H'));
		assertEquals(Symbol.MUSHROOM, Symbol.getName('M'));
	}

	@Test
	void testGetAscii() {
		assertEquals('b',Symbol.BUNNY_BROWN.getAscii());
		assertEquals('B', Symbol.BUNNY_BROWN_HOLE.getAscii());
		assertEquals('g',Symbol.BUNNY_GRAY.getAscii());
		assertEquals('G',Symbol.BUNNY_GRAY_HOLE.getAscii());
		assertEquals('w',Symbol.BUNNY_WHITE.getAscii());
		assertEquals('W',Symbol.BUNNY_WHITE_HOLE.getAscii());
		assertEquals('V',Symbol.FOX_HEAD_DOWN.getAscii());
		assertEquals('<',Symbol.FOX_HEAD_LEFT.getAscii());
		assertEquals('>',Symbol.FOX_HEAD_RIGHT.getAscii());
		assertEquals('^',Symbol.FOX_HEAD_UP.getAscii());
		assertEquals('T',Symbol.FOX_TAIL_DOWN.getAscii());
		assertEquals('⊣',Symbol.FOX_TAIL_LEFT.getAscii());
		assertEquals('⊢',Symbol.FOX_TAIL_RIGHT.getAscii());
		assertEquals('⊥',Symbol.FOX_TAIL_UP.getAscii());
		assertEquals('#',Symbol.GRASS.getAscii());
		assertEquals('H',Symbol.HOLE.getAscii());
		assertEquals('M',Symbol.MUSHROOM.getAscii());
	}

	@Test
	void testGetImageSrc() {
		assertEquals("bunny-brown.png",Symbol.BUNNY_BROWN.getImageSrc());
		assertEquals("bunny-brown-hole.png", Symbol.BUNNY_BROWN_HOLE.getImageSrc());
		assertEquals("bunny-gray.png",Symbol.BUNNY_GRAY.getImageSrc());
		assertEquals("bunny-gray-hole.png",Symbol.BUNNY_GRAY_HOLE.getImageSrc());
		assertEquals("bunny-white.png",Symbol.BUNNY_WHITE.getImageSrc());
		assertEquals("bunny-white-hole.png",Symbol.BUNNY_WHITE_HOLE.getImageSrc());
		assertEquals("fox-head-down.png",Symbol.FOX_HEAD_DOWN.getImageSrc());
		assertEquals("fox-head-left.png",Symbol.FOX_HEAD_LEFT.getImageSrc());
		assertEquals("fox-head-right.png",Symbol.FOX_HEAD_RIGHT.getImageSrc());
		assertEquals("fox-head-up.png",Symbol.FOX_HEAD_UP.getImageSrc());
		assertEquals("fox-tail-down.png",Symbol.FOX_TAIL_DOWN.getImageSrc());
		assertEquals("fox-tail-left.png",Symbol.FOX_TAIL_LEFT.getImageSrc());
		assertEquals("fox-tail-right.png",Symbol.FOX_TAIL_RIGHT.getImageSrc());
		assertEquals("fox-tail-up.png",Symbol.FOX_TAIL_UP.getImageSrc());
		assertEquals("grass.png",Symbol.GRASS.getImageSrc());
		assertEquals("hole.png",Symbol.HOLE.getImageSrc());
		assertEquals("mushroom.png",Symbol.MUSHROOM.getImageSrc());
	}

	@Test
	void testToString() {
		assertEquals("b",Symbol.BUNNY_BROWN.toString());
		assertEquals("B", Symbol.BUNNY_BROWN_HOLE.toString());
		assertEquals("g",Symbol.BUNNY_GRAY.toString());
		assertEquals("G",Symbol.BUNNY_GRAY_HOLE.toString());
		assertEquals("w",Symbol.BUNNY_WHITE.toString());
		assertEquals("W",Symbol.BUNNY_WHITE_HOLE.toString());
		assertEquals("V",Symbol.FOX_HEAD_DOWN.toString());
		assertEquals("<",Symbol.FOX_HEAD_LEFT.toString());
		assertEquals(">",Symbol.FOX_HEAD_RIGHT.toString());
		assertEquals("^",Symbol.FOX_HEAD_UP.toString());
		assertEquals("T",Symbol.FOX_TAIL_DOWN.toString());
		assertEquals("⊣",Symbol.FOX_TAIL_LEFT.toString());
		assertEquals("⊢",Symbol.FOX_TAIL_RIGHT.toString());
		assertEquals("⊥",Symbol.FOX_TAIL_UP.toString());
		assertEquals("#",Symbol.GRASS.toString());
		assertEquals("H",Symbol.HOLE.toString());
		assertEquals("M",Symbol.MUSHROOM.toString());
	}

}
