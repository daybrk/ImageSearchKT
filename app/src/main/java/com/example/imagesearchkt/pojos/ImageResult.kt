package com.example.imagesearchkt.pojos

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ImageResult {
    @SerializedName("position")
    @Expose
    var position: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("original")
    @Expose
    var original: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("is_product")
    @Expose
    var isProduct: String? = null

    @SerializedName("in_stock")
    @Expose
    var inStock: String? = null
}