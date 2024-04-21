package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Models.Lecteur;
import Models.Livre;
import application.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class LecteurController implements Initializable {
		Connection cnx;
		public PreparedStatement st;
		public ResultSet result;
	
	 	@FXML
	    private Button btnAdd;

	    @FXML
	    private Button btnDelete;

	    @FXML
	    private TableColumn<Lecteur, Integer> lecCIN;

	    @FXML
	    private TableColumn<Lecteur, Integer> lecCredit;

	    @FXML
	    private TableColumn<Lecteur, String> lecNom;

	    @FXML
	    private TableColumn<Lecteur, String> lecPrenom;

	    @FXML
	    private TextField searchNom;

	    @FXML
	    private TextField searchPrenom;

	    @FXML
	    private TableView<Lecteur> tableLecteur;

	    @FXML
	    private TextField txtCIN;

	    @FXML
	    private TextField txtCredit;

	    @FXML
	    private TextField txtNom;

	    @FXML
	    private TextField txtPrenom;

	    public ObservableList<Lecteur> data = FXCollections.observableArrayList();
	    
	    @FXML
	    void ajouterLecteur() {
	    	String cin= txtCIN.getText();
	    	String credit= txtCredit.getText();
	    	String nom= txtNom.getText();
	    	String prenom= txtPrenom.getText();
	    	
	    	
	    	String sql="INSERT INTO Lecteur (cin, nom,prenom, credit) VALUES (?, ?, ?, ?)";
	    	if(!cin.equals("")&&!credit.equals("")&&!nom.equals("")&&!prenom.equals("") ) {
	    		long valeurCIN = Long.parseLong(cin);
	    		long valeurCredit = Long.parseLong(credit);
	    		try {
					st=cnx.prepareStatement(sql);
					st.setLong(1, valeurCIN);
					st.setString(2, nom);
					st.setString(3, prenom);
					st.setLong(4, valeurCredit);
					st.execute();
					txtCIN.setText("");
					txtCredit.setText("");
					txtNom.setText("");
					txtPrenom.setText("");
					Alert alert = new Alert(AlertType.CONFIRMATION,"lecteur ajouté avec succès!", ButtonType.OK);
					alert.showAndWait();
					showLecteur();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
	    		alert.showAndWait();
	    	}

	    }

	    @FXML
	    void searchLecteur() {
	    	data.clear();

	        String nomFilter = searchNom.getText().toLowerCase();
	        String prenomFilter = searchPrenom.getText().toLowerCase();

	        try {
	            st = cnx.prepareStatement("SELECT * FROM lecteur");
	            result = st.executeQuery();

	            List<Lecteur> lecteurFiltres = new ArrayList<>();
	            while (result.next()) {
	                Lecteur lecteur = new Lecteur(
	                        result.getLong("cin"),
	                        result.getString("nom"),
	                        result.getString("prenom"),
	                        result.getLong("credit")
	                );
	                lecteurFiltres.add(lecteur);
	            }

	            lecteurFiltres = lecteurFiltres.stream()
	                    .filter(lecteur ->
	                    		nomFilter.isEmpty() || lecteur.getNom().toLowerCase().contains(nomFilter))
	                    .filter(lecteur ->
	                    		prenomFilter.isEmpty() || lecteur.getPrenom().toLowerCase().contains(prenomFilter))
	                    .collect(Collectors.toList());

	            tableLecteur.setItems(FXCollections.observableArrayList(lecteurFiltres));
	            if(lecteurFiltres.isEmpty()) {
		        	Alert alert = new Alert(AlertType.ERROR, "Aucun lecteur trouvé", ButtonType.OK);
		        	alert.showAndWait();
		        }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	       
	    }

	    @FXML
	    void supprimerLecteur() {
	    	String sql= "delete from lecteur where cin= '"+ txtCIN.getText()+"' and nom= '"+ txtNom.getText()+"'and prenom= '"+ txtPrenom.getText()+"'";
	    	if(!txtCIN.getText().equals("")&&!txtNom.getText().equals("")&&!txtPrenom.getText().equals("")&&!txtCredit.getText().equals("") ) {
	    	try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				txtCIN.setText("");
				txtNom.setText("");
				txtPrenom.setText("");
				txtCredit.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"lecteur supprimé avec succès!", ButtonType.OK);
				alert.showAndWait();
				showLecteur();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
	    		alert.showAndWait();
	    	}


	    }
	    
	    public void showLecteur() {
	    	data.clear();
	    	String sql = "select * from lecteur";
	    	try {
				st= cnx.prepareStatement(sql);
				result = st.executeQuery();
				while(result.next()) {
					data.add(new Lecteur(result.getLong("cin"), result.getString("nom"),result.getString("prenom"),result.getLong("credit")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	lecCIN.setCellValueFactory(new PropertyValueFactory<Lecteur, Integer>("cin"));
	    	lecNom.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("nom"));
	    	lecPrenom.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("prenom"));
		    lecCredit.setCellValueFactory(new PropertyValueFactory<Lecteur, Integer>("credit"));
		    tableLecteur.setItems(data);

	    }
	
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			DataBaseConnection c = new DataBaseConnection();
			cnx= c.connect();
			showLecteur();
			
		}

}
