package com.ebook.trian.kripto.Caesar;

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

import com.ebook.trian.kripto.Model.Model_enkripsi;
import com.ebook.trian.kripto.R;

public class Frag_Dec_caesar extends Fragment {

    Button proses;
    EditText key,cipher,hasil;

   public Frag_Dec_caesar newInstance(){
       return new Frag_Dec_caesar();
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_dec_caesar,container,false);
        key = (EditText) v.findViewById(R.id.et_key);
        cipher = (EditText) v.findViewById(R.id.et_cipher);
        hasil = (EditText) v.findViewById(R.id.et_hasil);
        proses = (Button) v.findViewById(R.id.bt_proses);

        final Model_enkripsi model_enkripsi = new Model_enkripsi();
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ket = Integer.parseInt(key.getText().toString());
                Model_enkripsi model_enkripsi = new Model_enkripsi();
                if (key.getText() == null || key.getText().length() < 1){
                    Toast.makeText(getActivity().getApplicationContext(),"key tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else {
                hasil.setText(model_enkripsi.decrypt(cipher.getText().toString(),ket));
            }
            }
        });


        return v;
    }
}
