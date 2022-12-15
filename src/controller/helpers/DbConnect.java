package controller.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
 public static DbConnect getInstance() {
	 return new DbConnect();
 }
public Connection getConnection() {
	 String dataBase="database";
         String username="root";
         String password="admin";
         String MySQLURL ="jdbc:mysql://localhost:3306/"+dataBase;
//	      String MySQLURL = "jdbc:sqlite::resource:Data.db";
//	      String MySQLURL = "jdbc:postgresql://localhost:5432/postgres,'postgres','okbb'";
	      Connection con = null;
	      
	      try {
	         con = DriverManager.getConnection(MySQLURL,username,password);
	         if (con != null) {
	           return con;
	         } 
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
   return con;
 }
 
	      
	   
}
