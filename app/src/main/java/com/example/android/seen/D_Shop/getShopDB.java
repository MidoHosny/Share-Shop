package com.example.android.seen.D_Shop;

import android.app.AlertDialog;
import android.content.Context;

import com.example.android.seen.DB.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getShopDB {

	 /**
	  * Connect Database
	  */
	 Database DB = new Database();
	 itemShop addColumn;

	 /**
	  * Method for get All Data in Class itemCategory
	  */


	 public List<itemShop> GetAllShop(Context context, String categoryID) {

		  /**
		   *  ArrayList To Get Column From Database and set in arrayList
		   */
		  List<itemShop> ShopList = new ArrayList<>();

		  /**
		   *  Select Statement  To Search form Table In Database
		   */
		  String selectQuery = "select * from Shop where CategoryID='" + categoryID + "'";

		  /**
		   * Connection DB
		   */
		  Connection conn;
		  conn = DB.ConnectDB(context);

		  if ( conn == null ) {

			   AlertDialog.Builder al = new AlertDialog.Builder(context);
			   al.setMessage("عفوا لا يوجد اتصال بالانترنت")
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

						 addColumn = new itemShop();
						 /**
						  * add column from table categoryActivity in database to itemCategory class
						  */
						 addColumn.setShopID(resultSet.getString(1));
						 addColumn.setShopTitle(resultSet.getString(2));
						 addColumn.setShopDec(resultSet.getString(3));
						 addColumn.setShopURL(resultSet.getString(4));
						 addColumn.setCategoryID(resultSet.getString(5));
						 addColumn.setProfile_image_background(resultSet.getString(6));
						 addColumn.setProfile_Dec(resultSet.getString(7));
						 addColumn.setProfile_image_DCE_1(resultSet.getString(8));
						 addColumn.setProfile_image_DCE_2(resultSet.getString(9));
						 addColumn.setProfile_image_DCE_3(resultSet.getString(10));
						 addColumn.setProfile_PhoneNumber(resultSet.getString(11));
						 addColumn.setProfile_Lat_Map(resultSet.getString(12));
						 addColumn.setProfile_Long_Map(resultSet.getString(13));
						 addColumn.setProfile_Open_Time(resultSet.getString(14));
						 addColumn.setProfile_Close_Time(resultSet.getString(15));
						 addColumn.setProfile_Facebook_URl(resultSet.getString(16));
						 addColumn.setProfile_Whatsapp_URL(resultSet.getString(17));

						 ResultSet resultSet_count = DB.Search("select count (RateValue) from Rating where ShopID ='" + addColumn.getShopID() + "' ");

						 if ( (resultSet_count.next()) ) {

							  addColumn.setPerson_count_rate(resultSet_count.getString(1));

						 }


						 /**
						  *  give data to CategoryList
						  */
						 ShopList.add(addColumn);


					} while (resultSet.next());  /** condition to data contains*/

			   } catch (SQLException e) {
					e.printStackTrace();
			   }


			   /**
				* Return all data to CategoryList
				*/
		  }
		  return ShopList;
	 }


}
