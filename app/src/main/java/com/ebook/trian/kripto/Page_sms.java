package com.ebook.trian.kripto;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.kripto.Model.Vigenere_Proses;
import com.ebook.trian.kripto.Model.Vigenere_Scan;

import java.util.ArrayList;

public class Page_sms extends AppCompatActivity {
    private Button btnSend;
    private EditText tvMessage;
    private EditText tvNumber;
    IntentFilter intentFilter;
    int requestCode = 1;

    ProgressDialog dialog;

    EditText plain,key;
    Button proses;
    Context context;
    String in_kunci ;
    boolean encrypt = false;
     String hp;

    public static String dataHuruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*():;_+-=/?>,<~`||[]. ";

    public static String pesan = "";
    public static String kunci = "";
    public static String chiper = "";

    public static ArrayList en = new ArrayList();
    public static ArrayList de = new ArrayList();


    BroadcastReceiver broadcastReceiver;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //display the message in the textview



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sms);
        //intent to filter for SMS message received
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        key = (EditText) findViewById(R.id.tv_key_page_sms);
        btnSend = (Button) findViewById(R.id.btnSend);
        tvNumber = (EditText) findViewById(R.id.tvNumber);
        tvMessage = (EditText) findViewById(R.id.tvMessage);
        dialog = new ProgressDialog(Page_sms.this);
        dialog.setMessage("proses");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);

        if (getIntent().getExtras() == null || getIntent().getExtras().isEmpty()){
            tvNumber.setText("");
            key.setText("");

        }else {
            hp = getIntent().getExtras().getString("no");
            in_kunci = getIntent().getExtras().getString("key");

        }
        tvNumber.setText(hp);
        key.setText(in_kunci);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.show();
                pesan = tvMessage.getText().toString();
                kunci = key.getText().toString();
                Vigenere_Scan s = new Vigenere_Scan();
                ArrayList sp = s.scannPesan(pesan,dataHuruf);
                ArrayList sk = s.scannKuci(pesan,kunci,dataHuruf);

                final Vigenere_Proses ed = new Vigenere_Proses();
                for (int i = 0; i < pesan.length();i++){
                    int iP = Integer.parseInt(sp.get(i).toString());
                    int iK = Integer.parseInt(sk.get(i).toString());
                    final int gE = ed.getEnkrip(iP, iK, dataHuruf);

                        en.add(gE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    en.clear();

                                }
                            },1000);


                }

                String pesan = ed.getHasil(en,dataHuruf);


                String myMsg = tvMessage.getText().toString();
                String txtNumber = tvNumber.getText().toString();
                sendMsg(txtNumber, pesan);
            }
        });

        mintaizin(Manifest.permission.SEND_SMS, requestCode);
        
    }

    private void mintaizin(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Page_sms.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Page_sms.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Page_sms.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Page_sms.this, new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMsg(String txtNumber, String myMsg) {
        String SENT = "Message Send";
        String DELIVERED = "Message Delivered";

        PendingIntent sentPi = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPi = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(txtNumber, null, myMsg, sentPi, deliveredPi);

    }

    @Override
    protected void onResume() {
        registerReceiver(intentReceiver,intentFilter);
        en.clear();
        super.onResume();

    }

    @Override
    protected void onPause() {
        unregisterReceiver(intentReceiver);
        super.onPause();

    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
