package com.ebook.trian.kripto.Caesar;

import android.content.Context;
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

public class Frag_Enc_caesar extends Fragment {
    EditText plain,key,hasil;
    Button proses;
    Context context;

    public Frag_Enc_caesar newInstance(){
        return new Frag_Enc_caesar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_enc_caesar,container,false);
        plain = v.findViewById(R.id.et_plain);
        key = v.findViewById(R.id.et_key);
        hasil = v.findViewById(R.id.et_hasil);
        proses = v.findViewById(R.id.bt_proses);
        context = getActivity().getApplicationContext();

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ket = Integer.parseInt(key.getText().toString());
                Model_enkripsi model_enkripsi = new Model_enkripsi();
                if (key.getText() == null || key.getText().length() < 1){
                    Toast.makeText(getActivity().getApplicationContext(),"key tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else {
                    hasil.setText(model_enkripsi.encrypt(plain.getText().toString(), ket));
                }
            }
        });
        return v;
    }
}
