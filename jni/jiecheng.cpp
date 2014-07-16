#include <jni.h>

extern "C" {
JNIEXPORT jint JNICALL Java_com_aoeng_degu_utils_JniUtils_getJieCheng(
		JNIEnv *env, jclass thisz, jint n) {

	int sum = 1;
	for (int i = 1; i <= n; i++)
		sum *= i;

	return sum;

}

}
