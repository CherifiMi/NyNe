package com.example.nyne

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyne.data.NyneDataStore
import com.example.nyne.ui.navigation.BottomBarScreen
import com.example.nyne.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val welcomeDataStore: NyneDataStore) : ViewModel(){
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> =
        mutableStateOf(Screens.WelcomeScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            welcomeDataStore.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = BottomBarScreen.Home.route
                } else {
                    _startDestination.value = Screens.WelcomeScreen.route
                }

                delay(100)
                _isLoading.value = false
            }
        }
    }

}