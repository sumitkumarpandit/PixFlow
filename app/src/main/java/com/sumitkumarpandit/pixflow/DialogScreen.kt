package com.sumitkumarpandit.pixflow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            title = { Text(text = "Enter Unsplash API Key") },
            confirmButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(text = "Cancel")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Do something with the text input when confirm button is clicked
                        onConfirm(textFieldValue.text)
                        onDismiss()
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            text = {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    BasicTextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
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
