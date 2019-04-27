package com.example.android.seen.A_Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.seen.DB.Database;
import com.example.android.seen.B_Ads.adsActivity;
import com.example.android.seen.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.roger.catloadinglibrary.CatLoadingView;
import com.sdsmdg.tastytoast.TastyToast;

import javax.mail.PasswordAuthentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class loginActivity extends AppCompatActivity {
	 /**
	  * Variables
	  */
	 EditText Email_forget;
	 CheckBox checkBox;
	 private ProgressBar pgsBar;
	 TextView mRegisterInLoginActivity, forget_password;
	 Button mbtnLogin;
	 public static EditText mTxtUserNameLogin, mTxtPasswordLogin;


	 /**
	  * for ProgressBar animation
	  */
	 CatLoadingView mView;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Bar();
		  setContentView(R.layout.login_activity);

		  /** ----------------------------------------------------------------------------------------*/


		  /**
		   * initialization Variables
		   */
		  forget_password = findViewById(R.id.forget_password);
		  checkBox = findViewById(R.id.checkBox);
		  pgsBar = findViewById(R.id.pBar);
		  mRegisterInLoginActivity = findViewById(R.id.textViewNewRegister);
		  mbtnLogin = findViewById(R.id.btnLogin);

		  mTxtUserNameLogin = findViewById(R.id.TxtUserNameLogin);
		  mTxtPasswordLogin = findViewById(R.id.TxtPasswordLogin);

		  /**
		   *  for ProgressBar animation
		   */
		  mView = new CatLoadingView();

		  /**
		   * -------------------------------------------------- Start Coding -------------------------
		   */
		  /**
		   *  Get shared preference
		   */

		  SharedPreferences ReadeShared = getSharedPreferences("LoginRememberMe", MODE_PRIVATE);

		  String ReadeUserName = ReadeShared.getString("UserName", null);
		  String Readepass = ReadeShared.getString("Pass", null);

		  if ( ReadeUserName != null && Readepass != null ) {

			   startActivity(new Intent(loginActivity.this, adsActivity.class));
		  }

		  /**
		   *
		   *   Forget Password
		   */
		  forget_password.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {

					AlertDialog.Builder alert = new AlertDialog.Builder(loginActivity.this);
					LayoutInflater layoutInflater = LayoutInflater.from(loginActivity.this);
					View view = layoutInflater.inflate(R.layout.forget_password, null);
					alert.setView(view);
					////////////////////

					Email_forget = view.findViewById(R.id.Email_forget);
					Button sendPassword = view.findViewById(R.id.btn_send_password);

					sendPassword.setOnClickListener(new View.OnClickListener() {
						 @Override
						 public void onClick(View v) {
							  if ( Email_forget.getText().toString().isEmpty() ) {

								   AlertError("يجب كتابه البريد الالكترونى ");
								   Email_forget.requestFocus();
							  } else {
								   Database DB = new Database();
								   Connection connection = DB.ConnectDB(loginActivity.this);


								   if ( connection == null ) {

										AlertError("لا يوجد اتصال بالانترنت ");

								   } else {
										ResultSet resultSet = DB.Search
												("select * from Register where Email = '" + Email_forget.getText().toString() + "'");
										try {
											 if ( resultSet.next() ) {
												  /**
												   *  Get data from Table Database
												   */
												  final String name = resultSet.getString(1);
												  final String pass = resultSet.getString(2);

												  /**
												   *  Send Email to user contains Password
												   */

												  new Thread(new Runnable() {
													   @Override
													   public void run() {
															try {
																 final String username = "name@company.com";
																 final String password = "********";
																 Properties props = new Properties();
																 props.put("mail.smtp.auth", "true");
																 props.put("mail.smtp.starttls.enable", "true");
																 props.put("mail.smtp.host", "smtp.gmail.com");
																 props.put("mail.smtp.port", "587");

																 Session session = Session.getInstance(props,
																		 new Authenticator() {

																			  protected PasswordAuthentication getPasswordAuthentication() {
																				   return new PasswordAuthentication(username, password);
																			  }
																		 });
																 try {
																	  javax.mail.Message message = new MimeMessage(session);
																	  message.setFrom(new InternetAddress("midohosny2250@gmail.com"));
																	  message.setRecipients(javax.mail.Message.RecipientType.TO,
																			  InternetAddress.parse(Email_forget.getText().toString()));
																	  message.setSubject("Forget Password");
																	  message.setText(" مرحبا بك " +
																			  "\n" +
																			  name +
																			  "\n " +
																			  "الباسورد الخاص بك    "
																			  + pass);
																	  Transport.send(message);

																	  AlertDialog.Builder al = new AlertDialog.Builder(loginActivity.this);
																	  al.setTitle("متنساش الباسورد تانى يا "
																			  +
																			  name);
																	  al.setMessage("الباسورد اتبعت على ايميلك");
																	  al.setNegativeButton("تمام ", null);
																	  al.create();
																	  al.show();

																 } catch (MessagingException e) {
																	  throw new RuntimeException(e);
																 }
															} catch (Exception ex) {
																 ex.printStackTrace();
															}
													   }
												  }).start();

											 } else {

												  AlertError("البريد الالكترونى غير صحيح ");

											 }
										} catch (SQLException e) {
											 e.printStackTrace();
										}
								   }
							  }
						 }
					});
					alert.create();
					alert.show();
			   }
		  });

		  /**
		   /**
		   * on click loginActivity to intent Home Activity
		   */
		  mbtnLogin.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
					/**
					 *  Connect Database
					 */
					Database DB = new Database();
					Connection conn = DB.ConnectDB(loginActivity.this);
					/**
					 * check the internet connection
					 */
					if ( conn == null ) {

						 AlertError("يرجى التحقق من اتصالك بالانترت");

					} else {
						 /**
						  * Use the search method
						  */
						 ResultSet resultSet = DB.Search("select * From Register  where  UserName='" + mTxtUserNameLogin.getText() + "' and Password='" + mTxtPasswordLogin.getText() + "'");
						 /**
						  * check if user name and password in database
						  */

						 try {

							  if ( resultSet.next() ) {

								   /**
									*  make remember me use shared preference
									*/
								   if ( checkBox.isChecked() ) {
										getSharedPreferences("LoginRememberMe", MODE_PRIVATE)
												.edit()
												.putString("UserName", mTxtUserNameLogin.getText().toString())
												.putString("Pass", mTxtPasswordLogin.getText().toString())
												.commit();

								   }
								   /**
									* animation progressbar
									*/
								   showDialog();
								   /**
									* intent home activity after 4 seconds
									*/
								   new Handler().postDelayed(new Runnable() {
										@Override
										public void run() {

											 TastyToast.makeText(getApplicationContext(), "  مرحبا  " + mTxtUserNameLogin.getText(), TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
											 startActivity(new Intent(loginActivity.this, adsActivity.class));

										}
								   }, 2000); /** Number Of Second Until go the Activity loginActivity */


							  } else {
								   /**
									* make toast to know user is not save in database
									* Or UserName Or Password invalid
									*/
								   AlertError("عفوا اسم المستخدم او كلمه المرور خطا");

							  }
						 } catch (SQLException e) {
							  e.printStackTrace();
						 }
					}
			   }
		  });

		  /**
		   * on click to intent RegisterActivity Activity
		   */
		  mRegisterInLoginActivity.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {

					startActivity(new Intent(loginActivity.this, RegisterActivity.class));
			   }
		  });


	 }

	 private void AlertError(String msgError) {

		  AlertDialog.Builder al = new AlertDialog.Builder(loginActivity.this);
		  al.setMessage(msgError);
		  al.setNegativeButton("حاول مره اخرى ", null);
		  al.create();
		  al.show();

	 }
	 /** End On Create -----------------------------------------------------------------------*/


	 /**
	  * method library for progressbar
	  */
	 public void showDialog() {

		  mView.show(getSupportFragmentManager(), "");
	 }


	 /**
	  * for printHashKey FaceBook Login
	  */
//    public void printHashKey() {
//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.android.seen",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(),
//                        Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//
//        }
//    }
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
} /**
 * End Class -----------------------------------------------------------------------
 */
