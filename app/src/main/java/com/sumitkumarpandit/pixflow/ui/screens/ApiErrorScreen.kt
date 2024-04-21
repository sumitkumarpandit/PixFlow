package com.sumitkumarpandit.pixflow.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sumitkumarpandit.pixflow.R
import com.sumitkumarpandit.pixflow.ui.component.PixFlowButton
import com.sumitkumarpandit.pixflow.ui.theme.SkyBlue

@Composable
fun ApiErrorScreen(code: Int, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (code) {
            401 -> {
                Image(
                    painter = painterResource(R.drawable.unauthorized),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
                PixFlowButton(
                    modifier = Modifier.size(100.dp, 40.dp),
                    buttonText = "Retry",
                    SkyBlue,
                    true
                ) {
                    onRetryClick()
                }
            }

            403 -> {
                Image(
                    painter = painterResource(R.drawable.missing_permission),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "Limit Exceeded",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            404 -> {
                Image(
                    painter = painterResource(R.drawable.error_404),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
            }

            else -> {
                Image(
                    painter = painterResource(R.drawable.no_data),
                    contentDescription = "", modifier = Modifier.size(200.dp)
                )
            }
        }

    }
}