package com.side.runwithme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.side.runwithme.utils.MutableEventFlow
import com.side.runwithme.utils.asEventFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class EventViewModel: ViewModel() {

    protected val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    sealed class Event {
        object Loading: Event()
        data class Success(val code: Int): Event()
        object Error: Event()
    }
}