package com.sumitkumarpandit.pixflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sumitkumarpandit.pixflow.models.PixFlowViewModel
import com.sumitkumarpandit.pixflow.ui.component.HeaderComponent
import com.sumitkumarpandit.pixflow.ui.screens.MainScreen
import com.sumitkumarpandit.pixflow.ui.theme.PixFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PixFlowViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixFlowTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        HeaderComponent()
                        MainScreen(viewModel)
                    }
                }
            }
        }
    }
}