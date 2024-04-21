package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RetourController implements Initializable {

	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	@FXML
    private Button btnRetour;

    @FXML
    private TextField txtCinLec;

    @FXML
    private TextField txtId;

    @FXML
    void retournerLivre() {
    	if(!txtCinLec.getText().equals("")&&!txtId.getText().equals("")) {
    	long valeurCIN = Long.parseLong(txtCinLec.getText());
    	long valeurId = Long.parseLong(txtId.getText());
    	String sql= "delete from emprunt where idLivre= '"+ valeurId+"' and cinLecteur= '"+ valeurCIN +"'";
    	try {
			st=cnx.prepareStatement(sql);
			st.executeUpdate();
			txtCinLec.setText("");
			txtId.setText("");
			Alert alert = new Alert(AlertType.CONFIRMATION,"livre retourné avec succès!", ButtonType.OK);
			alert.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
    		alert.showAndWait();
    	}

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataBaseConnection c = new DataBaseConnection();
		cnx= c.connect();
		
	}

}
