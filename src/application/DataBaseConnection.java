package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private String BDD = "bibliotheque";
    private String url = "jdbc:mysql://localhost:3306/" + BDD;
    private String username = "root";
    private String password = "";
	public DataBaseConnection() {}
	public Connection connect() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connecté");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
        return null;
	}
	

}
