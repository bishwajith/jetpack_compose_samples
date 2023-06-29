package com.example.jetpack_compose_samples.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class MyItem(val index: Int, val count: Int)

data class MyState(val items: MutableList<MutableState<MyItem>>)

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    val mutableState = mutableStateListOf<MutableState<MyItem>>()

    fun onInit() {
        viewModelScope.launch(Dispatchers.Default) {
            List(5) {
                mutableState.add(mutableStateOf(MyItem(it, 0)))
            }
        }
    }

    fun update(index: Int) {
        val item = mutableState[index]
        val newItem: MyItem = item.value.copy(count = item.value.count.plus(1))
//        state.items[index] = mutableStateOf(newItem)
        item.value = newItem
        mutableState[index] = item
    }

    fun addItem() {
        val lastItem = mutableState.last().value
        mutableState.add(mutableStateOf(lastItem.copy(index = lastItem.index + 1, count = 0)))
    }
}