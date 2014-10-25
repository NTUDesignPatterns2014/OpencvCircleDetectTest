package com.weitang114.opencvcircledetecttest;

import org.opencv.core.Mat;

/**
 * Created by WeiTang114 on 2014/10/24.
 */
public class NativeCircleDetector {
    public NativeCircleDetector() {

    }


    public void detect(Mat image) {
        nativeDetect(image.getNativeObjAddr());
    }

    private static native void nativeDetect(long imputimage);

}


