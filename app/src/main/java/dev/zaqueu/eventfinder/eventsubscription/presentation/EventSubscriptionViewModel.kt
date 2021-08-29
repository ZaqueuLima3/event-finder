package dev.zaqueu.eventfinder.eventsubscription.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.zaqueu.eventfinder.common.domain.model.CheckIn

class EventSubscriptionViewModel : ViewModel() {
    private val _isSucceed = MutableLiveData<Boolean>()
    val isSucceed = _isSucceed

    fun checkInEvent(checkIn: CheckIn) {
        _isSucceed.postValue(true)
    }
}
