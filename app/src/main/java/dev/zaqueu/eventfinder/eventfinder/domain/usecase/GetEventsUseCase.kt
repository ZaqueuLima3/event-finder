package dev.zaqueu.eventfinder.eventfinder.domain.usecase

import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository

class GetEventsUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke(): Result<List<Event>> {
        return try {
            val events = eventRepository.getEvents()
            Result.success(events)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}
