package com.sumitkumarpandit.pixflow

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.sumitkumarpandit.imagefluxlibrary.ImageCache
import com.sumitkumarpandit.imagefluxlibrary.ImageLoader
import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.models.PixFlowViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ImageGridComponent(viewModel: PixFlowViewModel, imageList: List<UPictures>, context: Context) {
    val scrollState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        Log.e("errorp", "ImageGridComponent: $viewModel" )
        items(imageList.size) { idx ->
            val coroutineScope = rememberCoroutineScope()
            var bitmapState by remember { mutableStateOf<Bitmap?>(null) }

            LaunchedEffect(coroutineScope) {
                this.launch {
                    Log.e("ERRORP", "ImageGridComponent: ${imageList[idx].urls.regular}", )
                    val bitmap =
                        loadBitmap(imageList[idx].urls.regular, context, imageList[idx].id)
                    bitmapState = bitmap
                }
            }
            ImageItem(imageBitmap = bitmapState?.asImageBitmap())
            if (idx == imageList.size - 1) {
                LaunchedEffect(scrollState) {
                    if (idx == scrollState.layoutInfo.totalItemsCount - 1) {
                        viewModel.getPictureUrls(BuildConfig.API_KEY)
                    }
                }
            }

        }
    }
}


suspend fun loadBitmap(url: String, context: Context, id: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        val cachedBitmap = ImageCache.loadBitmap(context, id)
        if (cachedBitmap != null) {
            cachedBitmap
        } else {
            val bitmapFromUrl = ImageLoader.getBitmapFromURL(url)
            bitmapFromUrl?.let {
                ImageCache.storeBitmap(it, context, id)
                it
            }
        }
    }
}

