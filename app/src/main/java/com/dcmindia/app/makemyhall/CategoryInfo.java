package com.dcmindia.app.makemyhall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Abu Osama on 15-07-2017.
 */

public class CategoryInfo extends AppCompatActivity {



TextView textView;
    String num="+91-80-48534423";

    Button call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorinfo);
        textView= (TextView) findViewById(R.id.categoryparticular);
        call= (Button) findViewById(R.id.infocat);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str1=bundle.getString("cat");
        textView.setText(str1);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionGranted()) {
                    call_action();
                }
            }
        });

    }

    public void call_action(){

        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+num));
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
