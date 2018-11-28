package com.example.android.seen.B_Ads;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.seen.C_Category.categoryActivity;
import com.example.android.seen.A_Login.LoginMethodActivity;
import com.example.android.seen.A_Login.loginActivity;
import com.example.android.seen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class adsActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	 CircleImageView no_image_profile;
	 private static int SELECT_IMAGE = 512;
	 private FirebaseAuth mAuth;
	 private adapterAds mAdapterOffers42;
	 ListView lst;
	 ProgressBar progressBar;
	 private TextView txtLoging_offers;

	 getAdsDB mGet_Offers_FromDB41 = new getAdsDB();
	 private SwipeRefreshLayout mSwipeRefreshLayout;
	 ArrayList<itemAds> items;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);

		  setContentView(R.layout.offers_activity);
		  items = new ArrayList<>(mGet_Offers_FromDB41.GetAllAds(adsActivity.this));

		  progressBar = findViewById(R.id.progressBarOferrs);
		  txtLoging_offers = findViewById(R.id.txtLoging_offers);


		  /**
		   * initialize ListView ID
		   */
		  lst = findViewById(R.id.ListViewOffers);

		  /**
		   * find view by id for a tool bar category
		   */
		  Toolbar toolbar = findViewById(R.id.toolbar);
		  setSupportActionBar(toolbar);


//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//        }, 2000);
		  /**
		   * SwipeRefreshLayout
		   */
		  mSwipeRefreshLayout = findViewById(R.id.swipe_Offers);
		  mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue)
				  , (getResources().getColor(R.color.purple))
				  , (getResources().getColor(R.color.green))
				  , (getResources().getColor(R.color.orange)));

		  /**
		   *  this to Refresh Data
		   */
		  mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			   @Override
			   public void onRefresh() {
					/**
					 * use method showDataCategory
					 */
					new Handler().postDelayed(new Runnable() {

						 @Override
						 public void run() {
							  showDataOffers();
							  mSwipeRefreshLayout.setRefreshing(false);

						 }

					}, 3000);

			   }
		  });

		  /**
		   * Var Firebase Database
		   */
		  mAuth = FirebaseAuth.getInstance();

		  /**
		   * find view for layout drawer to open menu bar
		   */
		  DrawerLayout drawer = findViewById(R.id.drawer_layout);
		  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		  drawer.addDrawerListener(toggle);
		  toggle.syncState();

/**************************************************************************************************/
		  /**
		   * to set navigation in menu
		   */
		  NavigationView navigationView =   findViewById(R.id.nav_view);

		  View headerView = navigationView.getHeaderView(0);
		  no_image_profile = headerView.findViewById(R.id.no_image_profile);


		  TextView profile_name = headerView.findViewById(R.id.profile_name);

//		  no_image_profile.setOnClickListener(new View.OnClickListener() {
//			   @Override
//			   public void onClick(View v) {
//					Toast.makeText(adsActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//					/**
//					 *  Open Gallery
//					 */
//					selecteImage();
//			   }
//		  });

/**************************************************************************************************/
		  /**
		   *  Save user name in shared preference and display in nave header bar
		   */
		  SharedPreferences ReadeName = getSharedPreferences("LoginRememberMe", MODE_PRIVATE);
		  String ReadeUserName = ReadeName.getString("UserName", null);
		  if ( ReadeUserName != null ) {

			   profile_name.setText(ReadeUserName);

		  }
		  String name = String.valueOf(loginActivity.mTxtUserNameLogin.getText().toString());
		  // profile_name.setText(name);

		  navigationView.setNavigationItemSelectedListener(this);
		  /**
		   *   show data from database
		   */

		  new Handler().postDelayed(new Runnable() {
			   @Override
			   public void run() {

					items = new ArrayList<>(mGet_Offers_FromDB41.GetAllAds(adsActivity.this));
					showDataOffers();

					// stop loading
					progressBar.setVisibility(View.GONE);
					txtLoging_offers.setVisibility(View.GONE);


			   }
		  }, 2000);

	 }

	 /**************************** End On Create ******************************************* */

	 private void selecteImage() {

		  Intent intent = new Intent();
		  intent.setType("image/*");
		  intent.setAction(Intent.ACTION_GET_CONTENT);
		  startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
	 }

	 @Override
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);
		  Toast.makeText(getApplicationContext(), "" + data.getData(), Toast.LENGTH_LONG).show();

		  if ( requestCode == SELECT_IMAGE && requestCode == RESULT_OK ) {

			   Uri filebathe = data.getData();

			   no_image_profile.setImageURI(filebathe);


		  }
	 }


	 /**
	  * When user press back button
	  *
	  * @param keyCode
	  * @param event
	  *
	  * @return
	  */
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		  if ( (keyCode == KeyEvent.KEYCODE_BACK) ) {
			   finishAffinity();
			   System.exit(1);
			   return true;
		  }
		  return super.onKeyDown(keyCode, event);
	 }


	 /**
	  * this for menu in nav drawer to get id and What happen When press position ID
	  *
	  * @param item
	  *
	  * @return
	  */
	 @SuppressWarnings("StatementWithEmptyBody")
	 @Override
	 public boolean onNavigationItemSelected(MenuItem item) {

		  int id = item.getItemId();

		  if ( id == R.id.nav_Categories ) {
			   /**
				* When press categoryActivity intent to categoryActivity Activity
				*/
			   startActivity(new Intent(adsActivity.this, categoryActivity.class));

		  } else if ( id == R.id.nav_Offers ) {
			   Toast.makeText(getApplicationContext(), "fb", Toast.LENGTH_LONG).show();

		  } else if ( id == R.id.nav_Favorites ) {
			   Toast.makeText(getApplicationContext(), "fb", Toast.LENGTH_LONG).show();

		  } else if ( id == R.id.share_app ) {

			   final Animation shake = AnimationUtils.loadAnimation(adsActivity.this, R.anim.shake);
			   AlertDialog.Builder alert = new AlertDialog.Builder(adsActivity.this);
			   LayoutInflater layoutInflater = LayoutInflater.from(adsActivity.this);
			   View view1 = layoutInflater.inflate(R.layout.share_application, null);

			   alert.setView(view1);

			   ImageView twitterShare = view1.findViewById(R.id.twitterShareApp);
			   ImageView fbShareApp = view1.findViewById(R.id.fbShareApp);
			   ImageView massShareApp = view1.findViewById(R.id.massShareApp);
			   ImageView whatsShareApp = view1.findViewById(R.id.whatsShareApp);
			   ImageView copyTextShareApp = view1.findViewById(R.id.copyTextShareApp);
			   ImageView otherShareApp = view1.findViewById(R.id.otherShareApp);

			   /**
				*  on Click to Sar By twitterShare
				*/
			   twitterShare.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));
						 try {
							  Intent twitter = new Intent(Intent.ACTION_SEND);
							  twitter.setType("text/plain");
							  twitter.putExtra(Intent.EXTRA_TEXT, "any Description" + "URL Application");
							  twitter.setPackage("advanced.twitter.android");
							  startActivity(twitter);
						 } catch (Exception e) {
							  Toast.makeText(adsActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
						 }

					}
			   });

			   /**
				*  on Click to Sar By fbShareApp
				*/
			   fbShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));

						 Intent FB = new Intent(Intent.ACTION_SEND);
						 FB.setType("text/plain");
						 FB.putExtra(Intent.EXTRA_TEXT, "any Description" + "URL Application");
						 FB.setPackage("com.facebook.katana");
						 startActivity(FB);
					}
			   });

			   /**
				*  on Click to Sar By massShareApp
				*/
			   massShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));

						 Intent sendIntent = new Intent();
						 sendIntent.setAction(Intent.ACTION_SEND);
						 sendIntent
								 .putExtra(Intent.EXTRA_TEXT,
										 "<---YOUR TEXT HERE--->.");
						 sendIntent.setType("text/plain");
						 sendIntent.setPackage("com.facebook.orca");
						 try {
							  startActivity(sendIntent);
						 } catch (android.content.ActivityNotFoundException ex) {
							  Toast.makeText(adsActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
						 }
					}
			   });

			   /**
				*  on Click to Sar By whatsShareApp
				*/
			   whatsShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));

						 Intent WhatsApp = new Intent(Intent.ACTION_SEND);
						 WhatsApp.setType("text/plain");
						 WhatsApp.putExtra(Intent.EXTRA_TEXT, "any Description" + "URL Application");
						 WhatsApp.setPackage("com.whatsapp");
						 startActivity(WhatsApp);
					}
			   });

			   /**
				*  on Click to  copyTextShareApp
				*/
			   copyTextShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));

						 ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
						 ClipData clip = ClipData.newPlainText("label", "Text to copy");
						 if ( clipboard != null ) {
							  clipboard.setPrimaryClip(clip);
						 }
						 Toast.makeText(adsActivity.this, "تم نسخ الرابط", Toast.LENGTH_LONG).show();

					}
			   });

			   /**
				*  on Click to Share By otherShareApp
				*/
			   otherShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(adsActivity.this, R.anim.image_click_animation));
						 try {
							  Intent i = new Intent(Intent.ACTION_SEND);
							  i.setType("text/plain");
							  i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
							  String sAux = "\nLet me recommend you this application\n\n";
							  sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
							  i.putExtra(Intent.EXTRA_TEXT, sAux);
							  startActivity(Intent.createChooser(i, "choose one"));
						 } catch (Exception e) {
							  //e.toString();
						 }
					}
			   });

			   alert.create();
			   alert.show();

		  } else if ( id == R.id.nav_Contact ) {

			   AlertDialog.Builder alert = new AlertDialog.Builder(adsActivity.this);
			   LayoutInflater layoutInflater = LayoutInflater.from(adsActivity.this);
			   View view = layoutInflater.inflate(R.layout.contact_us, null);
			   alert.setView(view);

			   CircleImageView Facebook_mido = view.findViewById(R.id.Facebook_mido);
			   CircleImageView whatsAppMido = view.findViewById(R.id.whatsAppMido);

			   Facebook_mido.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 try {

							  Intent intent = new Intent(Intent.ACTION_VIEW,
									  Uri.parse("https://www.facebook.com/profile.php?id=100015232508613"));
							  startActivity(intent);

						 } catch (Exception e) {

							  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/business/help/community/question/?id=1267743879941243")));

						 }
					}
			   });

			   whatsAppMido.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 final String PhoneNumberwhatsApp = "+201206314232";
						 startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.whatsapp.com/send?phone=+" + PhoneNumberwhatsApp)));

					}
			   });
			   alert.create();
			   alert.show();

		  } else if ( id == R.id.nav_LogOut ) {
			   /**
				* this to logout in facebook && google
				*/
//                mAuth.signOut();
//                LoginManager.getInstance().logOut();
//                updateUI();
			   /**
				*  Log out use shared Preference
				*/

			   getSharedPreferences("LoginRememberMe", MODE_PRIVATE)
					   .edit()
					   .clear()
					   .commit();
			   TastyToast.makeText(getApplicationContext(), " تم تسجيل الخروج  ", TastyToast.LENGTH_LONG, TastyToast.INFO);
			   startActivity(new Intent(adsActivity.this, loginActivity.class));
		  }

		  DrawerLayout drawer = findViewById(R.id.drawer_layout);
		  drawer.closeDrawer(GravityCompat.START);
		  return true;
	 }

	 /**
	  * facebook
	  */
	 @Override
	 public void onStart() {
		  super.onStart();
		  // Check if user is signed in (non-null) and update UI accordingly.
		  //   FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser == null) {
//            updateUI();
//        }


	 }

	 private void updateUI() {
		  TastyToast.makeText(getApplicationContext(), " تم تسجيل الخروج  ", TastyToast.LENGTH_LONG, TastyToast.INFO);

		  startActivity(new Intent(adsActivity.this, LoginMethodActivity.class));
	 }


	 /**
	  * Create Method to set AdapterArrayClass
	  */
	 private void showDataOffers() {

		  mAdapterOffers42 = new adapterAds(this, items);

		  lst.setAdapter(mAdapterOffers42);
		  lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			   	 /// not now

			   }
		  });

	 }


}
