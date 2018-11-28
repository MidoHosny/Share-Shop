package com.example.android.seen.A_Login;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.seen.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

public class SplashScreenBeforeLogin extends AppCompatActivity {

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Bar();
		  setContentView(R.layout.splash_activity);

		  /**
		   * For Make Simple Toast Animation
		   */
		  TastyToast.makeText(getApplicationContext(), "Welcome", TastyToast.LENGTH_LONG, TastyToast.DEFAULT)
				  .setGravity(Gravity.BOTTOM | Gravity.BOTTOM, 0, 0);
		  // this a SplashScreenBeforeLogin Activity Home
		  // This Display Finish after 3 second

		  new Handler().postDelayed(new Runnable() {
			   @Override
			   public void run() {

					Intent i = new Intent(SplashScreenBeforeLogin.this, LoginMethodActivity.class);
					startActivity(i);

					finish();
			   }
		  }, 3000); /** Number Of Second Until go the Activity loginActivity */
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

