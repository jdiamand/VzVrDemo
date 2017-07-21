package com.digiota.vzvrdemo;

/**
 * Created by jdiamand on 7/21/17.
 */

public class GL2JNILib {
    static {
        System.loadLibrary("gl2jni");
    }

    /**
     * @param width the current view width
     * @param height the current view height
     */
    public static native void init(int width, int height);
    public static native void step();
}
