package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Models.Livre;
import application.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class EmpruntController implements Initializable {
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	public long idliv;
	@FXML
    private Button btnEmprunter;
	
	@FXML
    private ChoiceBox<String> choixLivres;

    @FXML
    private DatePicker dateEmprunt;

    @FXML
    private DatePicker dateRetour;

    @FXML
    private TextField txtAuteur;

    @FXML
    private TextField txtCin;


    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPenom;

    @FXML
    private TextField txtTitre;

    @FXML
    private TextField txtType;
    

    @FXML
    void emprunterLivre() {
    	String cin= txtCin.getText();
    	
    	
    	
    	String sql="INSERT INTO Emprunt (idLivre, cinLecteur, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?)";
    	if(!cin.equals("")) {
    		long valeurCIN = Long.parseLong(cin);
    		LocalDate localDateEmp = dateEmprunt.getValue();
    		Date sqlDate = Date.valueOf(localDateEmp);
    		LocalDate localDateR = dateRetour.getValue();
    		Date sqlDate1 = Date.valueOf(localDateR);
    		try {
				st=cnx.prepareStatement(sql);
				st.setLong(1, idliv);
				st.setLong(2, valeurCIN);
				st.setDate(3, sqlDate);
				st.setDate(4, sqlDate1);
				st.execute();
				Alert alert = new Alert(AlertType.CONFIRMATION,"livre emprunté avec succès!", ButtonType.OK);
				alert.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
    		alert.showAndWait();
    	}


    }
    
    @FXML
    void selectLivre() {
        String livreSelectionne = choixLivres.getValue();

        if (livreSelectionne != null && !livreSelectionne.isEmpty()) {
            String sql = "select * from livre where titre = ?";
            try {
                st = cnx.prepareStatement(sql);
                st.setString(1, livreSelectionne);
                result = st.executeQuery();

                if (result.next()) {
                    txtTitre.setText(result.getString("titre"));
                    txtAuteur.setText(result.getString("auteur"));
                    txtType.setText(result.getString("type"));
                    idliv = result.getLong("code");
                } else {
                    System.out.println("Aucun enregistrement trouvé pour le titre : " + livreSelectionne);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    private void remplirChoiceBoxLivres() {
        ObservableList<String> livres = FXCollections.observableArrayList();

        try {
			st = cnx.prepareStatement("SELECT titre FROM livre");
			result = st.executeQuery();
			 while (result.next()) {
                 String titre = result.getString("titre");
                 livres.add(titre);
             }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        choixLivres.setItems(livres);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataBaseConnection c = new DataBaseConnection();
		cnx= c.connect();
        remplirChoiceBoxLivres();

	}

	

}
