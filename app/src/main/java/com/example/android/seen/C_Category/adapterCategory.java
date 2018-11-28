package com.example.android.seen.C_Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.seen.Tools.picassoImage;
import com.example.android.seen.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapterCategory extends ArrayAdapter<itemCategory> {

	 Context context;
	 ArrayList<itemCategory> mCategoryList;

	 /**
	  * Constructor to get Context and Layout
	  *
	  * @param context
	  * @param mCategoryList
	  */
	 public adapterCategory(Context context, ArrayList<itemCategory> mCategoryList) {
		  super(context, R.layout.list_item_category_format, mCategoryList);
		  this.context = context;
		  this.mCategoryList = mCategoryList;
	 }

	 /**
	  * class to initialize Var To set Title and image
	  */
	 public class Holder {
		  TextView mTitle;
		  CircleImageView mImage;
	 }


	 /**
	  * Method to set View From Item_List_Category To adapterCategory
	  *
	  * @param position
	  * @param convertView
	  * @param parent
	  *
	  * @return
	  */
	 public View getView(int position, View convertView, ViewGroup parent) {

		  /**
		   * data var initialize to get position from itemCategory
		   */
		  itemCategory data = getItem(position);

		  Holder viewHolder;
		  /**
		   * create data in list_item_category
		   */
		  if ( convertView == null ) {

			   viewHolder = new Holder();

			   /**
				* get layout to give adapter
				*/
			   LayoutInflater layoutInflater = LayoutInflater.from(getContext());
			   convertView = layoutInflater.inflate(R.layout.list_item_category_format, parent, false);

			   /**
				*  Get ID Views in Layout to give ViewHolder
				*/
			   viewHolder.mTitle = convertView.findViewById(R.id.Title_List_Category);
			   viewHolder.mImage = convertView.findViewById(R.id.Image_List_Category);


			   /**
				*  Set Tag to Convert
				*/
			   convertView.setTag(viewHolder);

		  } else {

			   viewHolder = (Holder) convertView.getTag();

		  }


		  /**
		   *  Set Var in Class item_list_Category to viewHolder
		   */
		  viewHolder.mTitle.setText(data.getCategoryTitle());
		  picassoImage.downloadImage(context, mCategoryList.get(position).getCategoryImageURL(), viewHolder.mImage);

		  //  viewHolder.mImage

		  return convertView;
	 }


}
