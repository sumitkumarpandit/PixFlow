package com.sumitkumarpandit.pixflow.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun PixFlowButton(modifier: Modifier, buttonText:String,color: Color,clickable:Boolean, onButtonClick: () -> Unit) {
    Button(
        modifier = modifier,
        border = BorderStroke(2.dp, color),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = clickable,
        onClick = {
            onButtonClick()
        },
    ) {
        Text(
            text = buttonText, color = color, style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
