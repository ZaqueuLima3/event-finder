package dev.zaqueu.eventfinder.common.utils

import android.view.View

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
