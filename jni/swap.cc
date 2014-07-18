#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
extern "C" {
JNIEXPORT void JNICALL Java_com_aoeng_degu_utils_JniUtils_swap(JNIEnv *env,
		jclass thisz, jint sbuf, jint rbuf) {
	printf("hello");

}

}
