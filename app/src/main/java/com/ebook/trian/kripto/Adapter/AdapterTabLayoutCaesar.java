package com.ebook.trian.kripto.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebook.trian.kripto.Caesar.Frag_Dec_caesar;
import com.ebook.trian.kripto.Caesar.Frag_Enc_caesar;
import com.ebook.trian.kripto.Vigenere.Frag_Dec_vigenere;
import com.ebook.trian.kripto.Vigenere.Frag_Enc_Vigenere;

//packagefragment

/**
 *
 */

public class AdapterTabLayoutCaesar extends FragmentPagerAdapter {

    private int numOfTabs;
    FragmentManager f;

    public AdapterTabLayoutCaesar(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Frag_Enc_caesar().newInstance();
            case 1:
                return new Frag_Dec_caesar().newInstance();
            default:
                return new Frag_Enc_caesar().newInstance();
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
