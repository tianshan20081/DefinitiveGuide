#include <jni.h>

JNIEXPORT jint JNICALL Java_com_aoeng_degu_utils_JNIUtils_getSum(JNIEnv * env,
		jclass o, jint a, jint b) {
	return (a + b);
}
