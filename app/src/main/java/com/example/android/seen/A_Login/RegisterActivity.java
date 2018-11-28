package com.example.android.seen.A_Login;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.seen.DB.Database;
import com.example.android.seen.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import java.sql.Connection;

public class RegisterActivity extends AppCompatActivity {

	 /**
	  * Variables
	  */
	 EditText mTxtUserNameRegister, mTxtPasswordRegister, mTxtEmail;
	 Button mBtnRegister;
	 final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Bar();
		  setContentView(R.layout.register_activity);

		  /**
		   * initialization Variables
		   */
		  mTxtUserNameRegister = findViewById(R.id.TxtUserNameRegister);
		  mTxtPasswordRegister = findViewById(R.id.TxtPasswordRegister);
		  mTxtEmail = findViewById(R.id.TxtEmailRegister);
		  mBtnRegister = findViewById(R.id.btnRegister);

		  /**
		   * -------------------------------------------------- Start Coding -------------------------
		   */
		  /**
		   *
		   */
		  /**
		   * set on click listener for button RegisterActivity
		   */
		  mBtnRegister.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
					/**
					 * check the user name is fill or not
					 */
					if ( mTxtUserNameRegister.getText().toString().isEmpty() ) {
						 mTxtUserNameRegister.setError("يجب ادخال الاسم");
						 mTxtUserNameRegister.requestFocus();
						 Toasty();
					}
					/**
					 * check the user name is fill or not
					 */
					else if ( mTxtPasswordRegister.getText().toString().isEmpty() ) {
						 mTxtPasswordRegister.setError("يجب ادخال كلمه المرور");
						 mTxtPasswordRegister.requestFocus();
						 Toasty();
					}

					/**
					 * check the email address is fill or not
					 */
					else if ( mTxtEmail.getText().toString().isEmpty() ) {
						 mTxtEmail.setError("يجب ادخال البريد الالكترونى");
						 mTxtEmail.requestFocus();
						 Toasty();
					}
					/**
					 * if all data is successfully
					 * save data in database server
					 * and intent to login activity to complete
					 */
					else if ( mTxtEmail.getText().toString().matches(emailPattern) ) {

						 /**
						  * Make a connection to the database
						  */
						 Database DB = new Database();
						 Connection coon = DB.ConnectDB(RegisterActivity.this);

						 /**
						  * check the internet is disable or not
						  */
						 if ( coon == null ) {
							  AlertError("يرجى التحقق من اتصالك بالانترت");

						 } else {
							  /**
							   * save data in database
							   */

							  String message = DB.RunDML("insert into Register values  ('" + mTxtUserNameRegister.getText() + "' ,'" + mTxtPasswordRegister.getText() + "' ,'" + mTxtEmail.getText() + "') ");
							  /**
							   * if message.return == "ok"
							   * data is save in database
							   */
							  if ( message.equals("ok") ) {
								   TastyToast.makeText(getApplicationContext(), "تم تسجيل بياناتك بنجاح", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
								   /**
									* intent to login Activity
									*/
								   startActivity(new Intent(RegisterActivity.this, loginActivity.class));
							  } else {
								   /**
									* when get error
									*/
								   /**------------------------------------------*/
								   /**
									* if user name is duplicate
									*/
								   if ( message.contains("PK_Regiter") ) {

										AlertError("عفوا اسم المستخدم مسجل من قبل "
												+ "\n"
												+ mTxtUserNameRegister.getText() +
												"\n"
												+ "   يمكنك اضافه ارقام او اسم شهره بجانب اسمك  ");
								   } else {
										/**
										 * for any error
										 */
										TastyToast.makeText(getApplicationContext(), "Error" + message, TastyToast.LENGTH_LONG, TastyToast.ERROR);
								   }
							  }
						 }

					}

					/**
					 * check the email address as Pattern or not
					 */
					else {
//
						 mTxtEmail.setError(" بريدك الالكترونى غير صحيح \n  هكذا يجب ادخال البريد الالكترونى \n " +
								 "example@domainName.com ");
						 mTxtEmail.requestFocus();

					}
			   }
		  }); /** End On Click listener -------------------------------------------------------- */


	 }       /** End onCreate --------------------------------------------------------------------------------- */

	 /**
	  * this a method Toast Animation for Display error
	  */
	 public void Toasty() {
		  TastyToast.makeText(getApplicationContext(), "هذا الحقل مطلوب", TastyToast.LENGTH_LONG, TastyToast.ERROR);
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

	 private void AlertError(String msgError) {

		  AlertDialog.Builder al = new AlertDialog.Builder(RegisterActivity.this);
		  al.setMessage(msgError);
		  al.setNegativeButton("حاول مره اخرى ", null);
		  al.create();
		  al.show();

	 }

}// End class  ---------------------------------------------------------------------------------
