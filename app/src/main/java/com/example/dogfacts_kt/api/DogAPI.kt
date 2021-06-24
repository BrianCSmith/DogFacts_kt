package com.example.dogfacts_kt.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

class DogAPI {

    val imageService: DogImageService by lazy {
        dogImageRetroFit.create(DogImageService::class.java)
    }

    val factService: DogFactService by lazy {
        dogFactRetroFit.create(DogFactService::class.java)
    }
//

    interface DogImageService {
        @GET("{count}")
        suspend fun getDogImages(
            @Path("count")
            urlCount: Int
        ): DogImageResponse
    }

    interface DogFactService {
        @GET("dogs")
        suspend fun getDogFacts(
            @Query("number")
            factCount: Int
        ): ArrayList<DogFact>


    }
}