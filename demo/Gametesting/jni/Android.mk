LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := triangle
LOCAL_SRC_FILES := learngl_triangle_TriangleRenderer.c \
					triangle.c

LOCAL_LDLIBS	:= -lGLESv1_CM

include $(BUILD_SHARED_LIBRARY)
