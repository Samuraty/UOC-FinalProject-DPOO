package edu.uoc.uocnejitos.view.gui;

import edu.uoc.uocnejitos.controller.Game;
import edu.uoc.uocnejitos.model.Coordinate;
import edu.uoc.uocnejitos.model.Grass;
import edu.uoc.uocnejitos.model.Hole;
import edu.uoc.uocnejitos.model.LevelException;
import edu.uoc.uocnejitos.model.Movable;
import edu.uoc.uocnejitos.model.Move;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PlayController {

	public static final int CANVAS_WIDTH = 1024;
	public static final int CANVAS_HEIGHT = 640;
	
	private Game game;
		
	private List<Coordinate> move;
	
	@FXML
	private Pane canvas;
		
	@FXML
	Label uiDifficulty;
	
	@FXML
	Label uiLevel;
	
	@FXML
	Label uiMoves;
	
	private Alert alert;
	
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
	 * @throws FileNotFoundException When there is a problem opening the configuration file
	 * @throws LevelException When there is a level exception/problem.	 
     */
    @FXML
    private void initialize() throws FileNotFoundException, LevelException {  
    	move = new ArrayList<Coordinate>();
    	game = new Game("levels/");
    	alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("Congratulations!");
    	if(game.nextLevel()) update();    
    }
    
    /**
     * Updates the status of the level (i.e. the flow of the game). It also paints the game in the GUI.
     * @throws FileNotFoundException When there is a problem opening the configuration file.
     * @throws LevelException When there is a level exception/problem.
     */
    private void update() throws FileNotFoundException, LevelException{    	
    	if(!game.isLevelSolved()) {
    		paint();    		
    	}else {
    		//Level solved, then we show an alert (popup) window.
    		uiMoves.setText(game.getNumMoves()+"/"+game.getMinMoves());
    		alert.setContentText("You have solved Level "+game.getNumLevel()+"!!");
    		alert.showAndWait();    		
    		if(!game.nextLevel()) {
    			try {
    				GuiApp.main.createView("GameOver.fxml");
    			} catch (IOException e) {
    				e.printStackTrace();
    				System.exit(2);
    			}
    		}else {
    			paint();
    		}
    	}    	
    }	   	
    /**
     * Paints the level in the GUI.
     */
    private void paint() {
    	ObservableList<Node> nodeList =  FXCollections.observableArrayList();        
    	canvas.getChildren().clear();
    	
    	uiDifficulty.setText(game.getDifficulty().toString());
    	
    	uiLevel.setText("Level "+game.getNumLevel());
    	
    	uiMoves.setText(game.getNumMoves()+"/"+game.getMinMoves());
    	    	
    	for(var piece : game.getPieces()){
    		StackPane sprite = new StackPane();    		
    		ImageView spriteImage = new ImageView(new Image("file:img/"+piece.getSymbol().getImageSrc()));    		
    		spriteImage.setFitWidth(120);
    		spriteImage.setFitHeight(120);
    		sprite.getChildren().add(spriteImage);
    		sprite.setTranslateX(125*piece.getCoord().getColumn());
    		sprite.setTranslateY(125*piece.getCoord().getRow());
    		
    		if(piece instanceof Movable) {    			
    			sprite.getStyleClass().add("piece-movable");    			
    		}
    		
    		if(piece instanceof Grass || piece instanceof Hole) {    			
    			sprite.getStyleClass().add("piece-movable-destination");    			
    		}
    			    		
    		nodeList.addAll(sprite);
    		
    		sprite.setOnMouseClicked(e ->{    			
    			onClick(piece.getCoord());
    		}); 		
    		
    	}
    	
    	canvas.getChildren().addAll(nodeList);
    	
    }
    
    /**
     * Manages the click event, when a coordinate is clicked. When 2 coordinates has been clicked, then it tries to update the status of the level by moving the piece.
     * @param coord Coordinate object that corresponds to the cell of the board that has been clicked.
     */
    private void onClick(Coordinate coord){
    	Optional<Node> nodeOptional = canvas.getChildren().stream().filter(n -> n.getTranslateX()/125==coord.getColumn() && n.getTranslateY()/125 == coord.getRow()).findFirst(); //125 
    	Node node = nodeOptional.get();
    	node.getStyleClass().add("clicked");
    		
    	move.add(coord);
    	if(move.size()==2) {    		
    		game.move(new Move(new Coordinate(move.get(0).getRow(), move.get(0).getColumn()),new Coordinate(move.get(1).getRow(), move.get(1).getColumn())));    		
    		try {
				update();
				move.clear();
			} catch (LevelException | FileNotFoundException e) {				
				e.printStackTrace();			
			}
    	}
    }
    
    /**
     * Restarts the current level when the Reload button is clicked.
     * @throws LevelException 
     * @throws FileNotFoundException 
     */
    public void reloadLevel() throws FileNotFoundException, LevelException{
		game.reload();
		update();
    }
}