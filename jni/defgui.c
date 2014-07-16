#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_aoeng_degu_utils_JniUtils_getMsgFromJni(
		JNIEnv *env, jclass thisz) {

	return (*env)->NewStringUTF(env, "This msg is from JNI C !");
}
