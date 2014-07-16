LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := defgui
LOCAL_SRC_FILES := defgui.c msg.cpp

include $(BUILD_SHARED_LIBRARY)
