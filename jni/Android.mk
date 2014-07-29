LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := defgui
LOCAL_SRC_FILES := defgui.c msg.cpp sums.cc jiecheng.cpp swap.cc swapf.c log.cpp 
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog
include $(BUILD_SHARED_LIBRARY)
