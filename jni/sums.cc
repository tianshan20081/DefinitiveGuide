#include <jni.h>

extern "C" {
JNIEXPORT jint JNICALL Java_com_aoeng_degu_utils_JniUtils_getSum(JNIEnv * env,
		jclass thisz, jint a, jint b)

		{

	return (a + b);
}
}
