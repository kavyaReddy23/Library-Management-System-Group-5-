package lib.management.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

	public static Connection getConnection()
	{ 
		Connection connection=null;
		
		 try
	        {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarymanagement", "root",
						"wiley");
	        }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}

	
}
