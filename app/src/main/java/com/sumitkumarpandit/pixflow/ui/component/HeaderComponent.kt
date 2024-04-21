package com.sumitkumarpandit.pixflow.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sumitkumarpandit.pixflow.R
import com.sumitkumarpandit.pixflow.ui.theme.NavyBlue
@Composable
fun HeaderComponent() {
    Row(modifier = Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.ic_app_logo), contentDescription = "")
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Bold,
            color = NavyBlue
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComp() {
    HeaderComponent()
}