package com.sumitkumarpandit.pixflow.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumitkumarpandit.pixflow.network.ApiInterface
import com.sumitkumarpandit.pixflow.network.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.create

class  PixFlowViewModel:ViewModel(){
    fun fetchData (apiKey:String){
        viewModelScope.launch {
            try {
                val rService = RetrofitService.getInstance().create(ApiInterface::class.java)
                val response = rService.getPictures(apiKey,1,30)
                Log.i("INFOP", "fetchData: $response")
            }
            catch (ex:Exception){
                Log.e("ERRORP", "fetchData: $ex", )
            }
        }
    }
}