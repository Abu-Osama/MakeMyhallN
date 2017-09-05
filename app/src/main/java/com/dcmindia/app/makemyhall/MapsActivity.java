package com.dcmindia.app.makemyhall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


public class MapsActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {
    private static final String LOG_TAG = "MainActivity";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView;
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mIdTextView;
    private TextView mPhoneTextView;
    private TextView mWebTextView;
    private TextView mAttTextView;
    Button search,backbutton,homebutton;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    String latilongi;
    String lat,lon,text;
    ImageView locationbtn;
    Spinner spinner;


//    RecyclerView recyclerView;  //destination
//    MyRecyclerAdapter myRecyclerAdapter;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));

    private static final String[]paths = {"All category","MarriageHall", "PartyHall","MeetingHall",
            "ConferenceHall","ExhibitionHall","EventHall"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        backbutton= (Button) findViewById(R.id.backbtn);
        homebutton= (Button) findViewById(R.id.homebtn);
        locationbtn= (ImageView) findViewById(R.id.locationbtn);
//        locationbtn.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//                LocationManager mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                if (!mgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    Toast.makeText(getApplication(), "GPS is disabled!", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),HomeActivity.class);
                startActivity(intent);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),HomeActivity.class);
                startActivity(intent);

            }
        });

        spinner= (Spinner) findViewById(R.id.spinner);
        spinner.setPrompt("All");
        spinner.setBackgroundColor(Color.BLACK);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MapsActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                    text =null;

                }else {

                    text = spinner.getSelectedItem().toString();
                    //String item=spinner.getSelectedItemPosition(""+position);
                    // String provider = spinner.getSelectedItem().toString();
                    //String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MapsActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search= (Button) findViewById(R.id.serachhalllocation);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAutocompleteTextView.length()==0) {
                    Toast.makeText(MapsActivity.this, "Fill the location", Toast.LENGTH_SHORT).show();

                } else
                {
                    String s = latilongi.toString();
                    String[] latLng = s.substring(10, s.length() - 1).split(",");
                    lat = latLng[0];
                    lon = latLng[1];
                    Toast.makeText(getApplication(), "Please Wait ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat",text);
                    startActivity(intent);
                }
            }
        });
        mGoogleApiClient = new GoogleApiClient.Builder(MapsActivity.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();
        mAutocompleteTextView = (AutoCompleteTextView) findViewById(R.id
                .autoCompleteTexxtView);
        mAutocompleteTextView.setThreshold(1);
//
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
         @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();
             latilongi=place.getLatLng().toString();

            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }

            places.release();
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }
}
