package com.ramm.cuscatlanpokemon.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<VIEWSTATE, VIEWCOMMAND, VIEWINTENT>: ViewModel() {
    private val _viewState = MutableStateFlow(initialState)

    @CallSuper
    protected fun setState(state: VIEWSTATE) {
        _viewState.value = state
    }

    val viewState: StateFlow<VIEWSTATE>
        get() = _viewState

    val currentState: VIEWSTATE
        get() = _viewState.value

    abstract val initialState: VIEWSTATE

    private val _viewCommand by lazy {
        MutableSharedFlow<ConsumableValue<VIEWCOMMAND>>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    @CallSuper
    fun sendCommand(command: VIEWCOMMAND) {
        _viewCommand.tryEmit(ConsumableValue(command))
    }

    val viewCommand: SharedFlow<ConsumableValue<VIEWCOMMAND>>
        get() = _viewCommand

    open fun onIntent(intent: VIEWINTENT) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    private val intentFlow = MutableSharedFlow<VIEWINTENT>()

    abstract suspend fun handleIntent(intent: VIEWINTENT)

    init {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                handleIntent(intent)
            }
        }
    }
}