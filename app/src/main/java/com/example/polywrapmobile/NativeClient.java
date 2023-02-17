package com.example.polywrapmobile;

import java.util.Map;

public class NativeClient {
    private long clientPtr;

    public NativeClient(Map<String, String> redirects) {
        nStartLogger();
        long resolverPtr = nCreateResolver(redirects);
        long clientPtr = nCreateClient(resolverPtr);

        this.clientPtr = clientPtr;
    }

    public String invoke(String uri, String method, String args) {
        return nInvoke(this.clientPtr, uri, method, args);
    }

    static {
        System.loadLibrary("polywrap_ffi_client");
    }

    private static native long nCreateResolver(Map<String, String> redirects);
    private static native void nDestructResolver(long resolverPtr);
    private static native void nStartLogger();
    private static native long nCreateClient(long resolverPtr);
    private static native void nDestructClient(long clientPtr);
    private static native String nInvoke(long clientPtr,
                                         String uri,
                                         String method,
                                         String args);
}

