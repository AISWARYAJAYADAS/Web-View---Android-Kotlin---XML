package com.qburst.webview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.qburst.webview.databinding.CustomSearchInputTextBinding

class CustomSearchInputText(context: Context,attrs: AttributeSet):ConstraintLayout(context,attrs) {
    private var binding: CustomSearchInputTextBinding = CustomSearchInputTextBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        setupCustomAttributes(attrs = attrs)
        setupListeners()
    }

    // setup custom attributes
    private fun setupCustomAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.customSearchInput, 0, 0)
        typedArray.recycle()
    }

    // set up listener
    private fun setupListeners() {
        // Set up text change listener for editText
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        // close icon
        binding.closeIcon.setOnClickListener {
            clearText()
        }
    }

    private fun clearText() {
        binding.editText.text?.clear()
    }

}