package com.sdos.commerce.data.models

import com.google.gson.annotations.SerializedName

data class Website(

    @SerializedName("url")
    private var url: String
)