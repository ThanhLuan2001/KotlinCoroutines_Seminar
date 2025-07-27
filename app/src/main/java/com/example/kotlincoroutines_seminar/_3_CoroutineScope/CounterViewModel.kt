package com.example.kotlincoroutines_seminar._3_CoroutineScope

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    fun startCount() = viewModelScope.launch {
        for (i in 1..100) {
            Log.e("LuanNT", "ViewModelScope Item: $i")
            delay(1000L)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("LuanNT", "onCleared")
    }


}
