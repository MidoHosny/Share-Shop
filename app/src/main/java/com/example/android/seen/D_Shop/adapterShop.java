package com.example.android.seen.D_Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.seen.Tools.picassoImage;
import com.example.android.seen.R;

import java.util.ArrayList;

public class adapterShop extends ArrayAdapter<itemShop> {

	 Context context;
	 ArrayList<itemShop> mitem_shop62;


	 public adapterShop(Context context, ArrayList<itemShop> mitem_shop62) {
		  super(context, R.layout.list_item_shop_format, mitem_shop62);
		  this.context = context;
		  this.mitem_shop62 = mitem_shop62;
	 }


	 public class Holder {
		  TextView mTitle, mDec,
				  person_count_rate;
		  ImageView mImage;

	 }


	 public View getView(int position, View convertView, ViewGroup parent) {

		  /**
		   *  var initialize from item_list_category
		   */

		  /**
		   * data var initialize to get position from itemCategory
		   */
		  itemShop data = getItem(position);

		  Holder viewHolder;

		  if ( convertView == null ) {

			   viewHolder = new Holder();

			   /**
				* get layout to give adapter
				*/
			   LayoutInflater layoutInflater = LayoutInflater.from(getContext());
			   convertView = layoutInflater.inflate(R.layout.list_item_shop_format, parent, false);

			   /**
				*  Get ID Views in Layout to give ViewHolder
				*/
			   viewHolder.mTitle = convertView.findViewById(R.id.Title_text_view_shop);
			   viewHolder.mDec = convertView.findViewById(R.id.Description_text_view_shops);
			   viewHolder.mImage = convertView.findViewById(R.id.image_shop);
			   viewHolder.person_count_rate = convertView.findViewById(R.id.person_count_rate);

			   convertView.setTag(viewHolder);
		  } else {

			   viewHolder = (Holder) convertView.getTag();

		  }

		  /**
		   *  Set Var in Class item_list_Category to viewHolder
		   */
		  viewHolder.mTitle.setText(data.ShopTitle);

		  viewHolder.person_count_rate.setText(data.person_count_rate);

		  viewHolder.mDec.setText(data.ShopDec);
		  picassoImage.downloadImage(context, mitem_shop62.get(position).getShopURL(), viewHolder.mImage);


//        Database db = new Database();
//        db.ConnectDB();
//        ResultSet resultSet = db.Search("select avg (RateValue) from Rating where ShopID ='" + data.getShopID() + "' ");
//        try {
//            if (resultSet.next()) {
//            //   Toast.makeText(getContext(), "erre" + resultSet.getString(1), Toast.LENGTH_LONG).show();
//                    viewHolder.RatingAvg.setRating(Float.parseFloat(resultSet.getString(1).toString()));
//
//                viewHolder.count_Rate.setText(resultSet.getString(1));
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        ResultSet resultSet_count = db.Search("select count (RateValue) from Rating where ShopID ='" + data.getShopID() + "' ");
//        try {
//            if ((resultSet_count.next())) {
//                viewHolder.person_count_rate.setText(resultSet_count.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


		  //   viewHolder.count_Rate.setText("50");
		  //  viewHolder.mImage

		  return convertView;
	 }


}
