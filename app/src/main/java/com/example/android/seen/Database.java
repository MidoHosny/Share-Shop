package com.example.android.seen.DB;

import android.content.Context;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mohamed Hosny on 10/08/2018.
 */

public class Database {

	 Connection conn = null;

	 public Connection ConnectDB(Context context) {
		  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		  StrictMode.setThreadPolicy(policy);


		  try {
			   Class.forName("net.sourceforge.jtds.jdbc.Driver");

			   conn = DriverManager.getConnection("jdbc:jtds:sqlserver://SQL5020.site4now.net/DB_A44F90_ourProject2" ,
					   "DB_A44F90_ourProject2_admin" ,
					   "Ourproject111");

		  } catch (ClassNotFoundException e) {
			   e.printStackTrace();
		  } catch (SQLException e) {
			   e.printStackTrace();
		  }
		  return conn;
	 }

	 /**
	  * Method for insert && update && Delete
	  *
	  * @param statement_DDL
	  *
	  * @return
	  */
	 // insert - update - delete
	 public String RunDML(String statement_DDL) {
		  try {
			   Statement runDML = conn.createStatement();
			   runDML.executeUpdate(statement_DDL);
			   return "ok";
		  } catch (SQLException e) {
			   e.printStackTrace();
			   return e.getMessage();
		  }
	 }

	 /**
	  * method for search
	  *
	  * @param SelectStatement
	  *
	  * @return
	  */
	 //select
	 public ResultSet Search(String SelectStatement) {
		  ResultSet SearchResult;
		  try {
			   Statement runDML = conn.createStatement();
			   SearchResult = runDML.executeQuery(SelectStatement);
			   return SearchResult;
		  } catch (SQLException e) {
			   e.printStackTrace();
			   return null;
		  }
	 }
}
