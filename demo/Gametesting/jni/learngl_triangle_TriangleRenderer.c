#include "learngl_triangle_TriangleRenderer.h"
#include "triangle.h"

void Java_qianxian_src_DemoRenderer_nativeInit(JNIEnv *jni, jobject obj) {
	init();
}

void Java_qianxian_src_DemoRenderer_nativeResize(JNIEnv *jni, jobject obj,jint w, jint h) {
	resize(w, h);
}

void Java_qianxian_src_DemoRenderer_nativeRender(JNIEnv *jni, jobject obj) {
	render();
}

void Java_qianxian_src_DemoRenderer_nativeSpeed(JNIEnv *jni, jobject obj,jint s) {
	setSpeed(s);
}
