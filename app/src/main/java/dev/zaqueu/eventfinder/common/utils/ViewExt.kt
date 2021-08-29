package dev.zaqueu.eventfinder.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.handleSingleClick(debounceTime: Long = 500L, callback: () -> Unit) {
    if (this.isClickable) {
        this.isClickable = false
        callback()
        this.postDelayed(
            {
                this.isClickable = true
            },
            debounceTime
        )
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
