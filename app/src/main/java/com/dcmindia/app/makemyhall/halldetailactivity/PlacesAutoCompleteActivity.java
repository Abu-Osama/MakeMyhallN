package com.dcmindia.app.makemyhall.halldetailactivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.PluginStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.dcmindia.app.makemyhall.DetailsActivity;
import com.dcmindia.app.makemyhall.HomeActivity;
import com.dcmindia.app.makemyhall.R;
import com.dcmindia.app.makemyhall.adapters.PlacesAutoCompleteAdapter;
import com.dcmindia.app.makemyhall.listeners.RecyclerItemClickListener;
import com.dcmindia.app.makemyhall.service.GPSTracker;
import com.dcmindia.app.makemyhall.utility.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class PlacesAutoCompleteActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    protected GoogleApiClient mGoogleApiClient;

    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));

    Context context;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    static final Integer GPS_SETTINGS = 0x7;
    static final Integer LOCATION = 0x1;
    GPSTracker gps;
    private EditText mAutocompleteView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    ImageView delete;
    TextView nearbylocation;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    Location location;
    Spinner spinner;
    private static final String[] paths = {"Select Category", "MarriageHall", "PartyHall",
            "MeetingHall", "ConferenceHall", "ExhibitionHall", "EventHall"};

    Double MyLat, MyLong;
    String CityName = "";
    String StateName = "";
    String CountryName = "";
    String add = "";
    String latilongi;
    String lat, lon, text;
    TextView backbutton,homebtn;
    Button searchcat;

    LocationManager locationManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        setContentView(R.layout.activity_search);
        backbutton= (TextView) findViewById(R.id.backbtn1);
        homebtn= (TextView) findViewById(R.id.homebtn1);
        searchcat= (Button) findViewById(R.id.searchbtn);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 , PlacesAutoCompleteActivity.this)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        final boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);


        spinner = (Spinner) findViewById(R.id.spinnernew1);
        spinner.setPrompt("All");
        //spinner.setBackgroundColor(Color.BLACK);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlacesAutoCompleteActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spinner.getSelectedItemPosition() == 0) {


                } else {

                    text = spinner.getSelectedItem().toString();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mAutocompleteView = (EditText) findViewById(R.id.autocomplete_places);

        delete = (ImageView) findViewById(R.id.cross);
        nearbylocation = (TextView) findViewById(R.id.locationnear);


        mAutoCompleteAdapter = new PlacesAutoCompleteAdapter(this, R.layout.searchview_adapter,
                mGoogleApiClient, BOUNDS_INDIA, null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAutoCompleteAdapter);
        delete.setOnClickListener(this);
        //getMyCurrentLocation();


        //call asynctak class here check the


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),HomeActivity.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),HomeActivity.class);
                startActivity(intent);

            }
        });


        searchcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAutocompleteView.length()==0 ||spinner.getSelectedItemPosition()==0) {

                    Toast.makeText(PlacesAutoCompleteActivity.this, "Fill the data", Toast.LENGTH_SHORT).show();

                } else
                {
                    String s = latilongi.toString();
                    String[] latLng = s.substring(10, s.length() - 1).split(",");
                    lat = latLng[0];
                    lon = latLng[1];
                   // Toast.makeText(getApplication(), "Please Wait ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat",text);
                    startActivity(intent);
                }
            }
        });

        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);




        nearbylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager==null) {

                    try {
                        if (isPermissionGranted()) {
                            getMyCurrentLocation();
                            call_action();
                        } else {

                            showGPSDisabledAlertToUser();
                        }
                    }
                    catch (Exception e){

                    }

                    }


                }
        });

        mAutocompleteView.setClickable(true);
        mAutocompleteView.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (!s.toString().equals("") && mGoogleApiClient.isConnected()||s.length()>0) {
                    mAutoCompleteAdapter.getFilter().filter(s.toString());
                    delete.setVisibility(View.VISIBLE);


                } else if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag, Constants.API_NOT_CONNECTED);


                }

                  else {

                    delete.setVisibility(View.INVISIBLE);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
                                    // Toast.makeText(getApplicationContext(), String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                    //mAutocompleteView= (EditText) places.get(0).getAddress();

                                    latilongi = String.valueOf(places.get(0).getLatLng());

                                    mAutocompleteView.setText(places.get(0).getAddress() + "");
                                    //mAutocompleteView.setEllipsize(TextUtils.TruncateAt.END);


                                } else {
                                    Toast.makeText(getApplicationContext(), Constants.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }

                                places.release();
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );
    }


    public  void getMyCurrentLocation() {


        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();


        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        //don't start listeners if no provider is enabled
        //if(!gps_enabled && !network_enabled)
        //return false;

        if (gps_enabled) {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);

        }


        if (gps_enabled) {
            location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        }


        if (network_enabled && location == null) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);

        }


        if (network_enabled && location == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }

        if (location != null) {

            MyLat = location.getLatitude();
            MyLong = location.getLongitude();
            //LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

            //latilongi= String.valueOf(latLng);


        } else {
            Location loc = getLastKnownLocation(this);
            if (loc != null) {

                MyLat = loc.getLatitude();
                MyLong = loc.getLongitude();



            }
        }
        locManager.removeUpdates(locListener); // removes the periodic updates from location listener to //avoid battery drainage. If you want to get location at the periodic intervals call this method using //pending intent.

        try {
// Getting address from found locations.
            Geocoder geocoder;

            List<Address> addresses;
           // locManager.requestLocationUpdates(bestProvider, 20000, 1, this);
            geocoder = new Geocoder(getApplication(), Locale.getDefault());
            addresses = geocoder.getFromLocation(MyLat, MyLong, 1);

            StateName = addresses.get(0).getAdminArea();
            CityName = addresses.get(0).getLocality();
            CountryName = addresses.get(0).getCountryName();
            add=addresses.get(0).getAddressLine(0);


            // you can get more details other than this . like country code, state code, etc.


            System.out.println(" StateName " + StateName);
            System.out.println(" CityName " + CityName);
            System.out.println(" CountryName " + CountryName);
        } catch (Exception e) {
            e.printStackTrace();
        }

          //lat= String.valueOf(MyLat);
          //= String.valueOf(MyLong);
          LatLng latLng=new LatLng(MyLat,MyLong);

          latilongi= String.valueOf(latLng);
//
//        textView2.setText(""+MyLat);
//        textView3.setText(""+MyLong);
//        textView1.setText(" StateName " + StateName +" CityName " + CityName +" CountryName " + CountryName);
          mAutocompleteView.setText(add);
    }

    // Location listener class. to get location.
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            if (location != null) {
            }
        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

    public Location getLastKnownLocation(Context context) {
        Location location = null;
        LocationManager locationmanager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        List list = locationmanager.getAllProviders();
        boolean i = false;
        Iterator iterator = list.iterator();
        do {
            //System.out.println("---------------------------------------------------------------------");
            if (!iterator.hasNext())
                break;
            String s = (String) iterator.next();
            //if(i != 0 && !locationmanager.isProviderEnabled(s))
            if (i != false && !locationmanager.isProviderEnabled(s))
                continue;
            // System.out.println("provider ===> "+s);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            Location location1 = locationmanager.getLastKnownLocation(s);
            if (location1 == null)
                continue;
            if (location != null) {
                //System.out.println("location ===> "+location);
                //System.out.println("location1 ===> "+location);
                float f = location.getAccuracy();
                float f1 = location1.getAccuracy();
                if (f >= f1) {
                    long l = location1.getTime();
                    long l1 = location.getTime();
                    if (l - l1 <= 600000L)
                        continue;
                }
            }
            location = location1;
            // System.out.println("location  out ===> "+location);
            //System.out.println("location1 out===> "+location);
            i = locationmanager.isProviderEnabled(s);
            // System.out.println("---------------------------------------------------------------------");
        } while (true);
        return location;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    private boolean isEmpty(EditText mAutocompleteView) {
        return mAutocompleteView.getText().toString().trim().length() == 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.v("Google API Callback", "Connection Done");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Google API Callback", "Connection Suspended");
        Log.v("Code", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.v("Google API Callback","Connection Failed");
        Log.v("Error Code", String.valueOf(connectionResult.getErrorCode()));
       // Toast.makeText(this, Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v==delete){
            mAutocompleteView.setText("");
            delete.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()){
            Log.v("Google API","Connecting");
            mGoogleApiClient.connect();


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            Log.v("Google API","Dis-Connecting");
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void call_action(){

        //Intent callIntent=new Intent(Intent.ACTION_DIAL);
//        callIntent.setData(Uri.parse("tel:"+mobnumnerhall));
        if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.checkSelfPermission(getApplication(),Manifest.permission.ACCESS_FINE_LOCATION)
                    !=PackageManager.PERMISSION_GRANTED)
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
        }
        //startActivity(callIntent);

    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
