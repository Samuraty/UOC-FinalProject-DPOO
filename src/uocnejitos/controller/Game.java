package edu.uoc.uocnejitos.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import edu.uoc.uocnejitos.model.Bunny;
import edu.uoc.uocnejitos.model.Level;
import edu.uoc.uocnejitos.model.LevelDifficulty;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Movable;
import edu.uoc.uocnejitos.model.Move;
import edu.uoc.uocnejitos.model.MoveDirection;
import edu.uoc.uocnejitos.model.Piece;

public class Game {
	
	/**
	 * Number of the current level.
	 */
	private int numLevel = 0;
	
	/**
	 * 
	 */
	private int numMoves = 0;
	
	/**
	 * Maximum quantity of levels that the game has.
	 */
	private final int MAX_LEVELS;
	
	
	/**
	 * Folder name where the configuration/level files are.
	 */
	private String fileFolder;
	
	/**
	 * Level object that contains the information of the current level.
	 */
	private Level level;
		
	/**
	 * Constructor
	 * @param fileFolder Folder name where the configuration/level files are.	 
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.	 
	 */
	public Game(String fileFolder) throws FileNotFoundException, LevelException {
		setFileFolder(fileFolder);
		MAX_LEVELS = new File(fileFolder).list().length;		
	}
	
	/**
	 * Setter of the field "fileFolder".
	 * @param fileFolder Folder name where the configuration/level files are. 
	 */
	private void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}
	
	/**
	 * Getter of the field "fileFolder".
	 * @return Value of the field "fileFolder".
	 */
	private String getFileFolder() {
		return fileFolder;
	}
	
	/**
	 * Returns the size of the board of the current level.
	 * @return The size of the board of the current level.
	 */
	public int getBoardSize() {
		return level.getSize();
	}
	
	/**
	 * Returns the difficulty of the current level.
	 * @return The difficulty of the current level.
	 */
	public LevelDifficulty getDifficulty() {
		return level.getDifficulty();
	}
	
	/**
	 * Returns the minimum number of moves that are needed to solve the current level.
	 * @return Minimum number of moves for solving the current level.
	 */
	public int getMinMoves() {
		return level.getMinMoves();
	}
	
	/**
	 * Says if the game is finished (true) or not (false). The game is finished when the field "numLevel" is equals to "MAX_LEVELS".
	 * @return True if there are no more levels and thus the game is finished. Otherwise, false.
	 */
	private boolean isFinished() {
		if (numLevel == MAX_LEVELS) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Getter of the field "numLevel".
	 * @return Value of the field "numLevel".
	 */
	public int getNumLevel() {
		return numLevel;
	}
	
	/**
	 * Checks if there is a new level, sets numMoves = 0 and loads the level. If the game is finished, it returns false.
	 * @return True if there is a next level and it has been loaded.
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
	 */
	public boolean nextLevel() throws FileNotFoundException, LevelException {		
		if (isFinished()) {
			return false;
		}
		setNumMoves(0);
		numLevel += 1;
		loadLevel();
		return true;
	}
	
	/**
	 * Loads a new level by using the value of numLevel attribute. 
	 * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.	 
	 */
	private void loadLevel() throws FileNotFoundException, LevelException{
		level = new Level(getFileFolder()+"level"+numLevel+".txt");				
	}
	
	/**
	 * Checks if the level is solved, i.e. all bunnies are in a hole.
	 * @return true if this level is beaten, otherwise false.
	 */
	public boolean isLevelSolved() {
		return level.isFinished();
	}
	
	/**
	 * Returns a String with the board of the current level in textual format.
	 * @return Text-based board of the current level.
	 */
	public String getBoardText() {
		return level.toString();
	}
		
	/**
     * Makes a move given by parameters. 
     *
     * @param move The object representing the move
     * @return true if the move was successful, false if parameters are invalid or
     * the move was unsuccessful (e.g. there was an exception).
     */
    public boolean move(Move move) {
    	
        if (move == null) return false;
        Piece piece;
		try {
			piece = level.getPiece(move.getStart());			
		} catch (LevelException e) {
			return false;
		}
        if (piece instanceof Movable && ((Movable) piece).move(move.getEnd(), level)) {
        	incNumMoves(move, piece instanceof Bunny);
        	return true;
        }
        
        return false;
    }
    
    /**
     * Returns the board of the current level as an 1-D list.
     * @return 1-D list of Pieces
     */
    public List<Piece> getPieces() {
    	return level.getBoard1D();
    }
    
    /**
     * Setter of the numMoves attribute.
     * @param numMoves New value of the numMoves attribute.
     */
    private void setNumMoves(int numMoves) {
    	this.numMoves = numMoves;
    }
    
    /**
     * Adds/Increases the attribute numMoves according to the distance between the ending and starting coordinates of move as long as the move is valid.
     * @param move Move object that contains the move that is done.
     * @param isBunny Indicates if the move is done by a bunny (true) or a fox (false). If the move is done by a bunny, the increment is always 1. 
     */
    private void incNumMoves(Move move, boolean isBunny) {
		if (isBunny) {
			setNumMoves(numMoves + 1);
		} else {
			if(move.getHorizontalDistance() > 0) {
				setNumMoves(numMoves + move.getHorizontalDistance());
			} else if(move.getHorizontalDistance() < 0) {
				setNumMoves(numMoves - move.getHorizontalDistance());
			} else if(move.getVerticalDistance() > 0) {
				setNumMoves(numMoves + move.getVerticalDistance());
			} else if(move.getVerticalDistance() < 0) {
				setNumMoves(numMoves - move.getVerticalDistance());
			}
		}
    }
    
    /**
     * Getter of numMoves attribute. 
     * @return The value of numMoves attribute.
     */
    public int getNumMoves() {
    	return numMoves;
    }
    
    /**
     * Reload the current level, i.e. assigns 0 to numMoves and load the level again.
     * @throws FileNotFoundException When there is a problem opening the configuration file.
	 * @throws LevelException When there is a level exception/problem.
     */
    public void reload() throws FileNotFoundException, LevelException {
    	setNumMoves(0);
    	loadLevel();
    }
}