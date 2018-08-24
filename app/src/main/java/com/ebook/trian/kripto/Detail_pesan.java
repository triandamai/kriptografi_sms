package com.ebook.trian.kripto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ebook.trian.kripto.Model.Vigenere_Proses;
import com.ebook.trian.kripto.Model.Vigenere_Scan;

import java.util.ArrayList;

public class Detail_pesan extends AppCompatActivity {
Intent intent;
Button baca,balas;
TextView isi,no;
EditText key;
    public String hp;
    public String pesan;
    Button proses;
    Context context;
    boolean status = false;

    //public static String pesan = "";
    public static String kunci = "";
    public static String chiper = "";

    public static String dataHuruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*():;_+-=/?>,<~`||[]. ";

    public static ArrayList en = new ArrayList();
    public static ArrayList dec = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pesan);
        key = findViewById(R.id.et_key_detail);
        isi = findViewById(R.id.tv_ini_detail);
        no = findViewById(R.id.tv_no_detail);
        baca = findViewById(R.id.baca);
        balas = findViewById(R.id.balas);

        hp = getIntent().getExtras().getString("no");
        pesan = getIntent().getExtras().getString("pesan");
        isi.setText(pesan);
        no.setText(hp);

        aksi();


    }

    private void aksi() {
        baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.getText().length() <= 1 || key.getText() == null) {
                    Toast.makeText(Detail_pesan.this, "Key harap diisi", Toast.LENGTH_SHORT).show();
                } else {
                    if (status == false) {
                        chiper = isi.getText().toString();
                        kunci = key.getText().toString();
                        Vigenere_Scan s = new Vigenere_Scan();
                        ArrayList sp = s.scannPesan(chiper, dataHuruf);
                        ArrayList sk = s.scannKuci(chiper, kunci, dataHuruf);

                        final Vigenere_Proses ed = new Vigenere_Proses();
                        for (int i = 0; i < chiper.length(); i++) {
                            int IC = Integer.parseInt(sp.get(i).toString());
                            int iK2 = Integer.parseInt(sk.get(i).toString());
                            int de = ed.getDekrip(IC, iK2, dataHuruf);
                            dec.add(de);

                        }

                        isi.setText(ed.getHasil(dec, dataHuruf));
                        status = true;
                        dec.clear();
                    } else {


                        isi.setText(pesan);
                        status = false;
                        en.clear();
                    }
                }
            }
        });

        balas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.getText().length() <= 1 || key.getText() == null){
                    Toast.makeText(Detail_pesan.this,"Key harap diisi",Toast.LENGTH_SHORT).show();
                }else {
                startActivity(new Intent(Detail_pesan.this,Page_sms.class).putExtra("no",hp).putExtra("key",key.getText().toString()));
            }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Detail_pesan.this,Daftar_inbox.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
