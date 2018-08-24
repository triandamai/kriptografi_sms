package com.ebook.trian.kripto.Vigenere;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ebook.trian.kripto.Model.Model_enkripsi;
import com.ebook.trian.kripto.Model.Vigenere_Proses;
import com.ebook.trian.kripto.R;

import java.util.ArrayList;
import com.ebook.trian.kripto.Model.Vigenere_Scan;

public class Frag_Enc_Vigenere extends Fragment {

    EditText plain,key,hasil;
    Button proses;
    Context context;
    boolean status = false;

    public static String dataHuruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*():;_+-=/?>,<~`||[]. ";

    public static String pesan = "";
    public static String kunci = "";
    public static String chiper = "";

    public static ArrayList en = new ArrayList();
    public static ArrayList de = new ArrayList();

    public  Frag_Enc_Vigenere newInstance(){
        return  new Frag_Enc_Vigenere();
    }
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_enc_vigenere,container,false);

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
                    pesan = plain.getText().toString();
                    kunci = key.getText().toString();
                    Vigenere_Scan s = new Vigenere_Scan();
                    ArrayList sp = s.scannPesan(pesan, dataHuruf);
                    ArrayList sk = s.scannKuci(pesan, kunci, dataHuruf);

                    final Vigenere_Proses ed = new Vigenere_Proses();
                    for (int i = 0; i < pesan.length(); i++) {
                        int iP = Integer.parseInt(sp.get(i).toString());
                        int iK = Integer.parseInt(sk.get(i).toString());
                        int gE = ed.getEnkrip(iP, iK, dataHuruf);

                        en.add(gE);


                    }
                    plain.setText(pesan);
                    hasil.setText(ed.getHasil(en, dataHuruf));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            en.clear();
                        }
                    }, 200);
                }
            }


        });
        return  v;
    }
}
