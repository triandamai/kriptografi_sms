package com.ebook.trian.kripto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ebook.trian.kripto.Caesar.Frag_Dec_caesar;
import com.ebook.trian.kripto.Caesar.Frag_Enc_caesar;
import com.ebook.trian.kripto.Vigenere.Frag_Dec_vigenere;
import com.ebook.trian.kripto.Vigenere.Frag_Enc_Vigenere;

public class Home_page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
boolean status = false;

    FrameLayout parent;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        parent = findViewById(R.id.parent_nav);
        rl = findViewById(R.id.content_home);



        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            status =false;
        }else {

            if (status == false) {
                Toast.makeText(Home_page.this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        status = true;

                    }
                }, 1000);
            } else if (status == true) {
                status = false;
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        rl.setVisibility(View.INVISIBLE);

        Fragment fragment = new Frag_awal().newInstance();
        switch (id){
            case R.id.nav_sms:
                fragment = new Frag_home().newInstance();
                break;
            case R.id.nav_Caesar:
                startActivity(new Intent(Home_page.this,Caesar_tab.class));
                break;
            case R.id.nav_Vigenere:
                //fragment = new Frag_Dec_caesar().newInstance();
                startActivity(new Intent(Home_page.this,Vigenere_tab.class));
                break;
            case R.id.nav_About:
                    fragment = new Frag_About().newInstance();
                    break;
            case R.id.nav_Exit:
                   Home_page.this.finish();
                   break;

        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.parent_nav,fragment);

       transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
