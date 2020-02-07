package com.example.baselib.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.EditText
import com.blankj.utilcode.util.SizeUtils

class AutoChangeSizeEditText : EditText {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, set: AttributeSet) : super(context, set) {
        init()
    }

    constructor(context: Context?, set: AttributeSet, defStyleAttr: Int) : super(context, set, defStyleAttr) {
        init()
    }

    fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 0) {
                    this@AutoChangeSizeEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                } else {
                    this@AutoChangeSizeEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                }
            }
        })
    }
}