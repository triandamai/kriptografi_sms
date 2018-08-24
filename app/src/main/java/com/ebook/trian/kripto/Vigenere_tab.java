package com.ebook.trian.kripto;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ebook.trian.kripto.Adapter.AdapterTabLayoutMain;

public class Vigenere_tab extends AppCompatActivity {
    Toolbar toolbar;

    TabLayout tabLayout;
    ViewPager viewPager;
    AdapterTabLayoutMain adapterTabLayoutMain;
    TabItem tabenkrips;
    TabItem tabdekripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_vigenere);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);
        tabenkrips = findViewById(R.id.tabEnkripsi);
        tabdekripsi = findViewById(R.id.tabDekripsi);
        adapterTabLayoutMain = new AdapterTabLayoutMain(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterTabLayoutMain);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
