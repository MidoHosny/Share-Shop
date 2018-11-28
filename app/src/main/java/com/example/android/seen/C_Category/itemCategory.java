package com.example.android.seen.C_Category;

public class itemCategory {

	 /**
	  * Var initialize from database column
	  */
	 String CategoryID, CategoryTitle, CategoryImageURL;

	 /**
	  *  Generate Get && set
	  */

	 /**
	  * method to get ID categoryActivity
	  *
	  * @return
	  */
	 public String getCategoryID() {
		  return CategoryID;
	 }

	 /**
	  * method to set ID categoryActivity
	  *
	  * @param categoryID
	  */
	 public void setCategoryID(String categoryID) {
		  CategoryID = categoryID;
	 }

	 /**
	  * method to get Title categoryActivity
	  *
	  * @return
	  */
	 public String getCategoryTitle() {
		  return CategoryTitle;
	 }

	 /**
	  * method to set Title category
	  *
	  * @param categoryTitle
	  */
	 public void setCategoryTitle(String categoryTitle) {
		  CategoryTitle = categoryTitle;
	 }

	 /**
	  * Method to Get ImageURL categoryActivity
	  *
	  * @return
	  */
	 public String getCategoryImageURL() {
		  return CategoryImageURL;
	 }

	 /**
	  * Method to Set ImageURL categoryActivity
	  *
	  * @param categoryImageURL
	  */
	 public void setCategoryImageURL(String categoryImageURL) {
		  CategoryImageURL = categoryImageURL;
	 }
}
