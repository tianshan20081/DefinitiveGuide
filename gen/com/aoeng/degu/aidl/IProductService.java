/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/paynet/androidWork/github/DefinitiveGuide/src/com/aoeng/degu/aidl/IProductService.aidl
 */
package com.aoeng.degu.aidl;
public interface IProductService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.aoeng.degu.aidl.IProductService
{
private static final java.lang.String DESCRIPTOR = "com.aoeng.degu.aidl.IProductService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.aoeng.degu.aidl.IProductService interface,
 * generating a proxy if needed.
 */
public static com.aoeng.degu.aidl.IProductService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.aoeng.degu.aidl.IProductService))) {
return ((com.aoeng.degu.aidl.IProductService)iin);
}
return new com.aoeng.degu.aidl.IProductService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getMap:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.aoeng.degu.aidl.Product _arg1;
if ((0!=data.readInt())) {
_arg1 = com.aoeng.degu.aidl.Product.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
java.util.Map _result = this.getMap(_arg0, _arg1);
reply.writeNoException();
reply.writeMap(_result);
return true;
}
case TRANSACTION_getProduct:
{
data.enforceInterface(DESCRIPTOR);
com.aoeng.degu.aidl.Product _result = this.getProduct();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.aoeng.degu.aidl.IProductService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.util.Map getMap(java.lang.String country, com.aoeng.degu.aidl.Product product) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.Map _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(country);
if ((product!=null)) {
_data.writeInt(1);
product.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getMap, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readHashMap(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.aoeng.degu.aidl.Product getProduct() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.aoeng.degu.aidl.Product _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getProduct, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.aoeng.degu.aidl.Product.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getMap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getProduct = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.util.Map getMap(java.lang.String country, com.aoeng.degu.aidl.Product product) throws android.os.RemoteException;
public com.aoeng.degu.aidl.Product getProduct() throws android.os.RemoteException;
}
