package com.dcmindia.app.makemyhall;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    ViewPager viewPager;

    TextView cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,
            cat12,cat13,cat14,cat15,cat16,cat17,cat18,cat19,cat20,cat21,cat22,cat23;


    private TabLayout tabLayout;
    private ViewPager viewPagernew;
    private String lat="12.9716";
    private String lon="77.5946";
    private String category1,category2,category3,category4,category5,category6;

    LinearLayout sliderdotspanel;
    private int dotscount;
    private ImageView[] dots;
    TextView weddingbutton,weddingbtm2,weddingbtm3,weddingbtm4,weddingbtm5,weddingbtm6;




    ViewPagerActivity viewPagerActivity;

    RequestQueue rq;
   // List<SliderUtils> sliderimg;

    String rquest_url="http://www.makemyhall.com/m/slider.php";

    public HomeFragment() {
        // Required empty public constructor
    }

    public boolean checkInternet(){
        ConnectivityManager manager= (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, null);
        viewPager= (ViewPager) v.findViewById(R.id.viewPager);
        viewPagerActivity=new ViewPagerActivity(getActivity());
        viewPager.setAdapter(viewPagerActivity);






        cat1= (TextView) v.findViewById(R.id.cat1);
        cat2= (TextView) v.findViewById(R.id.cat2);
        cat3= (TextView) v.findViewById(R.id.cat3);
        cat4= (TextView) v.findViewById(R.id.cat4);
        cat5= (TextView) v.findViewById(R.id.cat5);
        cat6= (TextView) v.findViewById(R.id.cat6);
        cat7= (TextView) v.findViewById(R.id.cat7);
        cat8= (TextView) v.findViewById(R.id.cat8);
        cat9= (TextView) v.findViewById(R.id.cat9);
        cat10= (TextView) v.findViewById(R.id.cat10);
        cat11= (TextView) v.findViewById(R.id.cat11);
        cat12= (TextView) v.findViewById(R.id.cat12);
        cat13= (TextView) v.findViewById(R.id.cat13);
        cat14= (TextView) v.findViewById(R.id.cat14);
        cat15= (TextView) v.findViewById(R.id.cat15);
        cat16= (TextView) v.findViewById(R.id.cat16);
        cat17= (TextView) v.findViewById(R.id.cat17);
        cat18= (TextView) v.findViewById(R.id.cat18);
        cat19= (TextView) v.findViewById(R.id.cat19);
        cat20= (TextView) v.findViewById(R.id.cat20);
        cat21= (TextView) v.findViewById(R.id.cat21);
        cat22= (TextView) v.findViewById(R.id.cat22);
        cat23= (TextView) v.findViewById(R.id.cat23);




        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat1.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });


        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat2.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat3.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat4.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat5.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat6.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat7.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat8.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat9.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat10.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat11.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat12.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat13.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat14.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat15.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat16.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat17.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat18.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat19.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat20.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat21.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat22.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        cat23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=cat23.getText().toString();
                Intent intent=new Intent(getActivity(),CategoryInfo.class);
                intent.putExtra("cat",str);
                startActivity(intent);
            }
        });
        weddingbutton= (TextView) v.findViewById(R.id.weddingbutton);
        weddingbtm2=   (TextView) v.findViewById(R.id.weddingbutton2);
        weddingbtm3=   (TextView) v.findViewById(R.id.btnwedding3);
        weddingbtm4=   (TextView) v.findViewById(R.id.btnwedding4);
        weddingbtm5=   (TextView) v.findViewById(R.id.btnweding5);
        weddingbtm6=   (TextView) v.findViewById(R.id.btnwedding6);
        weddingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category1="MarriageHall";
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category1);
                    startActivity(intent);
                }
                  else{

                    Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {
                    category2="PartyHall";
                   Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category2);
                startActivity(intent);
            }
                  else{

                Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
            }
            }
        });
        weddingbtm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category3 = "ExhibitionHall";
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat", category3);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category4="ConferenceHall";

                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category4);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weddingbtm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkInternet()) {
                    category5="MeetingHall";

                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category5);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        weddingbtm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category6="EventHall";
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category6);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getActivity(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

//
//
//        tabLayout = (TabLayout)v.findViewById(R.id.tablayoutfornew);
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cr));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cr));
//        tabLayout.addTab(tabLayout.newTab().setText("hii"));
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
        Timer timer=new Timer();
        rq= Volley.newRequestQueue(getActivity());
        //sliderimg=new ArrayList<>();
        //sendRequest1();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        return v;
    }



     public class MyTimerTask extends TimerTask {


         @Override
         public void run() {

             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {

                     if(viewPager.getCurrentItem()==0){

                         viewPager.setCurrentItem(1);
                     }else if(viewPager.getCurrentItem()==1){

                         viewPager.setCurrentItem(2);
                     }else {

                         viewPager.setCurrentItem(0);
                     }

                 }
             });
         }
     }


    }