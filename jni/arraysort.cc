#include <jni.h>
extern "C" {
JNIEXPORT jintArray JNICALL Java_com_aoeng_degu_utils_JNIUtils_getArraySort(
		JNIEnv * env, jclass obj, jintArray arr) {

	return arr;
}

}
