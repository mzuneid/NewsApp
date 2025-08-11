package com.mzdev.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzdev.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.mzdev.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
    private  set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
    private set


    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            startDestination = if (shouldStartFromHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            delay(200) //Without this delay, the onBoarding screen will show for a momentum.
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}