package com.example.jetpack_compose_samples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_samples.ui.theme.Jetpack_compose_samplesTheme
import com.example.jetpack_compose_samples.viewmodel.MainViewModel
import com.example.jetpack_compose_samples.viewmodel.MyItem

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view()
        viewModel.onInit()
    }

    private fun view() {
        setContent {
            MaterialTheme.colorScheme
            Jetpack_compose_samplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items: SnapshotStateList<MutableState<MyItem>> = viewModel.mutableState
                    LazyColumn {
                        this.itemsIndexed(items = items,
                            itemContent = { index: Int, item: MutableState<MyItem> ->
                                when (index) {
                                    0 -> Button(onClick = {
                                        viewModel.addItem()
                                    }, modifier = Modifier.padding(24.dp)) {
                                        Text("Add Item")
                                    }
                                    else -> MyText(index, item.value)
                                }

                            },
                            contentType = { _: Int, item: MutableState<MyItem> -> item })
                    }
                    //TodoList()
                }
            }
        }
    }

    @Composable
    fun MyText(index: Int, item: MyItem) {
        Log.d("MainActivity", "item $item")
        Text(
            text = "$index: Text ${item.count}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .clickable {
                    viewModel.update(index)
                },
        )
    }
}


