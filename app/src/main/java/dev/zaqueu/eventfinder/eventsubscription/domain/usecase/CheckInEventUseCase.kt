package dev.zaqueu.eventfinder.eventsubscription.domain.usecase

import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository

class CheckInEventUseCase(
    private val eventRepository: EventRepository
) {
    suspend operator fun invoke(checkIn: CheckIn): Result<Unit> {
        return try {
            eventRepository.checkInEvent(checkIn)
            Result.success(Unit)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}
