package psql_conn_test;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

public class User {
	
	private String userEmail;
	private String userName;
	private String userLastName;
	private DBConnection dc = new DBConnection();
	
	public User(String e) {
		Connection connection = dc.Connect();
		ResultSet resultSet;
		try {
			resultSet = connection.createStatement().executeQuery("SELECT * FROM user_db WHERE email= '" + e +"'");
			if(resultSet.next()) {
				userEmail = resultSet.getString(2);
				userName = resultSet.getString(4);
				userLastName = resultSet.getString(5);
			}
		} catch (SQLException e1) {
		
		} 
	}
	
	public String getEmail() throws SQLException {
		return userEmail;
	}
	
	public String getName()  {
		return userName;
	}
	
	public String getLastName()  {
		return userLastName;
	}
}
