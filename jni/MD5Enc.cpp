#include <jni.h>
#include "MD5.h"

extern "C" {
JNIEXPORT jstring JNICALL Java_com_aoeng_degu_utils_JniUtils_getCppMD5(
		JNIEnv *env, jclass thisz, jstring jstr) {

	return env->NewStringUTF("md5(jstr)");

}

}
