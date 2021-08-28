package dev.zaqueu.eventfinder.eventfinder.presentation

import dev.zaqueu.eventfinder.common.domain.model.Event

sealed interface EventFinderViewState {
    object Loading : EventFinderViewState
    data class Success(val events: List<Event>) : EventFinderViewState
    data class Error(val error: Throwable) : EventFinderViewState
}
