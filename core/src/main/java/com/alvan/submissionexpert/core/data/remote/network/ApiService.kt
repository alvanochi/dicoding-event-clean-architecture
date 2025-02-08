package com.alvan.submissionexpert.core.data.remote.network

import com.alvan.submissionexpert.core.data.remote.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int = -1,
        @Query("limit") limit: Int? = null,
        @Query("q") query: String? = null
    ): EventResponse

}