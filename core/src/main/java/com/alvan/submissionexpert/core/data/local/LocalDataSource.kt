package com.alvan.submissionexpert.core.data.local

import com.alvan.submissionexpert.core.data.local.entity.EventEntity
import com.alvan.submissionexpert.core.data.local.room.EventDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val eventDao: EventDao) {

    fun getAllEvent(): Flow<List<EventEntity>> = eventDao.getAllEvent()

    fun getFavoriteEvent(): Flow<List<EventEntity>> = eventDao.getFavoriteEvent()

    suspend fun insertEvent(eventList: List<EventEntity>) = eventDao.insertEvent(eventList)

    fun setFavoriteEvent(event: EventEntity, newState: Boolean) {
        event.isFavorite = newState
        eventDao.updateFavoriteEvent(event)
    }
}