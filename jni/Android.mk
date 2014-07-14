LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := hello-jni
LOCAL_SRC_FILES := hello-jni.c sums.c factorial.cpp arraysort.cc

include $(BUILD_SHARED_LIBRARY)
