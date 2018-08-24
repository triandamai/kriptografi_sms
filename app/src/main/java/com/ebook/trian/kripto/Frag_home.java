package com.ebook.trian.kripto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Frag_home extends Fragment {
Button sms;
    Cursor cursor;
    int requestCode = 1;
    Button buttonreadsms;

    public Frag_home newInstance(){
        return new Frag_home();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_home,container,false);
        sms = (Button) v.findViewById(R.id.bt_sms);

        buttonreadsms = (Button)v.findViewById(R.id.buttonreadsms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mintaizin(Manifest.permission.SEND_SMS, requestCode);
                startActivity(new Intent(getActivity(),Page_sms.class));

            }
        });
        buttonreadsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // public static final String INBOX = "content://sms/inbox";
                // public static final String SENT = "content://sms/sent";
                // public static final String DRAFT = "content://sms/draft";
                mintaizin(Manifest.permission.READ_SMS, requestCode);
                startActivity(new Intent(getActivity(),Daftar_inbox.class));
                getActivity().finish();
            }
        });

        return v;
    }
    private void mintaizin(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(getActivity().getApplicationContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }



}
