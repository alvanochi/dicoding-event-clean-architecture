package com.alvan.submissionexpert.core.data

import com.alvan.submissionexpert.core.data.local.LocalDataSource
import com.alvan.submissionexpert.core.data.remote.RemoteDataSource
import com.alvan.submissionexpert.core.data.remote.network.ApiResponse
import com.alvan.submissionexpert.core.data.remote.response.ListEventsItem
import com.alvan.submissionexpert.core.domain.model.Event
import com.alvan.submissionexpert.core.domain.repository.IEventRepository
import com.alvan.submissionexpert.core.utils.AppExecutors
import com.alvan.submissionexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IEventRepository {

    override fun getAllEvent(): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<ListEventsItem>>() {
            override fun loadFromDB(): Flow<List<Event>> {
                return localDataSource.getAllEvent().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Event>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ListEventsItem>>> =
                remoteDataSource.getAllEvent()

            override suspend fun saveCallResult(data: List<ListEventsItem>) {
                val eventList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertEvent(eventList)
            }
        }.asFlow()

    override fun getFavoriteEvent(): Flow<List<Event>> {
        return localDataSource.getFavoriteEvent().map { DataMapper.mapEntitiesToDomain(it) }

    }


    override fun setFavoriteEvent(event: Event, state: Boolean) {
        val eventEntity = DataMapper.mapDomainToEntity(event)
        appExecutors.diskIO().execute { localDataSource.setFavoriteEvent(eventEntity, state) }
    }
}