package com.dm.berxley.expensetracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.expensetracker.presentation.navgraph.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var showSplash by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Screen.CoreNavigator.route)
        private set

    var selectedBottomIndex by mutableIntStateOf(0)
        private set

    init {
        viewModelScope.launch {
            delay(2000)

//            //check if looged in and assign start page
//            if (true){
//                startDestination = Screen.CoreNavigator.route
//            }else{
//                startDestination = Screen.OnboardingNavigator.route
//            }
            showSplash = false
        }
    }

    fun setBottomIndex(index: Int){
        selectedBottomIndex = index
    }


}