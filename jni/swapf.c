#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

JNIEXPORT void JNICALL Java_com_aoeng_degu_utils_JniUtils_swapbf(JNIEnv *env,
		jobject jobj, jobject sbuf, jobject rbuf) {
	printf("Java_com_aoeng_degu_utils_JniUtils_swapbf");
//	jbyte* sbufp = (*env)->GetDirectBufferAddress(env, sbuf);
//	jbyte* rbufp = (*env)->GetDirectBufferAddress(env, rbuf);
//
//	MPI_Request sreq, rreq;
//	MPI_Status sstatus, rstatus;
//	MPI_Init(NULL, NULL);
//	MPI_Isend(sbufp, 3, MPI_BYTE, 0, 0, MPI_COMM_WORLD, &sreq);
//	MPI_Recv(rbufp, 3, MPI_BYTE, 0, 0, MPI_COMM_WORLD, &rstatus);
//	MPI_Wait(&sreq, &sstatus);
//	MPI_Finalize();
//
//	(*env)->ReleasePrimitiveArrayCritical(env, sbuf, sbufp, 0);
//	(*env)->ReleasePrimitiveArrayCritical(env, rbuf, rbufp, 0);
}
