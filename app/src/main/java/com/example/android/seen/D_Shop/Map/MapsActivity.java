package com.example.android.seen.D_Shop.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.seen.D_Shop.shopActivity;
import com.example.android.seen.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

	 private GoogleMap mMap;


	 // instance from currentLocationGPS to get current location user
	 currentLocationGPS currentLocationGps = new currentLocationGPS(MapsActivity.this);

	 // make circle of current location user
	 double LatCurrentLocationUser = currentLocationGps.getLatitude();
	 double LongCurrentLocationUser = currentLocationGps.getLongitude();

	 double LatShop = Double.parseDouble(shopActivity.item_shops62.getProfile_Lat_Map());
	 double LongShop = Double.parseDouble(shopActivity.item_shops62.getProfile_Long_Map());


	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Bar();
		  setContentView(R.layout.activity_maps);
		  // Obtain the SupportMapFragment and get notified when the map is ready to be used.
		  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				  .findFragmentById(R.id.map);
		  mapFragment.getMapAsync(this);
	 }

	 // whine map is ready
	 @Override
	 public void onMapReady(GoogleMap googleMap) {
		  mMap = googleMap;


		  /**
		   * to get current location user
		   */
		  LatLng mapPlace = new LatLng(currentLocationGps.getLatitude(), currentLocationGps.getLongitude());
		  /**
		   *  check user is tern location or not
		   *      if not
		   *            show dialog to open location
		   */
		  mMap.addMarker(new MarkerOptions().position(mapPlace)
				  .icon(BitmapDescriptorFactory.fromResource(R.drawable.plac))
				  .title(shopActivity.item_shops62.getShopTitle()));
		  mMap.moveCamera(CameraUpdateFactory.newLatLng(mapPlace));

		  // for add cercile

		  if ( shopActivity.item_shops62.getProfile_Lat_Map() != null ) {

			   //for circle current location
//
//			   Polygon polygon = mMap.addPolygon(new PolygonOptions()
//					   .add(new LatLng(LatCurrentLocationUser, LongCurrentLocationUser), new LatLng(LatShop, LongShop))
//					   .strokeWidth(25)
//					   .strokeColor(Color.RED)
//					   .geodesic(true));
//
//			   Circle circle = mMap.addCircle(new CircleOptions()
//					   .center(new LatLng(LatCurrentLocationUser, LongCurrentLocationUser))
//					   .radius(500)
//					   .strokeColor(Color.RED)
//					   .fillColor(R.color.map));


			   // map design
			   mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

			   // control in map
			   mMap.getUiSettings().setZoomControlsEnabled(true);

			   // Add a marker in cameraMap
			   mapPlace = new LatLng(LatShop, LongShop);
			   mMap.addMarker(new MarkerOptions()
					   .position(mapPlace)
					   .title(shopActivity.item_shops62.getShopTitle()))
					   .showInfoWindow();
			   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapPlace, 9));

		  } else {

			   // when lat && long is null from DB
			   AlertDialog.Builder al = new AlertDialog.Builder(MapsActivity.this);
			   al.setMessage("خطا"
					   + "\n" +
					   "هذا المحل لم يتم تسجيل له خريطه بعد...")
					   .setNegativeButton("تمام", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								 startActivity(new Intent(MapsActivity.this, shopActivity.class));
							}
					   });
			   al.create();
			   al.show();
		  }


	 }

	 /**
	  * method do show alert dialog to open location
	  */
//	 public void showSettingAlert() {
//		  AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapsActivity.this);
//		  alertDialog.setTitle("GPS setting!");
//		  alertDialog.setMessage("GPS is not enabled, Do you want to go to settings menu? ");
//		  alertDialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
//			   @Override
//			   public void onClick(DialogInterface dialog, int which) {
//					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//					MapsActivity.this.startActivity(intent);
//			   }
//		  });
//		  alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//			   @Override
//			   public void onClick(DialogInterface dialog, int which) {
//					dialog.cancel();
//			   }
//		  });
//		  alertDialog.show();
//	 }

	 /**
	  * to show action bar in mini api device  kitkat and lollipop
	  */
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
