package edu.uoc.uocnejitos.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** 
 * Level class.
 * @author David García Solórzano
 * @version 1.0  
 */
public class Level {
	/**
	 * Size of the board. SIZExSIZE.
	 */
	private final int SIZE;
	/**
	 * Level's difficulty
	 */
	private final LevelDifficulty DIFFICULTY;
	/**
	 * Number minimum of moves that are required in order to beat the level/challenge.
	 */
	private final int MIN_MOVES;
	/**
	 * List of pieces that are on the board.	
	 */
	private List<Piece> board;
	
	/**
	 * Constructor
	 * @param fileName Name of the configuration file which has all the information of the level.
	 * @throws FileNotFoundException When it is impossible to open the configuration file.
	 * @throws IllegalArgumentException When an error of this type happens, e.g. NumberFormatException.
	 * @throws LevelException When there is an error with the information of configuration file, e.g. incorrect size or min moves, no bunnies, no holes, #bunnies&lt;#holes
	 */
	public Level(String fileName) throws FileNotFoundException, IllegalArgumentException, LevelException {
		String line = null;
		long numBunnies = 0, numHoles = 0;
		int row = 0, column = 0;
		char pieceSymbol = ' ';
		Piece piece = null;
		
		
		try(Scanner sc = new Scanner(new File(fileName))){
			SIZE = Integer.parseInt(sc.nextLine());
			
			if(getSize()<3)
				throw new LevelException(LevelException.ERROR_SIZE);
			
			DIFFICULTY = LevelDifficulty.valueOf(sc.nextLine().toUpperCase());
			
			MIN_MOVES = Integer.parseInt(sc.nextLine());
			
			if(getMinMoves()<1)
				throw new LevelException(LevelException.ERROR_MIN_MOVES);
			
			board = new ArrayList<Piece>(SIZE*SIZE);
			
			//We populate the whole list with Grass pieces.			
			for(int i = 0; i < getSize(); i++) {
				for(int j = 0; j < getSize(); j++) {
					board.add(new Grass(new Coordinate(i, j)));
				}
			}			
			
			
			while(sc.hasNext()) {
				line = sc.nextLine();
				pieceSymbol= line.charAt(0);
				
				if(pieceSymbol != 'b' && pieceSymbol != 'B'
						&&
						pieceSymbol != 'w' && pieceSymbol != 'W'
						&&
						pieceSymbol != 'g' && pieceSymbol != 'g') { 
					pieceSymbol = Character.toUpperCase(pieceSymbol); 
				}
					
				row = calculateRow(line.toLowerCase().charAt(1)); 
				column = calculateColumn(line.toLowerCase().charAt(2));
				
				switch(Symbol.getName(pieceSymbol)) {
					case HOLE:
						piece = new Hole(new Coordinate(row,column));
						break;
					case MUSHROOM:
						piece = new Mushroom(new Coordinate(row,column));
						break;
					case BUNNY_WHITE:						
					case BUNNY_WHITE_HOLE:
					case BUNNY_BROWN:
					case BUNNY_BROWN_HOLE:
					case BUNNY_GRAY:
					case BUNNY_GRAY_HOLE:
						piece = new Bunny(new Coordinate(row,column),Symbol.getName(pieceSymbol));
						break;
					case FOX_HEAD_UP:
					case FOX_HEAD_DOWN:
					case FOX_HEAD_LEFT:
					case FOX_HEAD_RIGHT:						
						String direction = Symbol.getName(pieceSymbol).getImageSrc().split("-")[2];
						direction = direction.substring(0,direction.indexOf(".")).toUpperCase();
						FoxHead fox = new FoxHead(new Coordinate(row,column),FoxDirection.valueOf(direction));
						piece = fox;
						FoxTail tail = fox.getTail();						
						board.set((tail.getCoord().getRow()*getSize())+tail.getCoord().getColumn(),tail);
						break;
				default:
					break;					
				}			
				
				board.set((row*getSize())+column,piece);
			}
						
			numBunnies = getBoard1D().stream().filter(p -> p instanceof Bunny).count();
			numHoles = getBoard1D().stream().filter(p -> p instanceof Hole || p.getSymbol().getImageSrc().contains("-hole")).count();
			
			if(numBunnies==0)		
			 throw new LevelException(LevelException.ERROR_NO_BUNNIES);
			
			if(numHoles==0)
				throw new LevelException(LevelException.ERROR_NO_HOLES);
			
			if(numHoles<numBunnies) throw new LevelException(LevelException.ERROR_MORE_BUNNIES_THAN_HOLES);
			             
		}catch(FileNotFoundException e) {
			throw e;
		}
		
	}
	
	private int calculateColumn(char columnChar) throws LevelException {
		int ascii = (int) columnChar - 49;
		if(ascii < 0 || ascii >= SIZE) {
			throw new LevelException(LevelException.ERROR_INCORRECT_COLUMN);
		}
		return ascii;
	}
	
	private int calculateRow(char letter) throws LevelException {
		int ascii = (int) letter - 97;
		if(ascii < 0 || ascii >= SIZE) {
			throw new LevelException(LevelException.ERROR_INCORRECT_ROW);
		}
		return ascii;
	}
	
	public List<Piece> getBoard1D() {
		return board;
	}
	
	public Piece[][] getBoard2D() {
		Piece[][] board2D = new Piece[SIZE][SIZE]; 
		int element = 0;
		
		for(int i = 0; i < this.SIZE; i++) {
			for(int j = 0; j < this.SIZE; j++) {
				board2D[i][j] = board.get(element);
				element++;
			}
		}
		return board2D;
	}
	
	public LevelDifficulty getDifficulty() {
		return DIFFICULTY;
	}
	
	public int getMinMoves() {
		return MIN_MOVES;
	}
	
	public Piece getPiece(int row, int column) throws LevelException{
		if(!validatePosition(row, column)) {
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
		
		Piece piece = null;
		for(int i = 0; i < this.board.size(); i++) {
			if(column == board.get(i).getCoord().getColumn() && row == board.get(i).getCoord().getRow()) {
				piece = board.get(i);
			}
		}
		return piece;	
	}
	
	public Piece getPiece(Coordinate coord) throws LevelException{
		if(!validatePosition(coord.getRow(), coord.getColumn())) {
			throw new LevelException(LevelException.ERROR_COORDINATE);
		}
		
		Piece piece = null;
		for(int i = 0; i < this.board.size(); i++) {
			if(coord.getColumn() == board.get(i).getCoord().getColumn() && coord.getRow() == board.get(i).getCoord().getRow()) {
				piece = board.get(i);
			}
		}
		return piece;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public boolean isFinished() {
		for(int i = 0; i < this.board.size(); i++) {
			if(board.get(i).getSymbol() == Symbol.BUNNY_WHITE || board.get(i).getSymbol() == Symbol.BUNNY_GRAY || board.get(i).getSymbol() == Symbol.BUNNY_BROWN) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isObstacle(int row, int column){
		try {
			if(getPiece(row, column) instanceof Mushroom || getPiece(row, column) instanceof Fox || getPiece(row, column) instanceof Bunny) {
				return true;
			}
			return false;
		} catch (LevelException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isObstacle(Coordinate coord){
		try {
			if(getPiece(coord) instanceof Mushroom || getPiece(coord) instanceof Fox || getPiece(coord) instanceof Bunny) {
				return true;
			}
			return false;
		} catch (LevelException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setPiece(Coordinate coord, Piece piece) throws LevelException{
		Collections.replaceAll(board, getPiece(coord), piece);
	}
	
	public String toString() {
		String response = "  ";
		int element = 0;
		
		for(int i = 1; i <= SIZE; i++) {	
			response += "" + i;
		};
		
		for(int i = 1; i <= SIZE; i++) {
			response += "\n" + (char) (96 + i) + "|";
			for(int j = 1; j <= SIZE; j++) {
				response += "" + board.get(element).getSymbol().getAscii();
				element++;
			}
		};
		response += "\n";
		return response;
	}
	
	private boolean validatePosition(int row, int column) {
		if(row < 0 || row >= SIZE || column < 0 || column >= SIZE) {
			return false;
		}
		return true;
	}
}