package psql_conn_test;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

public class DBConnection {
		public Connection Connect() {
			try {
				String url = "jdbc:postgresql://localhost:5000/users";
				String user = "postgres";
				String password = "123ergi1";
				
				Class.forName("org.postgresql.Driver");
				Connection con = DriverManager.getConnection(url, user, password);
				
				return con;
				
			} catch(ClassNotFoundException | SQLException e) {
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
			}
			
			return null;
		}
}
