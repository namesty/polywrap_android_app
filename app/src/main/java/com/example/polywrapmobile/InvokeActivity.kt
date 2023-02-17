package com.example.polywrapmobile

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.polywrapmobile.databinding.ActivityInvokeBinding

class InvokeActivity : AppCompatActivity() {

    private lateinit var invokeViewModel: InvokeViewModel
    private lateinit var binding: ActivityInvokeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInvokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = binding.uri
        val method = binding.method
        val args = binding.args
        val invoke = binding.invoke

        invokeViewModel = ViewModelProvider(this)
                .get(InvokeViewModel::class.java)

        uri?.afterTextChanged {
            invokeViewModel.uriChanged(
                uri.text.toString()
            )
        }

        method?.afterTextChanged {
            invokeViewModel.methodChanged(
                method.text.toString()
            )
        }

        args?.afterTextChanged {
            invokeViewModel.argsChanged(
                args.text.toString()
            )
        }

        invoke?.setOnClickListener {
            invokeViewModel.invoke()
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}