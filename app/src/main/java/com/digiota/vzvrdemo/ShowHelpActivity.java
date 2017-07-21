package com.digiota.vzvrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by jdiamand on 7/20/17.
 */

public class ShowHelpActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_help);

        ActionBar actionBar = getSupportActionBar();



        if (actionBar != null ) {

            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher_round);
            actionBar.setTitle(" Verizon VR Demo");
        }



        if (getResources().getBoolean(R.bool.has_two_panes)) {
            finish();
            return;
        }


        ShowHelpFragment f = new ShowHelpFragment();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, f).commit();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        menu.findItem(R.id.help).setIcon(R.drawable.ic_done_black_24dp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.help) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish() ;

        }
        return super.onOptionsItemSelected(item);
    }
}
