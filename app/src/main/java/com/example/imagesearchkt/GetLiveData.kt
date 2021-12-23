package com.example.imagesearchkt


import androidx.lifecycle.LiveData
import com.example.imagesearchkt.pojos.ImageResult
import androidx.lifecycle.MutableLiveData
import com.example.imagesearchkt.api.GoogleSearch
import com.google.gson.JsonObject
import com.example.imagesearchkt.api.SerpApiSearchException
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class GetLiveData {

    val data: LiveData<List<ImageResult>>
        get() = sImageData

    companion object {
        private val sImageData = MutableLiveData<List<ImageResult>>()
        val instance: GetLiveData
            get() = GetLiveData()
        private val imageList: MutableList<ImageResult> = ArrayList()

        fun addImageToList(query: String) {
            imageList.clear()
            try {
                val parameter: MutableMap<String, String> = HashMap()

                // parameters
                parameter["q"] = query
                parameter["tbm"] = "isch"
                parameter["ijn"] = "0";
                parameter["api_key"] = "bc7fed1a4c4359aaaf2ad45f4047efda43c50fc07824bd3b6a58effc95314db0"

                val search =
                    GoogleSearch(parameter)
                try {
                    val data: JsonObject = search.getJson()
                    val results = data["images_results"].asJsonArray
                    for (i in 0 until results.size()) {
                        val imageResult = ImageResult()
                        imageResult.position = results[i].asJsonObject["position"].asString
                        imageResult.title = results[i].asJsonObject["title"].asString
                        imageResult.link = results[i].asJsonObject["link"].asString
                        imageResult.original = results[i].asJsonObject["original"].asString
                        imageResult.source = results[i].asJsonObject["source"].asString
                        imageResult.thumbnail = results[i].asJsonObject["thumbnail"].asString
                        imageList.add(imageResult)
                    }
                    sImageData.postValue(imageList)
                } catch (ex: SerpApiSearchException) {
                    println("oops exception detected!")
                    ex.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}