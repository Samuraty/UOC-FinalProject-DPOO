package edu.uoc.uocnejitos.model;

public enum Symbol {
	
	BUNNY_WHITE('w', "bunny-white.png"),
	BUNNY_WHITE_HOLE('W', "bunny-white-hole.png"),
	BUNNY_GRAY('g', "bunny-gray.png"),
	BUNNY_GRAY_HOLE('G', "bunny-gray-hole.png"),
	BUNNY_BROWN('b', "bunny-brown.png"),
	BUNNY_BROWN_HOLE('B', "bunny-brown-hole.png"),
	FOX_HEAD_UP('^', "fox-head-up.png"),
	FOX_TAIL_UP('⊥', "fox-tail-up.png"),
	FOX_HEAD_RIGHT('>', "fox-head-right.png"),
	FOX_TAIL_RIGHT('⊢', "fox-tail-right.png"),
	FOX_HEAD_DOWN('V', "fox-head-down.png"),
	FOX_TAIL_DOWN('T', "fox-tail-down.png"),
	FOX_HEAD_LEFT('<', "fox-head-left.png"),
	FOX_TAIL_LEFT('⊣', "fox-tail-left.png"),
	MUSHROOM('M', "mushroom.png"),
	HOLE('H', "hole.png"),
	GRASS('#', "grass.png");
	
	private final char ascii;
	private final String imageSrc;
	
	private Symbol(char ascii, String imageSrc) {
		this.ascii = ascii;
		this.imageSrc = imageSrc;
	}
	
	public static Symbol getName(char ascii) {
		Symbol symb = null;
		for(Symbol type : Symbol.values()) {
			if(type.getAscii() == ascii) 
				symb = type;
		}
		return symb;
	}
	
	public char getAscii() {
		return ascii;
	}
	
	public String getImageSrc() {
		return imageSrc;
	}
	
	public String toString() {
		return String.valueOf(ascii);
	}
	
}
