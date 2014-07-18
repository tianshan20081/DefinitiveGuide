#include <jni.h>
#include<android/log.h>
#include "com_aoeng_degu_utils_JniUtils.h"

JNIEXPORT void JNICALL Java_com_aoeng_degu_utils_JniUtils_getCppLog(JNIEnv *env,
		jclass thisz, jint lev) {
	switch (lev) {
	case 1:
		LOGD("LOGD 1");
		break;
	case 2:
		LOGI("LOGI 2");
		break;
	case 3:
		LOGW("LOGW 3");
		break;
	case 4:
		LOGE("LOGE 4");
		break;
	case 5:
		LOGF("LOGF 5");
		break;

	}

}
