package com.achmadabrar.myapplication.data.models

import com.google.gson.annotations.SerializedName

data class Leagues (
    @SerializedName("idLeague")
    val id: Long,
    @SerializedName("strLeague")
    val leagueName: String,
    @SerializedName("strWebsite")
    val website: String,
    @SerializedName("strFacebook")
    val facebook: String,
    @SerializedName("strTwitter")
    val twitter: String,
    @SerializedName("strYoutube")
    val youtube: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strLogo")
    val logo: String
)