package com.example.imagesearchkt

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchkt.pojos.ImageResult
import com.squareup.picasso.Picasso

class ImVRecAdapter(private var imageResults: List<ImageResult>) : RecyclerView.Adapter<ImVRecAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rec_im_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Picasso.with(MainActivity.appContext)
            .load(imageResults[position].original)
            .into(holder.uploadImage)
        holder.uploadImage.setOnClickListener {
            MainActivity.setImage(
                holder.uploadImage,
                position,
                imageResults
            )
        }
    }

    fun getImageResults(): List<ImageResult> {
        return imageResults
    }

    override fun getItemCount(): Int {
        return imageResults.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uploadImage: ImageView = itemView.findViewById(R.id.upload_iv)
    }

}