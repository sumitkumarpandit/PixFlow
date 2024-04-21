package com.sumitkumarpandit.pixflow

import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sumitkumarpandit.pixflow.models.PixFlowViewModel
import com.sumitkumarpandit.pixflow.network.ApiResponse
import com.sumitkumarpandit.pixflow.util.SharedPrefManager

@Composable
fun MainScreen(viewModel: PixFlowViewModel, context: Context) {
    var apiKey by remember {
        mutableStateOf(SharedPrefManager.getString(context, "api_key", ""))
    }
    var dialogState by remember {
        mutableStateOf(apiKey.isEmpty())
    }
    if (dialogState) {
        TextInputDialog(showDialog = dialogState, onDismiss = {
            dialogState = false
        }, onConfirm = {
            dialogState = false
            Log.e("ddf", "onCreate: $it")
            apiKey = it
            SharedPrefManager.saveString(context, "api_key", it)
        })
    } else {
        viewModel.getPictureUrls(apiKey)
        Log.e("wwe", "onCreate: $apiKey")
        val pictures by viewModel.pictures.collectAsState()
        val picturesResponse by viewModel.picturesResponse.collectAsState()


        when (val result = picturesResponse) {
            is ApiResponse.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ApiResponse.Success -> {
                Log.e("ssd", "onCreate: $pictures")
                ImageGridComponent(viewModel, pictures, context)
            }

            is ApiResponse.Error -> {
                Log.e("ERROR", "Api Error: ${result.message}")
                ApiErrorScreen()
            }
        }
    }
}

@Composable
fun ApiErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_data),
            contentDescription = "", modifier = Modifier.size(200.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(150.dp, 60.dp)
        ) {
            Text(text = "Retry")
        }
    }
}