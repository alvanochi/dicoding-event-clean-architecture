package com.alvan.submissionexpert.core.domain.usecase

import com.alvan.submissionexpert.core.data.Resource
import com.alvan.submissionexpert.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventUseCase {
    fun getAllEvent(): Flow<Resource<List<Event>>>

    fun getFavoriteEvent(): Flow<List<Event>>

    fun setFavoriteEvent(event: Event, state: Boolean)
}