#include <jni.h>

extern "C" {
JNIEXPORT jint JNICALL Java_com_aoeng_degu_utils_JNIUtils_getFactorial(
		JNIEnv * env, jclass o, jint n) {

	int sum = 1;
	for (int i = 1; i <= n; i++)
		sum *= i;
	return sum;
}
}
