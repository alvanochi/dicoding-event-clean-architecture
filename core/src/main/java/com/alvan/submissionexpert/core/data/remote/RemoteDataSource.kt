package com.alvan.submissionexpert.core.data.remote

import com.alvan.submissionexpert.core.data.remote.network.ApiResponse
import com.alvan.submissionexpert.core.data.remote.network.ApiService
import com.alvan.submissionexpert.core.data.remote.response.ListEventsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllEvent(): Flow<ApiResponse<List<ListEventsItem>>> {
        return flow {
            try {
                val response = apiService.getEvents()
                val dataArray = response.listEvents
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.listEvents))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

