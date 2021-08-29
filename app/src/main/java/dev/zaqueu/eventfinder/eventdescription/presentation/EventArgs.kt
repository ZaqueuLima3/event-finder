package dev.zaqueu.eventfinder.eventdescription.presentation

import android.os.Parcelable
import dev.zaqueu.eventfinder.common.domain.model.Event
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventArgs(
    val id: String,
    val title: String,
    val description: String,
    val image: String
) : Parcelable


fun Event.toEventArgs() = EventArgs(
    id = id,
    title = title,
    description = description,
    image = image
)
