package com.example.android.politicalpreparedness.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/civicinfo/v2/"

// TODO: Add adapters for Java Date and custom adapter ElectionAdapter (included in project)
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(CivicsHttpClient.getClient())
        .baseUrl(BASE_URL)
        .build()

interface CivicsApiService {
    //TODO: Add elections API Call
    @GET("elections")
    suspend fun getElection()

    //TODO: Add voterinfo API Call
    @GET("voterinfo")
    suspend fun getVoteInfo(
        @Query("address") address:String,
        @Query("electionId") electionId:String
    )

    //TODO: Add representatives API Call
    @GET("representatives")
    suspend fun getRepresentativeAdrress(
        @Query("address") address:String
    )

    @GET("representatives/ocdId")
    suspend fun getRepresentativeOcId(
        @Query("ocdId") ocdId:String
    )

}

object CivicsApi {
    val retrofitService: CivicsApiService by lazy {
        retrofit.create(CivicsApiService::class.java)
    }
}