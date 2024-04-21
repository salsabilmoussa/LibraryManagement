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

import Models.Livre;
import application.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivreController implements Initializable {
		Connection cnx;
		public PreparedStatement st;
		public ResultSet result;

	   	@FXML
	    private Button btnAdd;

	    @FXML
	    private Button btnDelete;
	    @FXML
	    private TableColumn<Livre, Integer> livCode;
	    
	    @FXML
	    private TableColumn<Livre, String> livTitre;
	    
	    @FXML
	    private TableColumn<Livre, String> livAuteur;

	    @FXML
	    private TableColumn<Livre, Integer> livISBN;

	    @FXML
	    private TableColumn<Livre, String> livType;

	    @FXML
	    private TextField searchAuteur;

	    @FXML
	    private TextField searchTitre;
	    
	    @FXML
	    private TextField txtAuteur;

	    @FXML
	    private TextField txtISBN;

	    @FXML
	    private TextField txtTitre;

	    @FXML
	    private TextField txtType;

	    @FXML
	    private TableView<Livre> tableLivre;

	    public ObservableList<Livre> data = FXCollections.observableArrayList();
	    
	    @FXML
	    void ajouterLivre() {
	    	String aut= txtAuteur.getText();
	    	String titr= txtTitre.getText();
	    	String typ= txtType.getText();
	    	String isbn= txtISBN.getText();
	    	
	    	
	    	String sql="INSERT INTO Livre (titre, auteur, ISBN, type) VALUES (?, ?, ?, ?)";
	    	if(!aut.equals("")&&!titr.equals("")&&!typ.equals("")&&!isbn.equals("") ) {
	    		long valeurISBN = Long.parseLong(isbn);
	    		try {
					st=cnx.prepareStatement(sql);
					st.setString(1, titr);
					st.setString(2, aut);
					st.setLong(3, valeurISBN);
					st.setString(4, typ);
					st.execute();
					txtAuteur.setText("");
					txtTitre.setText("");
					txtType.setText("");
					txtISBN.setText("");
					Alert alert = new Alert(AlertType.CONFIRMATION,"livre ajouté avec succès!", ButtonType.OK);
					alert.showAndWait();
					showLivre();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
	    		alert.showAndWait();
	    	}

	    }

	    @FXML
	    void searchLivre() {
	    	data.clear();

	        String titreFilter = searchTitre.getText().toLowerCase();
	        String auteurFilter = searchAuteur.getText().toLowerCase();

	        try {
	            st = cnx.prepareStatement("SELECT * FROM livre");
	            result = st.executeQuery();

	            List<Livre> livresFiltres = new ArrayList<>();
	            while (result.next()) {
	                Livre livre = new Livre(
	                        result.getLong("code"),
	                        result.getString("titre"),
	                        result.getString("auteur"),
	                        result.getString("type"),
	                        result.getLong("ISBN")
	                );
	                livresFiltres.add(livre);
	            }

	            livresFiltres = livresFiltres.stream()
	                    .filter(livre ->
	                            titreFilter.isEmpty() || livre.getTitre().toLowerCase().contains(titreFilter))
	                    .filter(livre ->
	                            auteurFilter.isEmpty() || livre.getAuteur().toLowerCase().contains(auteurFilter))
	                    .collect(Collectors.toList());

	            tableLivre.setItems(FXCollections.observableArrayList(livresFiltres));
	            if(livresFiltres.isEmpty()) {
		        	Alert alert = new Alert(AlertType.ERROR, "Aucun livre trouvé", ButtonType.OK);
		        	alert.showAndWait();
		        }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	       
	    }

	    @FXML
	    void supprimerLivre() {
	    	String sql= "delete from livre where ISBN= '"+ txtISBN.getText()+"' and titre= '"+ txtTitre.getText()+"'and auteur= '"+ txtAuteur.getText()+"'";
	    	if(!txtAuteur.getText().equals("")&&!txtTitre.getText().equals("")&&!txtType.getText().equals("")&&!txtISBN.getText().equals("") ) {
	    	try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				txtAuteur.setText("");
				txtTitre.setText("");
				txtType.setText("");
				txtISBN.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"livre supprimé avec succès!", ButtonType.OK);
				alert.showAndWait();
				showLivre();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING, "veuillez remplir toute les champs!", ButtonType.OK);
	    		alert.showAndWait();
	    	}


	    }
	    
	    public void showLivre() {
	    	data.clear();
	    	String sql = "select * from livre";
	    	try {
				st= cnx.prepareStatement(sql);
				result = st.executeQuery();
				while(result.next()) {
					data.add(new Livre(result.getLong("code"), result.getString("titre"),result.getString("auteur"),result.getString("type"),result.getLong("ISBN")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	livCode.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("code"));
	    	livTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
	    	livAuteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
	    	livType.setCellValueFactory(new PropertyValueFactory<Livre, String>("type"));
		    	livISBN.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("ISBN"));
		    	tableLivre.setItems(data);

	    }
	
	    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataBaseConnection c = new DataBaseConnection();
		cnx= c.connect();
		showLivre();
		
	}

	

}
