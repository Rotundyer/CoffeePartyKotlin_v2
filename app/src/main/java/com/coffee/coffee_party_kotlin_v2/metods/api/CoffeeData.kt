package com.coffee.coffee_party_kotlin_v2.metods.api

import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("count") var count: Int,
    @SerializedName("results") var results: List<Result>
) {
    data class Result(
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("image") var image: String
    )
}

data class Types(
    @SerializedName("count") var count: Int,
    @SerializedName("results") var results: List<Result>
) {
    data class Result(
        @SerializedName("id") var id: Int,
        @SerializedName("title") var title: String
    )
}