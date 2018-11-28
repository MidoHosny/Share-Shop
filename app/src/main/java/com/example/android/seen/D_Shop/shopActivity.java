package com.example.android.seen.D_Shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.android.seen.C_Category.categoryActivity;
import com.example.android.seen.DB.Database;
import com.example.android.seen.D_Shop.Map.MapsActivity;
import com.example.android.seen.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Calendar;

public class shopActivity extends AppCompatActivity {


	 public static itemShop item_shops62;
	 ArrayList<itemShop> items;
	 ProgressBar progressBarShop;
	 private TextView txtLoging;
	 private adapterShop mAdapterShop63;
	 ListView lst;
	 getShopDB mGet_Shop_FromDB61 = new getShopDB();
	 SwipeRefreshLayout mSwipeRefreshLayout;


	 SliderLayout sliderShow;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Bar();
		  setContentView(R.layout.shop_activity);

		  progressBarShop = findViewById(R.id.progressBarShop);
		  txtLoging = findViewById(R.id.txtLoging);

		  mSwipeRefreshLayout = findViewById(R.id.swipeShop);
		  mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue)
				  , (getResources().getColor(R.color.purple))
				  , (getResources().getColor(R.color.green))
				  , (getResources().getColor(R.color.orange)));
		  mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			   @Override
			   public void onRefresh() {
					/**
					 * use method showDataCategory
					 */
					new Handler().postDelayed(new Runnable() {

						 @Override
						 public void run() {

							  showDataCategory();
							  mSwipeRefreshLayout.setRefreshing(false);

						 }

					}, 3000);

			   }
		  });
		  lst = findViewById(R.id.GradViewShop);

		  new Handler().postDelayed(new Runnable() {

			   @Override
			   public void run() {

					showDataCategory();

					progressBarShop.setVisibility(View.GONE);
					txtLoging.setVisibility(View.GONE);


			   }

		  }, 1000);

	 }


	 private void showDataCategory() {

		  /**
		   *  initialize Array list from item_list_category Class
		   */

		  final ArrayList<itemShop> items;

		  /**
		   * set var items to method GET_ALL_CATEGORY
		   */
		  items = new ArrayList<>(mGet_Shop_FromDB61.GetAllShop(shopActivity.this, categoryActivity.CategoryID));

		  /**
		   *  use Var mAdapterCategory from Adapter categoryActivity Class
		   */

		  mAdapterShop63 = new adapterShop(this, items);

		  LayoutInflater layoutInflater = LayoutInflater.from(shopActivity.this);
		  View viewl = layoutInflater.inflate(R.layout.list_item_shop_format, null);
		  /**
		   *  set adapter to GradView
		   */
		  lst.setAdapter(mAdapterShop63);

		  /**
		   *  Set on Item Click Listener for Position To Show Bottom Sheet Dialog Profile Owner
		   */


		  lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					/**
					 *  Get Variable From Class Database
					 */
					item_shops62 = items.get(position);

					/**
					 *  Make Bottom Sheet Dialog
					 */
					BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(shopActivity.this);
					/**
					 *  Make Layout inflater From Layout to set Layout To Bottom Sheet Dialog
					 */
					LayoutInflater layoutInflater = LayoutInflater.from(shopActivity.this);
					View view1 = layoutInflater.inflate(R.layout.list_item_profile_shop_format, null);


					/**
					 *  Set Content View To Bottom Sheet Dialog
					 */
					bottomSheetDialog.setContentView(view1);

					/**
					 *  initialization Variable
					 */
					TextView shopNameProfile = view1.findViewById(R.id.shopNameProfile);
					ImageView loveProfile = view1.findViewById(R.id.loveProfile);
					TextView DEC_shop_profile = view1.findViewById(R.id.DEC_shop_profile);
					ImageView Call_profile = view1.findViewById(R.id.Call_profile);
					ImageView MAP_profile = view1.findViewById(R.id.MAP_profile);
					ImageView Time_profile = view1.findViewById(R.id.Time_profile);
					ImageView FB_profile = view1.findViewById(R.id.FB_profile);
					ImageView whats_profile = view1.findViewById(R.id.whats_profile);
					ImageView share_Profile_Oner = view1.findViewById(R.id.share_Profile_Oner);
					ImageView rating = view1.findViewById(R.id.rating);


					/**
					 *  Set Image for shop Profile && Image DEC
					 */


					sliderShow = view1.findViewById(R.id.slider);


					sliderShow.setCustomIndicator((PagerIndicator) view1.findViewById(R.id.custom_indicator));


					TextSliderView a = new TextSliderView(shopActivity.this);
					a.image(items.get(position).getProfile_image_background())
							.setScaleType(BaseSliderView.ScaleType.Fit);
					sliderShow.addSlider(a);


					TextSliderView b = new TextSliderView(shopActivity.this);
					b.image(items.get(position).getProfile_image_DCE_1())
							.setScaleType(BaseSliderView.ScaleType.Fit);
					sliderShow.addSlider(b);

					TextSliderView c = new TextSliderView(shopActivity.this);
					c.image(items.get(position).getProfile_image_DCE_2())
							.setScaleType(BaseSliderView.ScaleType.Fit);
					sliderShow.addSlider(c);

					TextSliderView d = new TextSliderView(shopActivity.this);
					d.image(items.get(position).getProfile_image_DCE_3())
							.setScaleType(BaseSliderView.ScaleType.Fit);
					sliderShow.addSlider(d);


					/**
					 * Set shopActivity Name From DataBase
					 */
					shopNameProfile.setText(item_shops62.getShopTitle());

					/**
					 *  Set Description For Profile
					 */
					DEC_shop_profile.setText(item_shops62.getProfile_Dec());


					/**
					 *  on click to rating
					 */
					rating.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  AlertDialog.Builder alrt = new AlertDialog.Builder(shopActivity.this);
							  LayoutInflater lay = LayoutInflater.from(shopActivity.this);
							  final View view2 = lay.inflate(R.layout.rating_profile, null);
							  alrt.setView(view2);

							  final RatingBar mBar = view2.findViewById(R.id.mRatingBar);
							  final ProgressBar progressBarRating = view2.findViewById(R.id.progressBarRating);


							  TextView send_Rating = view2.findViewById(R.id.send_Rating);
							  send_Rating.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {
										progressBarRating.setVisibility(View.VISIBLE);
										SharedPreferences ReadeName = getSharedPreferences("LoginRememberMe", MODE_PRIVATE);
										String ReadeUserName = ReadeName.getString("UserName", null);

										Database DB = new Database();
										DB.ConnectDB(shopActivity.this);
										String msg = DB.RunDML("insert into Rating values ( '" + ReadeUserName + "' , '" + item_shops62.getShopID() + "','" + mBar.getRating() + "','" + Calendar.getInstance().getTime() + "')");

										if ( msg.equals("ok") ) {
											 progressBarRating.setVisibility(View.GONE);
											 Toast.makeText(getApplicationContext(), "Rating send done", Toast.LENGTH_LONG).show();
										} else {
											 progressBarRating.setVisibility(View.VISIBLE);

											 msg = DB.RunDML(" update Rating set RateValue ='" + mBar.getRating() + "' ,RateDate ='" + Calendar.getInstance().getTime() + "' where UserName='" + ReadeUserName + "' and  ShopID ='" + item_shops62.getShopID() + "' ");

											 if ( msg.equals("ok") ) {

												  progressBarRating.setVisibility(View.GONE);
												  Toast.makeText(getApplicationContext(), "Rating update done", Toast.LENGTH_LONG).show();
											 } else {
												  Toast.makeText(getApplicationContext(), "Error" + msg, Toast.LENGTH_LONG).show();
/**
 *  validation
 */
											 }
										}

								   }
							  });


							  alrt.create();
							  alrt.show();
						 }
					});

					/**
					 *  on click to share_Profile_Oner
					 */
					share_Profile_Oner.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(shopActivity.this);
							  /**
							   *  Make Layout inflater From Layout to set Layout To Bottom Sheet Dialog
							   */
							  LayoutInflater layoutInflater = LayoutInflater.from(shopActivity.this);
							  View view1 = layoutInflater.inflate(R.layout.share_profile_oner, null);


							  /**
							   *  Set Content View To Bottom Sheet Dialog
							   */
							  bottomSheetDialog.setContentView(view1);


							  LinearLayout fb_Share_Oner = view1.findViewById(R.id.fb_Share_Oner);
							  LinearLayout mass_share_oner = view1.findViewById(R.id.mass_share_oner);
							  LinearLayout whatsapp_share_oner = view1.findViewById(R.id.whatsapp_share_oner);
							  LinearLayout twetter_share_oner = view1.findViewById(R.id.twetter_share_oner);
							  LinearLayout other_share_oner = view1.findViewById(R.id.other_share_oner);

							  fb_Share_Oner.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {

										Intent FB = new Intent(Intent.ACTION_SEND);
										FB.setType("text/plain");
										FB.putExtra(Intent.EXTRA_TEXT,
												"\n+" +
														item_shops62.getShopTitle()
														+
														"\n"
														+
														item_shops62.getProfile_PhoneNumber());
										FB.setPackage("com.facebook.katana");
										startActivity(FB);

								   }
							  });


							  mass_share_oner.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {
										Intent sendIntent = new Intent();
										sendIntent.setAction(Intent.ACTION_SEND);
										sendIntent.putExtra(Intent.EXTRA_TEXT,
												"\n+" +
														item_shops62.getShopTitle() +
														"\n" +
														item_shops62.getProfile_PhoneNumber());
										sendIntent.setType("text/plain");
										sendIntent.setPackage("com.facebook.orca");
										try {
											 startActivity(sendIntent);
										} catch (android.content.ActivityNotFoundException ex) {
											 Toast.makeText(shopActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
										}
								   }
							  });

							  whatsapp_share_oner.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {

										Intent WhatsApp = new Intent(Intent.ACTION_SEND);
										WhatsApp.setType("text/plain");
										WhatsApp.putExtra(Intent.EXTRA_TEXT,
												"\n+" +
														item_shops62.getShopTitle() +
														"\n" +
														item_shops62.getProfile_PhoneNumber());
										WhatsApp.setPackage("com.whatsapp");
										startActivity(WhatsApp);

								   }
							  });

							  twetter_share_oner.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {
										try {
											 Intent twitter = new Intent(Intent.ACTION_SEND);
											 twitter.setType("text/plain");
											 twitter.putExtra(Intent.EXTRA_TEXT,
													 "\n+" +
															 item_shops62.getShopTitle() +
															 "\n" +
															 item_shops62.getProfile_PhoneNumber());
											 twitter.setPackage("advanced.twitter.android");
											 startActivity(twitter);
										} catch (Exception e) {
											 Toast.makeText(shopActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
										}
								   }
							  });


							  other_share_oner.setOnClickListener(new View.OnClickListener() {
								   @Override
								   public void onClick(View v) {
										try {
											 Intent i = new Intent(Intent.ACTION_SEND);
											 i.setType("text/plain");
											 i.putExtra(Intent.EXTRA_TEXT,
													 "\n+" +
															 item_shops62.getShopTitle() +
															 "\n" +
															 item_shops62.getProfile_PhoneNumber());
											 startActivity(Intent.createChooser(i, "choose one"));
										} catch (Exception e) {
											 //e.toString();
										}
								   }
							  });


							  /**---------------------------------------------------------------------------------
							   *  Show Bottom Sheet Dialog
							   */
							  bottomSheetDialog.show();
						 }
					});


					/**
					 *  On Click to Call
					 */
					Call_profile.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {

							  final String PhoneNumber = item_shops62.getProfile_PhoneNumber();
							  Intent intent = new Intent(Intent.ACTION_DIAL);
							  intent.setData(Uri.parse("tel:" + PhoneNumber));
							  startActivity(intent);
						 }
					});

					/**
					 *   on click to open whats app
					 */
					whats_profile.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  final String PhoneNumberwhatsApp = item_shops62.getProfile_Whatsapp_URL();
							  startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.whatsapp.com/send?phone=+" + PhoneNumberwhatsApp)));
						 }
					});

					/**
					 * on click for time profile
					 */
					Time_profile.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {

							  AlertDialog.Builder alt = new AlertDialog.Builder(shopActivity.this);

							  alt.setTitle("مواعيد العمل");
							  alt.setIcon(R.drawable.time);
							  alt.setMessage(
									  item_shops62.getProfile_Open_Time()
											  + "\n"
											  + item_shops62.getProfile_Close_Time());
							  alt.setNegativeButton("تمام", null);
							  alt.create();
							  alt.show();

						 }
					});

					/**
					 *  on click  to open  the facebook profile
					 */
					FB_profile.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  try {

								   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item_shops62.getProfile_Facebook_URl()));
								   startActivity(intent);

							  } catch (Exception e) {

								   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/business/help/community/question/?id=1267743879941243")));

							  }
						 }
					});

					/**
					 *   on Click to open Google Map
					 */
					MAP_profile.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  //   startActivity(new Intent(shopActivity.this,MapsActivity.class));

							  //   if (Settings.ACTION_LOCATION_SOURCE_SETTINGS == null){
							  //    showSettingAlert();
							  //  }
							  //     else {
							  startActivity(new Intent(shopActivity.this, MapsActivity.class));
							  //   }

						 }
					});

					/**---------------------------------------------------------------------------------
					 *  Show Bottom Sheet Dialog
					 */
					bottomSheetDialog.show();

			   }  /** ------------------------------- END ON CLICK ------------------------------------*/
		  });

	 }

	 public void showSettingAlert() {
		  AlertDialog.Builder alertDialog = new AlertDialog.Builder(shopActivity.this);
		  alertDialog.setTitle("GPS setting!");
		  alertDialog.setMessage("GPS is not enabled, Do you want to go to settings menu? ");
		  alertDialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
			   @Override
			   public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					shopActivity.this.startActivity(intent);
			   }
		  });
		  alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			   @Override
			   public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
			   }
		  });
		  alertDialog.show();
	 }

	 private void Bar() {

		  Window window = getWindow();

		  if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {
			   window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			   SystemBarTintManager tintManager = new SystemBarTintManager(this);
			   tintManager.setStatusBarTintEnabled(true);
			   tintManager.setTintColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
		  }
		  if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
			   window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			   window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		  }

	 }
}
