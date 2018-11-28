package com.example.android.seen.B_Ads;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.android.seen.DB.Database;
import com.example.android.seen.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getAdsDB {

	 /**
	  * Connect Database
	  */
	 Database DB = new Database();

	 /**
	  * Method for get All Data in Class itemCategory
	  */

	 public List<itemAds> GetAllAds(final Context context) {

		  /**
		   *  ArrayList To Get Column From Database and set in arrayList
		   */
		  List<itemAds> AdsList = new ArrayList<itemAds>();

		  /**
		   *  Select Statement  To Search form Table In Database
		   */
		  String selectQuery = "select * from  Advertisement";

		  /**
		   * Connection DB
		   */
		  Connection conn;
		  conn = DB.ConnectDB(context);

		  if ( conn == null ) {


			   final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
			   LayoutInflater layoutInflater = LayoutInflater.from(context);
			   View view1 = layoutInflater.inflate(R.layout.no_internet_connection, null);
			   bottomSheetDialog.setContentView(view1);

			   final Button retry = view1.findViewById(R.id.retry);
			   retry.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						 getAdsDB mGet_Offers_FromDB41 = new getAdsDB();
						 mGet_Offers_FromDB41.GetAllAds(context);
						 bottomSheetDialog.dismiss();

					}
			   });

			   bottomSheetDialog.setCancelable(false);
			   bottomSheetDialog.show();


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

						 itemAds addColumn = new itemAds();
						 /**
						  * add column from table categoryActivity in database to itemCategory class
						  */
						 addColumn.setAdsID(resultSet.getString(1));
						 addColumn.setAdsTitle(resultSet.getString(2));
						 addColumn.setAdsDec(resultSet.getString(3));
						 addColumn.setAdsImageURL(resultSet.getString(4));

						 /**
						  *  give data to CategoryList
						  */
						 AdsList.add(addColumn);

					} while (resultSet.next());  /** condition to data contains*/

			   } catch (SQLException e) {
					e.printStackTrace();
			   }

			   /**
				* Return all data to CategoryList
				*/
		  }
		  return AdsList;
	 }

}
