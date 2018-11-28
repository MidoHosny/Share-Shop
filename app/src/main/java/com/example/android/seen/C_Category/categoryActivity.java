package com.example.android.seen.C_Category;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.seen.A_Login.LoginMethodActivity;
import com.example.android.seen.A_Login.loginActivity;
import com.example.android.seen.B_Ads.adsActivity;
import com.example.android.seen.R;
import com.example.android.seen.D_Shop.shopActivity;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class categoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	 /**
	  * initialize var
	  */


	 CircleImageView no_image_profile;
	 ProgressBar progressBarCategory;
	 private TextView txtLoginCategory;

	 public static String CategoryID = null;
	 com.example.android.seen.C_Category.itemCategory itemCategory;
	 adapterCategory mAdapterCategory;
	 GridView lst;
	 getCategoryDB mGet_Category_FromDB51 = new getCategoryDB();
	 ImageView imag;
	 SwipeRefreshLayout mSwipeRefreshLayout;
	 ArrayList<com.example.android.seen.C_Category.itemCategory> items;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.category_activity);
 
		  /**
		   * initialize SwipeRefreshLayout
		   */
		  progressBarCategory = findViewById(R.id.progressBarCategory);
		  txtLoginCategory = findViewById(R.id.txtLoging_Category);

		  lst = findViewById(R.id.GradViewCategory);

		  /**
		   * find view by id for a tool bar category
		   */
		  Toolbar toolbar = findViewById(R.id.toolbar_category);
		  setSupportActionBar(toolbar);

		  /**
		   * use method showDataCategory
		   */
		  new Handler().postDelayed(new Runnable() {
			   @Override
			   public void run() {

					items = new ArrayList<>(mGet_Category_FromDB51.GetAllCategory(categoryActivity.this));
					showDataCategory();
					progressBarCategory.setVisibility(View.GONE);
					txtLoginCategory.setVisibility(View.GONE);

			   }
		  }, 1000);

		  /**
		   * find view for layout drawer to open menu bar
		   */
		  DrawerLayout drawer = findViewById(R.id.drawer_layout_category);
		  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		  drawer.addDrawerListener(toggle);
		  toggle.syncState();

		  /**
		   * to set navigation in menu
		   */
		  NavigationView navigationView = findViewById(R.id.nav_view_category);

		  View headerview = navigationView.getHeaderView(0);
		  no_image_profile = headerview.findViewById(R.id.no_image_profile);

		  TextView profile_name = headerview.findViewById(R.id.profile_name);


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
		   *  this to Refresh Data
		   */
		  mSwipeRefreshLayout = findViewById(R.id.swipeCategory);
		  mSwipeRefreshLayout.setColorSchemeColors
				  (getResources().getColor(R.color.blue)
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

					}, 2000);

			   }
		  });


	 }  /** ***************************** End OnCreate *****************************/

	 /**
	  * When user press back button
	  */
	 @Override
	 public void onBackPressed() {
		  startActivity(new Intent(categoryActivity.this, adsActivity.class));
	 }


	 /**
	  * this for menu in nav drawer to get id and what happen When press position
	  *
	  * @param item
	  *
	  * @return
	  */
	 @SuppressWarnings("StatementWithEmptyBody")
	 @Override
	 public boolean onNavigationItemSelected(MenuItem item) {
		  // Handle navigation view item clicks here.
		  int id = item.getItemId();

		  if ( id == R.id.nav_Categories ) {
			   // Handle the camera action

		  } else if ( id == R.id.nav_Offers ) {

			   /**
				* When press adsActivity intent to adsActivity Activity
				*/
			   startActivity(new Intent(categoryActivity.this, adsActivity.class));
		  } else if ( id == R.id.nav_Favorites ) {

		  } else if ( id == R.id.share_app ) {
			   final Animation shake = AnimationUtils.loadAnimation(categoryActivity.this, R.anim.shake);
			   AlertDialog.Builder alert = new AlertDialog.Builder(categoryActivity.this);
			   LayoutInflater layoutInflater = LayoutInflater.from(categoryActivity.this);
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
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));
						 try {
							  Intent twitter = new Intent(Intent.ACTION_SEND);
							  twitter.setType("text/plain");
							  twitter.putExtra(Intent.EXTRA_TEXT, "any Description" + "URL Application");
							  twitter.setPackage("advanced.twitter.android");
							  startActivity(twitter);
						 } catch (Exception e) {
							  Toast.makeText(categoryActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
						 }

					}
			   });

			   /**
				*  on Click to Sar By fbShareApp
				*/
			   fbShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));

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
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));

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
							  Toast.makeText(categoryActivity.this, "عفوا ليس لديك هذا البرنامج", Toast.LENGTH_LONG).show();
						 }
					}
			   });

			   /**
				*  on Click to Sar By whatsShareApp
				*/
			   whatsShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));

						 Intent WhatsApp = new Intent(Intent.ACTION_SEND);
						 WhatsApp.setType("text/plain");
						 WhatsApp.putExtra(Intent.EXTRA_TEXT, "any Description" + "URL Application");
						 WhatsApp.setPackage("com.whatsapp");
						 startActivity(WhatsApp);
					}
			   });

			   /**
				*  on Click to Sar By copyTextShareApp
				*/
			   copyTextShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));

						 ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
						 ClipData clip = ClipData.newPlainText("label", "Text to copy");
						 clipboard.setPrimaryClip(clip);
						 Toast.makeText(categoryActivity.this, "تم نسخ الرابط", Toast.LENGTH_LONG).show();

					}
			   });

			   /**
				*  on Click to Sar By otherShareApp
				*/
			   otherShareApp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						 v.startAnimation(AnimationUtils.loadAnimation(categoryActivity.this, R.anim.image_click_animation));
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

			   AlertDialog.Builder alert = new AlertDialog.Builder(categoryActivity.this);
			   LayoutInflater layoutInflater = LayoutInflater.from(categoryActivity.this);
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
//            mAuth.signOut();
//            LoginManager.getInstance().logOut();
			   //  updateUI();

			   getSharedPreferences("LoginRememberMe", MODE_PRIVATE)
					   .edit()
					   .clear()
					   .commit();
			   TastyToast.makeText(getApplicationContext(), " تم تسجيل الخروج  ", TastyToast.LENGTH_LONG, TastyToast.INFO);
			   startActivity(new Intent(categoryActivity.this, loginActivity.class));

		  }

		  DrawerLayout drawer = findViewById(R.id.drawer_layout_category);
		  drawer.closeDrawer(GravityCompat.START);
		  return true;
	 }

	 /**
	  * method for logout account and intent to loginMethod Activity
	  */
	 private void updateUI() {
		  TastyToast.makeText(getApplicationContext(), " تم تسجيل الخروج  ", TastyToast.LENGTH_LONG, TastyToast.INFO);

		  startActivity(new Intent(categoryActivity.this, LoginMethodActivity.class));
	 }


	 /**
	  * Create List to set AdapterArrayClass
	  */
	 private void showDataCategory() {

		  mAdapterCategory = new adapterCategory(this, items);

		  lst.setAdapter(mAdapterCategory);

		  lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click_animation));

					Intent intent = new Intent(categoryActivity.this, shopActivity.class);
					itemCategory = items.get(position);

					CategoryID = itemCategory.CategoryID;

					startActivity(intent);

			   }
		  });


	 }

	 public void anim(ImageView imageView, Context context) {
		  final Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
		  imageView.startAnimation(shake);

	 }

}
