package com.ebook.trian.kripto;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ebook.trian.kripto.Adapter.SmsAdapter;
import com.ebook.trian.kripto.Model.SmsModel;

import java.util.ArrayList;

public class Daftar_inbox extends AppCompatActivity {
    Toolbar toolbar;
    ListView lv_sms;
    ArrayList<SmsModel> smsModelArrayList;
    SmsAdapter smsAdapter;
    ProgressDialog progressDialog;
    int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_inbox_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mintaizin(Manifest.permission.READ_SMS, requestCode);
        //method for initialisation


        //Method to read sms and load into listview

            init();
            readSms();


    }
    private void mintaizin(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Daftar_inbox.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Daftar_inbox.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Daftar_inbox.this, new String[]{permission}, requestCode);



            } else {

                ActivityCompat.requestPermissions(Daftar_inbox.this, new String[]{permission}, requestCode);

            }
        } else {
            //Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            this.requestCode = 2;

        }

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading sms");

        smsModelArrayList = new ArrayList<>();
        lv_sms = (ListView) findViewById(R.id.lv_sms);
        smsAdapter = new SmsAdapter(Daftar_inbox.this,smsModelArrayList);
        lv_sms.setAdapter(smsAdapter);
        progressDialog.show();

    }
    public void readSms(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Uri uri = Uri.parse("content://sms/inbox");
                Cursor c = getContentResolver().query(uri, null, null ,null,null);
                startManagingCursor(c);
                if(c.moveToFirst()) {
                    for(int i = 0; i < c.getCount(); i++) {

                        String mobile = c.getString(c.getColumnIndexOrThrow("address")).toString();
                        String message = c.getString(c.getColumnIndexOrThrow("body")).toString();

                        //adding item to array list
                        smsModelArrayList.add(new SmsModel(mobile, message));
                        c.moveToNext();

                    }
                    progressDialog.dismiss();
                }
                //c.close();


                // notifying listview adapter

                smsAdapter.notifyDataSetChanged();
            }
        },2000);
        // Read the sms data
       smsModelArrayList.clear();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Daftar_inbox.this,Home_page.class).putExtra("hal","home"));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        readSms();
    }
}
