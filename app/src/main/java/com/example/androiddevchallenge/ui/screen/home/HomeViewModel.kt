package com.example.androiddevchallenge.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.screen.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlin.math.min
import kotlin.random.Random

class HomeViewModel(private val puppies: Flow<List<Puppy>>) : BaseViewModel() {

    private var index = 1
    private val invokeFlow = MutableStateFlow(index)
    private val state: Flow<HomeState> = invokeFlow
        .flatMapLatest { puppies }
        .map<List<Puppy>, HomeState> { HomeState.Success(it) }
        .catch { emit(HomeState.Failure) }
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    private var manualFlow = MutableSharedFlow<HomeState>(extraBufferCapacity = 1)

    val homeState = merge(state, manualFlow)
        .stateIn(viewModelScope, SharingStarted.Lazily, HomeState.Loading)

    fun loadPuppies() {
        manualFlow.tryEmit(HomeState.Loading)
        invokeFlow.value = ++index
    }

    fun forceFailure() {
        manualFlow.tryEmit(HomeState.Failure)
    }
}

sealed class HomeState {
    object Initial : HomeState()
    object Failure : HomeState()
    object Loading : HomeState()
    data class Success(val puppies: List<Puppy>) : HomeState()
}

