LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


#OPENCV_CAMERA_MODULES:=on
#OPENCV_INSTALL_MODULES:=on

include D:\dev\OpenCV-2.4.9-android-sdk\sdk\native\jni\OpenCV.mk

LOCAL_MODULE    := circle_detection
LOCAL_SRC_FILES := houghcircle.cpp
LOCAL_CFLAGS    += -ID:\dev\OpenCV-2.4.9-android-sdk\sdk\native\jni\include
LOCAL_LDLIBS    += -llog -ldl

include $(BUILD_SHARED_LIBRARY)