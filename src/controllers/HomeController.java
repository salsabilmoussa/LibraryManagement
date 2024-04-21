package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {
		
        private Parent fxml;
        
        @FXML
	    private AnchorPane root;
        
	    @FXML
	    void accueil(MouseEvent event) {
	    	try {
				fxml= FXMLLoader.load(getClass().getResource("/interfaces/Accueil.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void lecteur(MouseEvent event) {
	    	try {
				fxml= FXMLLoader.load(getClass().getResource("/interfaces/Lecteur.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	    
	    @FXML
	    void retour(MouseEvent event) {
	    	try {
				fxml= FXMLLoader.load(getClass().getResource("/interfaces/retour.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }

	    @FXML
	    void livre(MouseEvent event) {
	    	try {
				fxml= FXMLLoader.load(getClass().getResource("/interfaces/Livre.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	    
	    @FXML
	    void emprunt(MouseEvent event) {
	    	try {
				fxml= FXMLLoader.load(getClass().getResource("/interfaces/Emprunt.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}

}
