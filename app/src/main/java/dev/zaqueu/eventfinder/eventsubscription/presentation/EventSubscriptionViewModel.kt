package dev.zaqueu.eventfinder.eventsubscription.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import dev.zaqueu.eventfinder.eventsubscription.domain.usecase.CheckInEventUseCase
import kotlinx.coroutines.launch

class EventSubscriptionViewModel(
    private val checkInEventUseCase: CheckInEventUseCase
) : ViewModel() {
    private val _checkInState = MutableLiveData<CheckInState>()
    val checkInState = _checkInState

    fun checkInEvent(checkIn: CheckIn) {
        viewModelScope.launch {
            val result = checkInEventUseCase(checkIn)
            result.onSuccess { _checkInState.postValue(CheckInState.Success) }
            result.onFailure { _checkInState.postValue(CheckInState.Error) }
        }
    }
}

sealed class CheckInState {
    object Success : CheckInState()
    object Error : CheckInState()
}
