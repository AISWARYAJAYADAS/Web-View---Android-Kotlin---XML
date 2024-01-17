package com.qburst.webview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.qburst.webview.databinding.CustomToolbarBinding

class CustomToolBar(context: Context, attrs:AttributeSet):ConstraintLayout(context, attrs) {
    private var binding: CustomToolbarBinding = CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        setupCustomAttributes(attrs = attrs)

    }

    // setup custom attributes
    private fun setupCustomAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomToolBarView, 0, 0)
        typedArray.recycle()
    }

    fun setBackButtonClickListener(listener : OnClickListener){
        val backButton = binding.backArrow
        backButton.setOnClickListener(listener)
    }
}