package dev.zaqueu.eventfinder.eventfinder.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zaqueu.eventfinder.eventfinder.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.launch

class EventFinderViewModel(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {
    private val _eventViewState = MutableLiveData<EventFinderViewState>()
    val eventViewState = _eventViewState

    fun getEvents() {
        viewModelScope.launch {
            _eventViewState.postValue(EventFinderViewState.Loading)
            val result = getEventsUseCase()
            result.onSuccess { _eventViewState.postValue(EventFinderViewState.Success(it)) }
            result.onFailure { _eventViewState.postValue(EventFinderViewState.Error(it)) }
        }
    }
}
