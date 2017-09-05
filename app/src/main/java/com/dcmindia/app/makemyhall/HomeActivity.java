package com.dcmindia.app.makemyhall;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcmindia.app.makemyhall.halldetailactivity.PlacesAutoCompleteActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText placeedit;

    private String lat="12.9716";
    private String lon="77.5946";

    ImageView cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,
            cat12,cat13,cat14,cat15,cat16,cat17,cat18,cat19,cat20,cat21,cat22,cat23;
    private String category1,category2,category3,category4,category5,category6;
    TextView weddingbutton,weddingbtm2,weddingbtm3,weddingbtm4,weddingbtm5,weddingbtm6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        placeedit = (EditText) findViewById(R.id.place_edit);


        cat1= (ImageView) findViewById(R.id.cat1);
        cat2= (ImageView) findViewById(R.id.cat2);
        cat3= (ImageView) findViewById(R.id.cat3);
        cat4= (ImageView) findViewById(R.id.cat4);
        cat5= (ImageView) findViewById(R.id.cat5);
        cat6= (ImageView) findViewById(R.id.cat6);
        cat7= (ImageView) findViewById(R.id.more);

        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });

        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MoreServices.class);
                startActivity(intent);
            }
        });


        weddingbutton= (TextView)findViewById(R.id.weddingbutton);
        weddingbtm2=   (TextView)findViewById(R.id.weddingbutton2);
        weddingbtm3=   (TextView) findViewById(R.id.btnwedding3);
        weddingbtm4=   (TextView)findViewById(R.id.btnwedding4);
        weddingbtm5=   (TextView)findViewById(R.id.btnweding5);
        weddingbtm6=   (TextView)findViewById(R.id.btnwedding6);
        weddingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category1="MarriageHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category1);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {
                    category2="PartyHall";
                    Intent intent = new Intent(getApplication(),DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category2);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weddingbtm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category3 = "ExhibitionHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat", category3);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category4="ConferenceHall";

                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category4);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weddingbtm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkInternet()) {
                    category5="MeetingHall";

                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category5);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        weddingbtm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category6="EventHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category6);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            String lati= String.valueOf(location.getLatitude());
//                            Toast.makeText(HomeActivity.this, ""+lati, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


        View decorView = getWindow().getDecorView();
         // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

//        floatingActionButton= (FloatingActionButton) findViewById(R.id.floatiinbtn);
//        floatingActionButton.getBackgroundTintMode();

        placeedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),PlacesAutoCompleteActivity.class);
                startActivity(intent);
            }
        });
        //rsetHasOptionsMenu(true);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        HomeFragment homeFragment = new HomeFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.homeframe, homeFragment);
//        transaction.commit();

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    public boolean checkInternet(){
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //b. from network manger & get active network information
        //NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        //c.check if network connected or not
        if(networkInfo==null  ||networkInfo.isConnected()==false) {
            //means there is no internet
            //webview.loadData("<h1>No Internet check internet<h1>", "text/html", null);
            return  false;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(checkInternet()) {
                Intent intent = new Intent(HomeActivity.this, PlacesAutoCompleteActivity.class);
                startActivity(intent);
                return true;
            }
            else{

                Toast.makeText(this, "check Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


