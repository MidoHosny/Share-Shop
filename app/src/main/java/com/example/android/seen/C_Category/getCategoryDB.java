package com.example.android.seen.C_Category;

import android.app.AlertDialog;
import android.content.Context;

import com.example.android.seen.DB.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getCategoryDB {

	 /**
	  * Connect Database
	  */
	 Database DB = new Database();

	 /**
	  * Method for get All Data in Class itemCategory
	  */

	 public List<itemCategory> GetAllCategory(Context context) {

		  /**
		   *  ArrayList To Get Column From Database and set in arrayList
		   */
		  List<itemCategory> CategoryList = new ArrayList<>();

		  /**
		   *  Select Statement  To Search form Table In Database
		   */
		  String selectQuery = "select * from  Category";

		  /**
		   * Connection DB
		   */
		  Connection conn = DB.ConnectDB(context);

		  if ( conn == null ) {
			   AlertDialog.Builder al = new AlertDialog.Builder(context);
			   al.setTitle("خطا")
					   .setMessage("عفوا لا يوجد اتصال بالانترنت")
					   .setNegativeButton("تمام", null);
			   al.create();
			   al.show();
		  } else {
			   /**
				* var NameStatement to used Select Statement
				*/
			   Statement stmt;

			   try {
					stmt = conn.createStatement();

					/**
					 * Var Result to implements Select Statement
					 */
					ResultSet resultSet = stmt.executeQuery(selectQuery);

					/**
					 * chick if resultSet Contains data Or not
					 */
					resultSet.next();
					/**
					 *  Make loop to get all data from database
					 */

					do {

						 /**
						  *  get data from database and add in itemCategory arrayList
						  */

						 itemCategory addColumn = new itemCategory();
						 /**
						  * add column from table categoryActivity in database to itemCategory class
						  */
						 addColumn.setCategoryID(resultSet.getString(1));
						 addColumn.setCategoryTitle(resultSet.getString(2));
						 addColumn.setCategoryImageURL(resultSet.getString(3));

						 /**
						  *  give data to CategoryList
						  */
						 CategoryList.add(addColumn);

					} while (resultSet.next());  /** condition if data contains*/

			   } catch (SQLException e) {
					e.printStackTrace();
			   }


			   /**
				* Return all data to CategoryList
				*/
		  }
		  return CategoryList;
	 }


}
