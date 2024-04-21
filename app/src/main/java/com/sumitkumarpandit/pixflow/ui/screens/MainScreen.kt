package com.sumitkumarpandit.pixflow.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sumitkumarpandit.pixflow.models.PixFlowViewModel
import com.sumitkumarpandit.pixflow.network.ApiResponse
import com.sumitkumarpandit.pixflow.ui.component.ImageGridComponent
import com.sumitkumarpandit.pixflow.util.SharedPrefManager

@Composable
fun MainScreen(viewModel: PixFlowViewModel) {
    val context = LocalContext.current
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
                ImageGridComponent(viewModel, pictures, context, apiKey)
            }

            is ApiResponse.Error -> {
                ApiErrorScreen(result.code) {
                    dialogState = true
                }
                Log.e("ERROR", "Api Error: ${result.message}")

            }
        }
    }
}


