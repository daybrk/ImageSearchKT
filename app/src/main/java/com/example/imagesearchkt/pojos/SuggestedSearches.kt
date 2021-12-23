package com.example.imagesearchkt.pojos

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class SuggestedSearches {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("chips")
    @Expose
    var chips: String? = null

    @SerializedName("serpapi_link")
    @Expose
    var serpapiLink: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
}