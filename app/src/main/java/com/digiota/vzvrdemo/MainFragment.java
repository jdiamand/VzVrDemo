package com.digiota.vzvrdemo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by jdiamand on 7/18/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener{

    GL2JNIView mGLView ;
    static {
        System.loadLibrary("native-lib");
    }


    static boolean mGLViewViz = true  ;

    public MainFragment() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);
        getActivity().setContentView(R.layout.activity_main);


        mGLView = (GL2JNIView) v.findViewById(R.id.surfaceviewclass);
        if (!mGLViewViz) {
            mGLView.setVisibility(View.INVISIBLE);
        }

        ImageButton playButton = (ImageButton) v.findViewById(R.id.playButton) ;
        buttonEffect(playButton) ;
        playButton.setOnClickListener(this);


        ImageButton pauseButton = (ImageButton) v.findViewById(R.id.pauseButton) ;
        buttonEffect(pauseButton) ;
        pauseButton.setOnClickListener(this);

        ImageButton stopButton = (ImageButton) v.findViewById(R.id.stopButton) ;
        buttonEffect(stopButton) ;
        stopButton.setOnClickListener(this);


        TextView tv = (TextView) v.findViewById(R.id.feedback_textView);
        MainActivity mainActivity = (MainActivity)(getActivity()) ;
        tv.setText( mainActivity.stringFromJNI());


        return v;

    }







    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0x88646464, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }



    @Override
    public void onClick(View view) {

        MainActivity mainActivity = (MainActivity)getActivity() ;
        GL2JNIView gl2JNIView = mainActivity.getGLView() ;
        switch (view.getId()) {

            case R.id.playButton :

                Toast.makeText(getContext(),"You selected play",Toast.LENGTH_SHORT).show() ;
                mGLView.setVisibility(View.VISIBLE);
                mGLViewViz = true ;
                break ;

            case R.id.pauseButton :

                Toast.makeText(getContext(),"You selected pause",Toast.LENGTH_SHORT).show() ;
                mGLView.setVisibility(View.INVISIBLE);
                mGLViewViz = false ;
                break ;

            case R.id.stopButton :

                Toast.makeText(getContext(),"You selected stop",Toast.LENGTH_SHORT).show() ;
                mGLView.setVisibility(View.INVISIBLE);
                mGLViewViz = false  ;
                break ;

            default :
                break ;
        }



    }
}
