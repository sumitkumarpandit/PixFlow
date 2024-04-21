package com.sumitkumarpandit.pixflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sumitkumarpandit.pixflow.ui.component.PixFlowButton
import com.sumitkumarpandit.pixflow.ui.theme.BluishWhite
import com.sumitkumarpandit.pixflow.ui.theme.NavyBlue
import com.sumitkumarpandit.pixflow.ui.theme.SkyBlue

@Composable
fun TextInputDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (showDialog) {
        var textFieldValue by remember {
            mutableStateOf(TextFieldValue())
        }
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Enter API Key",
                    style = MaterialTheme.typography.titleSmall,
                    color = NavyBlue,
                    modifier = Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                PixFlowButton(
                    modifier = Modifier
                        .size(110.dp, 50.dp),
                    "Confirm", SkyBlue, clickable = textFieldValue.text.isNotEmpty()
                ) {
                    onConfirm(textFieldValue.text)
                    onDismiss()
                }
            },
            dismissButton = {},
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(60.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .background(BluishWhite)
                        .padding(8.dp, 12.dp, 8.dp, 12.dp)
                ) {
                    BasicTextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            containerColor = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTextInputDialog() {
    TextInputDialog(
        showDialog = true,
        onDismiss = {},
        onConfirm = { }
    )
}
