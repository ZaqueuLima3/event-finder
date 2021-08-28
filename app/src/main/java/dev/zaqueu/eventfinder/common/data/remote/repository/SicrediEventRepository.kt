package dev.zaqueu.eventfinder.common.data.remote.repository

import dev.zaqueu.eventfinder.common.data.remote.EventApi
import dev.zaqueu.eventfinder.common.data.remote.mapper.mapToModel
import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository

class SicrediEventRepository(
    private val eventApi: EventApi
) : EventRepository {
    override suspend fun getEvents(): List<Event> {
        return eventApi.getEvents().map { it.mapToModel() }
    }
}
