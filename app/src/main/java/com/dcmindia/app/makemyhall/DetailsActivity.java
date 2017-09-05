package com.dcmindia.app.makemyhall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class DetailsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Listnew> halllist;
    private ProgressDialog progress;
    LinearLayout linearLayout;
    MyRecyclerAdapter myRecyclerAdapter;
    String lat1, lon1,category1;
    ProgressDialog progressDialog;



    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

        @Override
        public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = getLayoutInflater().inflate(R.layout.row, parent, false);
            MyRecyclerAdapter.MyViewHolder myViewHolder = new MyRecyclerAdapter.MyViewHolder(v);
            return myViewHolder;
        }


        public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, final int position) {
            final Listnew get = halllist.get(position);
            holder.hallname.setText(get.getHallname());
            holder.halladd.setText(get.getHalladd());
            holder.hallprice.setText("\u20B9" + get.getHallprice());
            //holder.hallnumber.setText("+91" + get.getHallnumber());
            Glide.with(DetailsActivity.this).load(get.getHallimage()).into(holder.hallimage);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplication(), TabsHeaderActivity.class);
                    intent.putExtra("add", get.getHalladd());
                    intent.putExtra("hallname", get.getHallname());
                    intent.putExtra("mobilenumber", get.getHallnumber());
                    intent.putExtra("imageslider", get.getHallimage());
                    intent.putExtra("latidude",get.getHalllatitude());
                    intent.putExtra("longitude",get.getHalllongitude());
                    intent.putExtra("emailid",get.getHallemail());
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return halllist.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView hallimage;
            //recyclerView.getRecycledViewPool().clear();

            public TextView hallname, halladd, hallprice, hallnumber;
            RatingBar rating;

            public MyViewHolder(View itemView) {
                super(itemView);

                hallimage = (ImageView) itemView.findViewById(R.id.imageview);
                hallname = (TextView) itemView.findViewById(R.id.textview1);
                halladd = (TextView) itemView.findViewById(R.id.textview2);
                hallprice = (TextView) itemView.findViewById(R.id.textview3);
                hallnumber = (TextView) itemView.findViewById(R.id.textview4);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutid);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toast.makeText(this, "Please Wait", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lat1 = bundle.getString("lat");
        lon1 = bundle.getString("lon");
        category1 = bundle.getString("cat");

        if (this.checkInternet()) {
            new SendRequest().execute("http://www.makemyhall.com/m/hallcate.php");

        }

        halllist = new ArrayList<Listnew>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        myRecyclerAdapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(myRecyclerAdapter);
        //recyclerView.getRecycledViewPool().clear();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
//    protected void onResume() {
//        super.onResume();
//
//        recyclerView.removeAllViewsInLayout();//removes all the views
//
//        //then reload the data
//        SendRequest doPostCall = new SendRequest(); //my AsyncTask...
//        doPostCall.execute();
//    }

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

     class SendRequest extends AsyncTask<String, Void, String> {

        String line;



        protected void onPreExecute() {

            super.onPreExecute();


        }

        protected String doInBackground(String... p1) {

            try {

                URL url = new URL(p1[0]);

                JSONObject postDataParams = new JSONObject();


                postDataParams.put("latitude", lat1);
                postDataParams.put("longitute", lon1);
                postDataParams.put("category",category1);

                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

//                if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //StringBuffer sb = new StringBuffer();
                StringBuilder sb=new StringBuilder();
                line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);

                }


                Log.d("sb",sb.toString());
                return sb.toString();
                //in.close();

//                } else {
//                    return new String("false : " + responseCode);
//                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "no internet permission";
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }


        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

               JSONObject jsonObject = new JSONObject(s);

                JSONArray jsonArray = jsonObject.getJSONArray(Config.TAG_Hall_ok);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jc = jsonArray.getJSONObject(i);
                    String hallname = jc.getString(Config.TAG_Hall_Name);
                    String hallnumber = jc.getString(Config.TAG_Hall_Num);
                    String halladd = jc.getString(Config.TAG_Address);
                    String hallprice = jc.getString(Config.TAG_Price);
                    String hallbanner = jc.getString(Config.TAG_Banner);
                    String latitude=jc.getString("hall_latitude");
                    String longitude=jc.getString("hall_longitute");
                    String emailid=jc.getString("hall_email");


                    Listnew list = new Listnew();
                    list.setHallname(hallname);
                    list.setHallnumber(hallnumber);
                    list.setHalladd(halladd);
                    list.setHallprice(hallprice);
                    list.setHallimage(hallbanner);
                    list.setHalllatitude(latitude);
                    list.setHalllongitude(longitude);
                    list.setHallemail(emailid);
                    halllist.add(list);

                }

                myRecyclerAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
