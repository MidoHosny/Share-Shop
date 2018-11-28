package com.example.android.seen.Tools;

import android.content.Context;
import android.widget.ImageView;

import com.example.android.seen.R;
import com.squareup.picasso.Picasso;

public class picassoImage {

	 public static void downloadImage(Context context, String URL, ImageView imageView) {

		  if ( URL != null && URL.length() > 0 ) {

			   Picasso.with(context).load(URL).placeholder(R.drawable.defult).into(imageView);
		  } else {
			   Picasso.with(context)
					   .load(R.drawable.defult)
					   .error(R.drawable.defult)
					   .into(imageView);
		  }

	 }
}
