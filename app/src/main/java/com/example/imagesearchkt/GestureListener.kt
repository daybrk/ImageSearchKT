package com.example.imagesearchkt

import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import com.squareup.picasso.Picasso
import android.widget.Toast

internal class GestureListener : SimpleOnGestureListener() {
    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (velocityX > 1000) {
            if (MainActivity.position != 0) {
                MainActivity.position = MainActivity.position - 1
                Picasso.with(MainActivity.appContext)
                    .load(MainActivity.getsImageList()[MainActivity.position].original)
                    .into(MainActivity.getsImageSwap())
            } else {
                Toast.makeText(MainActivity.appContext, "Это первое изображение назад некуда", Toast.LENGTH_SHORT).show()
            }
        } else if (velocityX < -1000) {
            MainActivity.position = MainActivity.position + 1
            Picasso.with(MainActivity.appContext)
                .load(MainActivity.getsImageList()[MainActivity.position].original)
                .into(MainActivity.getsImageSwap())
        }
        return super.onFling(e1, e2, velocityX, velocityY)
    }
}