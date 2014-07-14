#include <string.h>
#include <jni.h>

jstring Java_com_aoeng_degu_utils_JNIUtils_msgFromJNI(JNIEnv * env,
		jobject thisz) {
	return (*env)->NewStringUTF(env, "Hello From JNI define");
}
