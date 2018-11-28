package com.example.android.seen.B_Ads;

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

public class adapterAds extends ArrayAdapter<itemAds> {

	 Context context;
	 ArrayList<itemAds> mAdverList;

	 public adapterAds(Context context, ArrayList<itemAds> mAdverList) {

		  super(context, R.layout.list_item_adver_format, mAdverList);
		  this.context = context;
		  this.mAdverList = mAdverList;
	 }


	 public class Holder {
		  TextView mTitle, mDesc;
		  ImageView mImageURl;

	 }

	 public View getView(int position, View convertView, ViewGroup parent) {

		  itemAds data = getItem(position);
		  Holder ViewHolder;

		  if ( convertView == null ) {

			   ViewHolder = new Holder();

			   LayoutInflater layoutInflater = LayoutInflater.from(getContext());
			   convertView = layoutInflater.inflate(R.layout.list_item_adver_format, parent, false);
			   ViewHolder.mTitle = convertView.findViewById(R.id.Title_text_view_Adver);
			   ViewHolder.mDesc = convertView.findViewById(R.id.Description_text_view_Adver);
			   ViewHolder.mImageURl = convertView.findViewById(R.id.image_Adver);

			   ViewHolder.mImageURl.setScaleType(ImageView.ScaleType.FIT_XY);
			   convertView.setTag(ViewHolder);

		  } else {

			   ViewHolder = (Holder) convertView.getTag();
		  }
		  ViewHolder.mTitle.setText(data.getAdsTitle());
		  ViewHolder.mDesc.setText(data.getAdsDec());
		  picassoImage.downloadImage(context, mAdverList.get(position).getAdsImageURL(), ViewHolder.mImageURl);

		  return convertView;

	 }

}
