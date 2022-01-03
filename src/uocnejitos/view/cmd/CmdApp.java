package edu.uoc.uocnejitos.view.cmd;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uoc.uocnejitos.controller.Game;
import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Move;


public class CmdApp {

	/**
	 * Game object that allows to manage the game.
	 */
	Game game;	
	
	/** Initializes a new game with the folder which contains the levels' configuration files and with the game mode "turns".	  
	 * @throws FileNotFoundException When the level's configuration file is not found. 
	 * @throws LevelException When there is a level exception/problem.	
	 */
	public CmdApp() throws FileNotFoundException, LevelException {
		this.game = new Game("levels/");
	}
	
	/**
	 * Manages the idle process of the game.
	 * @throws FileNotFoundException When the level's configuration file is not found.
	 * @throws LevelException When there is a level exception/problem. 
	 */
	public void launchGame() throws FileNotFoundException, LevelException {
		Scanner sc = new Scanner(System.in);
		Coordinate coordStarting, coordEnding;
		
		while(game.nextLevel()) {			
			System.out.println("LEVEL "+game.getNumLevel());			
			System.out.println();
			// print board and keep accepting moves until game is over
			while (!game.isLevelSolved()) {
				System.out.println("Moves: "+game.getNumMoves()+"/"+game.getMinMoves());
				System.out.println(game.getBoardText());
				System.out.println(" | Enter bunny/fox's position (row,col), e.g. a3: ");
				coordStarting = coordinateFromInput(sc.nextLine());	
			
				if(coordStarting == null) {
					System.out.println("[ERROR] Incorrect input format!!");
				}else {
					System.out.println("Enter destination position square (row,col): ");
					coordEnding = coordinateFromInput(sc.nextLine());
			
					if(coordEnding == null) {
						System.out.println("[ERROR] Incorrect input format!!");
					}else if(!game.move(new Move(coordStarting, coordEnding))) {;
						System.out.println("[ERROR] Illegal move!!");		
					}
				}
				System.out.println();
			}			
			
			System.out.println(game.getBoardText());
			System.out.println("Congrats!! You have solved Level "+game.getNumLevel()+" with "+game.getNumMoves()+" moves (min:"+game.getMinMoves()+")!!");
			System.out.println("Press enter to continue...");				
			sc.nextLine();						
		}
		
		sc.close();		
	}
	
	/**
	 * Transform an user input (String) in the corresponding Coordinate object.
	 * @param input User input (captured from the keyboard) with the format rowColumn, e.g. "a1".
	 * @return Coordinate object that corresponds to the user input.
	 */
	private Coordinate coordinateFromInput(String input){
		char x = input.toLowerCase().charAt(1);
		char y = input.toLowerCase().charAt(0);
		
		if(input.length()!=2 
				|| 
				!Character.isLetter(y) //no letter
				||
				!(y >= 'a' && y < 97 +  game.getBoardSize())
						||
				!Character.isDigit(x) //no number
				||
				!(x >= 49 && x < 49 +  game.getBoardSize())
				) return null;
		
		return new Coordinate(((int)y - 97), ((int)x-49));
	}
	
	/**
	 * Main method: entry point of the program.
	 * @param args This parameter is not needed.
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		try {
			CmdApp cmd = new CmdApp();		
			cmd.launchGame();			
		} catch (FileNotFoundException | LevelException e) {			
			e.printStackTrace();
		}
		
		System.out.println("Finishing... bye!!");
	}	
}
