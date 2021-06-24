package com.example.dogfacts_kt.api

import com.google.gson.annotations.SerializedName

data class DogImageResponse(@SerializedName("message") val urlsList: ArrayList<String> , val status : String )

