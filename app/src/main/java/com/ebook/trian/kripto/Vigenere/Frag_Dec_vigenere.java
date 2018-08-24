package com.ebook.trian.kripto.Vigenere;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ebook.trian.kripto.Model.Vigenere_Proses;
import com.ebook.trian.kripto.Model.Vigenere_Scan;
import com.ebook.trian.kripto.R;

import java.util.ArrayList;

import static android.content.Intent.getIntent;

public class Frag_Dec_vigenere extends Fragment {
    Intent intent;
    EditText plain,key,hasil;
    Button proses;
    Context context;
    boolean encrypt = false;

    public static String pesan = "";
    public static String kunci = "";
    public static String chiper = "";

    public static String dataHuruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*():;_+-=/?>,<~`||[]. ";

    public static ArrayList en = new ArrayList();
    public static ArrayList dec = new ArrayList();

    public Frag_Dec_vigenere newInstance(){
        return new Frag_Dec_vigenere();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_dec_vigenere,container,false);
        plain = v.findViewById(R.id.et_plain);
        key = v.findViewById(R.id.et_key);
        hasil = v.findViewById(R.id.et_hasil);
        proses = v.findViewById(R.id.bt_proses);
        context = getActivity().getApplicationContext();




        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.getText().length() < 1 || key.getText() == null){
                    Toast.makeText(getActivity(),"key tidak bolek kosong",Toast.LENGTH_SHORT).show();
                }else {
                chiper = plain.getText().toString();
                kunci = key.getText().toString();
                Vigenere_Scan s = new Vigenere_Scan();
                ArrayList sp = s.scannPesan(chiper,dataHuruf);
                ArrayList sk = s.scannKuci(chiper,kunci,dataHuruf);

                final Vigenere_Proses ed = new Vigenere_Proses();
                for (int i =0; i < chiper.length();i++){
                    int IC = Integer.parseInt(sp.get(i).toString());
                    int iK2 = Integer.parseInt(sk.get(i).toString());
                    int de = ed.getDekrip(IC, iK2,dataHuruf);
                    dec.add(de);

                }
                plain.setText(chiper);
                hasil.setText(ed.getHasil(dec,dataHuruf));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dec.clear();
                    }
                },200);
            }
                }
        });


        return v;
    }
}
