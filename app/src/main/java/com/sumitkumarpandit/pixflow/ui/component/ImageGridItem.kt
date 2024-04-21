package com.sumitkumarpandit.pixflow.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ImageItem(imageBitmap: ImageBitmap?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(120.dp, 180.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        } ?: run {
            ShimmerItem()
        }

    }
}


@Composable
fun ShimmerItem() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
        )
    }
}