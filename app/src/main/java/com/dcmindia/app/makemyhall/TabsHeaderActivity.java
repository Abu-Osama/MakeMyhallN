package com.dcmindia.app.makemyhall;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabsHeaderActivity extends AppCompatActivity {
    private static final String TAG = TabsHeaderActivity.class.getSimpleName();

    String latitude,longitude,hallname,haddress,mobnumnerhall,emailidhall;
    ImageView imageView;
    TextView nameh,addh,phoneh,hallemaillast;
    @BindView(R.id.call) TextView call;
    @BindView(R.id.location) TextView location;
    @BindView(R.id.morecate) TextView morecat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_header);
        imageView= (ImageView) findViewById(R.id.htab_header);
        nameh= (TextView) findViewById(R.id.hallnamelst);
        addh= (TextView) findViewById(R.id.addhalllast);
        phoneh= (TextView) findViewById(R.id.hallphonelast);
        hallemaillast= (TextView) findViewById(R.id.hallemaillast);
        ButterKnife.bind(this);


//        call= (TextView) findViewById(R.id.call);
//        location= (TextView) findViewById(R.id.location);
//        morecat= (TextView) findViewById(R.id.morecate);




        call.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(isPermissionGranted()) {
                        call_action();
                }

                return false;
            }
        });

//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(isPermissionGranted()) {
//                        call_action();
//                      }
//            }
//        });
//
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " " + hallname + "";
                       startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(geoUri)));


            }
        });

        morecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1=new Intent(getApplication(),LastDeatils.class);
                       intent1.putExtra("num",mobnumnerhall);
                       startActivity(intent1);


            }
        });

        //call framgent

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String image = bundle.getString("imageslider");
        latitude  = bundle.getString("latidude");
        longitude=bundle.getString("longitude");
        hallname=bundle.getString("hallname");
        haddress=bundle.getString("add");
        mobnumnerhall=bundle.getString("mobilenumber");
        emailidhall=bundle.getString("emailid");

        Glide.with(this).load(image).into(imageView);

        nameh.setText(hallname);
        addh.setText("Address: "+haddress);
        phoneh.setText("Mobile No: "+mobnumnerhall);
        hallemaillast.setText("Email ID: "+emailidhall);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("MakeMyhall");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        //setupViewPager(viewPager);
        viewPager.beginFakeDrag();


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);

//        tabLayout.addTab(tabLayout.newTab().setText("Call"));
//        tabLayout.addTab(tabLayout.newTab().setText("Location"));
//        tabLayout.addTab(tabLayout.newTab().setText("More category"));

        //tabLayout.setupWithViewPager(viewPager);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.htab_collapse_toolbar);

        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sliderimg);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {

                    int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                    collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                    collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
                }
            });

        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            Log.e(TAG, "onCreate: failed to create bitmap from background", e.fillInStackTrace());
            collapsingToolbarLayout.setContentScrimColor(
                    ContextCompat.getColor(this, R.color.primary_500)
            );
            collapsingToolbarLayout.setStatusBarScrimColor(
                    ContextCompat.getColor(this, R.color.primary_700)
            );
        }

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewPager.setCurrentItem(tab.getPosition());
//                Log.d(TAG, "onTabSelected: pos: " + tab.getPosition());
//
//                switch (tab.getPosition()) {
//                    case 0:
//                        if(isPermissionGranted()) {
//                            call_action();
//                        }
//                        break;
//
//                    case 1:
//                        String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " " + hallname + "";
//                        startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(geoUri)));
//                        break;
//
//                    case 2:
//
//                        Intent intent1=new Intent(getApplication(),LastDeatils.class);
//                        intent1.putExtra("num",mobnumnerhall);
//                        startActivity(intent1);
//
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFrag(new DummyFragment(ContextCompat.getColor(this, R.color.cyan_50)), "Call");
//        adapter.addFrag(new DummyFragment(ContextCompat.getColor(this, R.color.amber_50)), "Location");
//        adapter.addFrag(new DummyFragment(ContextCompat.getColor(this, R.color.purple_50)), "email");
//       viewPager.setAdapter(adapter);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class DummyFragment extends Fragment {
        int color;

        Bundle args;

        TextView namehal,addresshall,phonehall;


        public DummyFragment() {
        }

        @SuppressLint("ValidFragment")
        public DummyFragment(int color) {

            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

//            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
//            frameLayout.setBackgroundColor(color);
//1
//            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
//            recyclerView.setLayoutManager(linearLayoutManager);
//            recyclerView.setHasFixedSize(true);

//            DessertAdapter adapter = new DessertAdapter(getContext());
//            recyclerView.setAdapter(adapter);

            return view;
        }
    }

    public void call_action(){

        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+mobnumnerhall));
        if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.checkSelfPermission(getApplication(),Manifest.permission.CALL_PHONE)
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
        startActivity(callIntent);

    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
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

}