package com.saartak.el.magisk;

public interface IIsolatedService extends android.os.IInterface
{
    /** Default implementation for IIsolatedService. */
    public static class Default implements com.saartak.el.magisk.IIsolatedService
    {
        // Check if Magisk is present

        @Override public boolean isMagiskPresent() throws android.os.RemoteException
        {
            return false;
        }
        @Override
        public android.os.IBinder asBinder() {
            return null;
        }
    }
    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends android.os.Binder implements com.saartak.el.magisk.IIsolatedService
    {
        private static final java.lang.String DESCRIPTOR = "com.saartak.el.magisk.IIsolatedService";
        /** Construct the stub at attach it to the interface. */
        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }
        /**
         * Cast an IBinder object into an com.saartak.el.magisk.IIsolatedService interface,
         * generating a proxy if needed.
         */
        public static com.saartak.el.magisk.IIsolatedService asInterface(android.os.IBinder obj)
        {
            if ((obj==null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin!=null)&&(iin instanceof com.saartak.el.magisk.IIsolatedService))) {
                return ((com.saartak.el.magisk.IIsolatedService)iin);
            }
            return new com.saartak.el.magisk.IIsolatedService.Stub.Proxy(obj);
        }
        @Override public android.os.IBinder asBinder()
        {
            return this;
        }
        @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
        {
            java.lang.String descriptor = DESCRIPTOR;
            switch (code)
            {
                case INTERFACE_TRANSACTION:
                {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_isMagiskPresent:
                {
                    data.enforceInterface(descriptor);
                    boolean _result = this.isMagiskPresent();
                    reply.writeNoException();
                    reply.writeInt(((_result)?(1):(0)));
                    return true;
                }
                default:
                {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }
        private static class Proxy implements com.saartak.el.magisk.IIsolatedService
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
            // Check if Magisk is present

            @Override public boolean isMagiskPresent() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_isMagiskPresent, _data, _reply, 0);
                    if (!_status && getDefaultImpl() != null) {
                        return getDefaultImpl().isMagiskPresent();
                    }
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            public static com.saartak.el.magisk.IIsolatedService sDefaultImpl;
        }
        static final int TRANSACTION_isMagiskPresent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        public static boolean setDefaultImpl(com.saartak.el.magisk.IIsolatedService impl) {
            // Only one user of this interface can use this function
            // at a time. This is a heuristic to detect if two different
            // users in the same process use this function.
            if (Stub.Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Stub.Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }
        public static com.saartak.el.magisk.IIsolatedService getDefaultImpl() {
            return Stub.Proxy.sDefaultImpl;
        }
    }
    // Check if Magisk is present

    public boolean isMagiskPresent() throws android.os.RemoteException;
}
