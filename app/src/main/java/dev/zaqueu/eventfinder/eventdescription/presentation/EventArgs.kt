package dev.zaqueu.eventfinder.eventdescription.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventArgs(
    val title: String,
    val description: String,
    val image: String
) : Parcelable
