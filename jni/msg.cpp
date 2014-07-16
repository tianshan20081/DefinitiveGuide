#include <jni.h>
extern "C" {
JNIEXPORT jstring JNICALL Java_com_aoeng_degu_utils_JniUtils_getMsgFromJniCpp(
		JNIEnv *env, jclass thisz) {

	return env->NewStringUTF("This Message is from JNI CPP !");
}

}
