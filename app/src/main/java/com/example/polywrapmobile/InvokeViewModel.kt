package com.example.polywrapmobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InvokeViewModel() : ViewModel() {
    private val _client = NativeClient(mapOf(
        "wrap://ens/add.eth" to
                "wrap://http/https://raw.githubusercontent.com/namesty/test-wrappers/main/subinvoke"
    ))

    private var _uri = ""
    private var _method = ""
    private var _args = ""

    private var _invokeResult = String()

    fun invoke() {
        _invokeResult = _client.invoke(this._uri, this._method, this._args)

        println(_invokeResult)
    }

    fun uriChanged(uri: String) {
        this._uri = uri;
    }

    fun methodChanged(method: String) {
        this._method = method;
    }

    fun argsChanged(args: String) {
        this._args = args;
    }

    private fun isInvokeDataValid(username: String): Boolean {
        return true
    }
}