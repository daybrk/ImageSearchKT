package com.example.imagesearchkt

import androidx.appcompat.widget.SearchView
import com.example.imagesearchkt.GetLiveData

class SearchTextListener : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String): Boolean {
        val gfgThread = Thread { GetLiveData.addImageToList(query) }
        gfgThread.start()
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}