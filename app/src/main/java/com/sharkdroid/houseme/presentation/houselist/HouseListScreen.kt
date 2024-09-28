package com.sharkdroid.houseme.presentation.houselist

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sharkdroid.houseme.domain.model.RoomData
import com.sharkdroid.houseme.presentation.viewmodel.HomeScreenViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HouseListScreen(viewModel: HomeScreenViewModel = viewModel()) {

    val corourtine = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        corourtine.launch {


            val rooms = viewModel.hlo.collectLatest {


                Log.d("pawon", it.toString())
            }
        }
    }
}






//    Column (modifier = Modifier.fillMaxSize()){
//
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Display each room in the list
//            items(rooms) { room ->
//                com.sharkdroid.houseme.presentation.houselist.RoomItem(room)
//            }
//        }
//
//    }


