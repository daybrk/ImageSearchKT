package com.example.imagesearchkt

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchkt.pojos.ImageResult
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var imageWeb: WebView? = null
    private var mGestureDetector: GestureDetectorCompat? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appContext = applicationContext
        mGestureDetector = GestureDetectorCompat(this, GestureListener())

        imageWeb = findViewById(R.id.image_web)
        imageWeb!!.visibility = View.INVISIBLE

        sImageSwap = findViewById(R.id.image_swap)
        sImageSwap!!.visibility = View.INVISIBLE

        searchBut = findViewById(R.id.search_but)
        searchBut!!.visibility = View.INVISIBLE
        searchBut!!.setOnClickListener {
            imageWeb!!.settings.javaScriptEnabled = true
            imageWeb!!.loadUrl(sImageList[position].link!!)
            imageWeb!!.webViewClient = WebViewer().webViewClient
            imageWeb!!.visibility = View.VISIBLE
            searchBut!!.visibility = View.INVISIBLE
        }

        imageRecycler = findViewById(R.id.rec_im)
        imageRecycler!!.layoutManager = GridLayoutManager(this, 3)
        GetLiveData.instance.data.observe(this, { imageResults ->
                imageRecycler!!.adapter = ImVRecAdapter(imageResults)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search photo here"
        searchView.setOnQueryTextListener(SearchTextListener())
        return super.onCreateOptionsMenu(menu)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mGestureDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onBackPressed() {
        if (sImageSwap!!.visibility == View.VISIBLE) {
            sImageSwap!!.visibility = View.INVISIBLE
            imageRecycler!!.visibility = View.VISIBLE
            imageWeb!!.visibility = View.INVISIBLE
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private var imageRecycler: RecyclerView? = null
        @SuppressLint("StaticFieldLeak")
        private var sImageSwap: ImageView? = null
        @SuppressLint("StaticFieldLeak")
        private var searchBut: Button? = null
        private var sImageList: List<ImageResult> = ArrayList<ImageResult>()
        var appContext: Context? = null
            private set
        var position = 0

        fun setImage(imageView: ImageView, position: Int, imageResults: List<ImageResult>) {
            sImageSwap!!.setImageDrawable(imageView.drawable)
            sImageSwap!!.visibility = View.VISIBLE
            searchBut!!.visibility = View.VISIBLE
            imageRecycler!!.visibility = View.INVISIBLE
            sImageList = imageResults
            Companion.position = position
        }

        fun getsImageList(): List<ImageResult> {
            return sImageList
        }

        fun getsImageSwap(): ImageView? {
            return sImageSwap
        }
    }
}