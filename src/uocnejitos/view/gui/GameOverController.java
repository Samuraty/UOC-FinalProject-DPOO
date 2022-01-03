package edu.uoc.uocnejitos.view.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {

	@FXML
	private Button buttonStart;
	
	@FXML
	public void back() {		
		try{
			GuiApp.main.createView("Welcome.fxml");
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@FXML
	public void exit() {		
		System.exit(0);		
	}	
}