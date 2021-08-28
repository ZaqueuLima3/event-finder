package dev.zaqueu.eventfinder.common.data.remote.mapper

import dev.zaqueu.eventfinder.common.data.remote.model.EventResponse
import dev.zaqueu.eventfinder.common.domain.model.Event

fun EventResponse.mapToModel(): Event {
    return Event(
        id = id,
        date = date,
        description = description,
        image = image,
        latitude = latitude,
        longitude = longitude,
        price = price,
        title = title
    )
}
