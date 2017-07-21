package com.digiota.vzvrdemo;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    static boolean isHelpVisible = true  ;

    public GL2JNIView getGLView() {
        return mGLView;
    }

    GL2JNIView mGLView =null;
    boolean mIsDualPane = false;

    ShowHelpFragment mShowHelpFragment;
    MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mGLView = (GL2JNIView) this.findViewById(R.id.surfaceviewclass);
        ActionBar actionBar = getSupportActionBar();



        if (actionBar != null ) {

            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher_round);
            actionBar.setTitle(" Verizon VR Demo");
        }



        // find our fragments
        mShowHelpFragment = (ShowHelpFragment) getSupportFragmentManager().findFragmentById(
                R.id.description);
        mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(
                R.id.display);



        View descriptionView = findViewById(R.id.description);
        mIsDualPane =     (descriptionView != null) && (descriptionView.getVisibility() == View.VISIBLE);

        if (mIsDualPane && isHelpVisible) {
            setContentView(R.layout.onepane);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);


        if (mIsDualPane) {
            if (isHelpVisible) {
                menu.findItem(R.id.help).setIcon(R.drawable.ic_help_black_24dp);
            } else {

                menu.findItem(R.id.help).setIcon(R.drawable.ic_done_black_24dp);
            }


        } else {
            menu.findItem(R.id.help).setIcon(R.drawable.ic_help_black_24dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.help) {

            if (mIsDualPane) {
                if (isHelpVisible) {
                    isHelpVisible = false;
                    Intent i = new Intent(this, MainActivity.class);

                    startActivity(i);

                } else {
                    isHelpVisible = true ;
                    Intent i = new Intent(this, MainActivity.class);

                    startActivity(i);

                }

            } else {

                Intent i = new Intent(this, ShowHelpActivity.class);
                startActivity(i);
                finish() ;

            }

        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


}
