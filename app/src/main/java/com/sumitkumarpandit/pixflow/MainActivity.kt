package com.sumitkumarpandit.pixflow

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sumitkumarpandit.imagefluxlibrary.ImageCache
import com.sumitkumarpandit.imagefluxlibrary.ImageLoader
import com.sumitkumarpandit.pixflow.data.UPictures
import com.sumitkumarpandit.pixflow.models.PixFlowViewModel
import com.sumitkumarpandit.pixflow.network.ApiInterface
import com.sumitkumarpandit.pixflow.network.ApiResponse
import com.sumitkumarpandit.pixflow.network.RetrofitService
import com.sumitkumarpandit.pixflow.repository.PixFlowRepository
import com.sumitkumarpandit.pixflow.ui.theme.PixFlowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PixFlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel.getPictureUrls(BuildConfig.API_KEY)
                    SplashScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(viewModel: PixFlowViewModel) {
    val context = LocalContext.current
    val pictures by viewModel.pictures.collectAsState()
    ImageGrid(viewModel, pictures, context)


//    when (val result = imageData) {
//        is ApiResponse.Loading -> {
//            // Show loading UI
//                Log.e("ERRORP", "SplashScreen: LOADING", )
////            Text(text = "Loading...")
//        }
//        is ApiResponse.Success -> {
//            // Show success UI
//            Log.e("ERRORP", "SplashScreen: ${result.data}", )
//            ImageGrid(viewModel,imageList = result.data,context)
//        }
//        is ApiResponse.Failure -> {
//            // Show failure UI
//            Log.e("ERRORP", "SplashScreen: Error", )
//
//            Text(text = "Error: ${result.errorCode}")
//        }
//
//        else -> {
//
//        }
//    }
}

@Composable
fun ImageGrid(viewModel: PixFlowViewModel, imageList: List<UPictures>, context: Context) {
    val scrollState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        items(imageList.size) { idx ->
            val coroutineScope = rememberCoroutineScope()
            var bitmapState by remember { mutableStateOf<Bitmap?>(null) }

            LaunchedEffect(coroutineScope) {
                this.launch {
                    if (idx in scrollState.firstVisibleItemIndex..<imageList.size) {
                        val bitmap =
                            loadBitmap(imageList[idx].urls.regular, context, imageList[idx].id)
                        bitmapState = bitmap
                    }
                }
            }
            bitmapState?.let { loadedBitmap ->
                ImageItem(bitmap = loadedBitmap.asImageBitmap())
            }
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

@Composable
fun ImageItem(bitmap: ImageBitmap) {
    if (bitmap != null)
        Image(bitmap = bitmap, contentDescription = "")
    else
        Image(painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PixFlowTheme {
    }
}