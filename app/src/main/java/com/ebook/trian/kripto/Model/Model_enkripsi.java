package com.ebook.trian.kripto.Model;

import android.widget.Toast;

public class Model_enkripsi {
    String plain = "";
    int key = 0;

    public String encrypt(String str, int key){
        String encrypt ="";
            for (int i = 0; i<str.length(); i++){
                int c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    c = c + (key % 26);
                    if (c > 'Z')
                        c = c - 26;

                }else if (Character.isLowerCase(c)){
                        c= c +(key%26);
                        if (c> 'z')
                            c = c-26;
                    }
                encrypt += (char) c;
                }
        return encrypt;
    }
    public static String decrypt(String str, int key){
        String decrypt = "";
        for (int i = 0; i < str.length(); i++){
            int c = str.charAt(i);
            if (Character.isUpperCase(c)){
                c = c - (key%26);
                if (c<'A')
                    c = c + 26;

            }else if(Character.isLowerCase(c)){
                c = c - (key%26);
                if (c < 'a')
                    c = c + 26;


            }
            decrypt += (char) c;
        }

        return decrypt;
    }
    public String getPlain() {
        return plain;
    }
    public void setPlain(String plain) {
        this.plain = plain;
    }
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
}
