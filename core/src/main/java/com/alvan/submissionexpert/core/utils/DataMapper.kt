package com.alvan.submissionexpert.core.utils

import com.alvan.submissionexpert.core.data.local.entity.EventEntity
import com.alvan.submissionexpert.core.data.remote.response.ListEventsItem
import com.alvan.submissionexpert.core.domain.model.Event

object DataMapper {
    fun mapResponsesToEntities(input: List<ListEventsItem>): List<EventEntity> {
        val eventList = ArrayList<EventEntity>()
        input.map {
            val event = EventEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                mediaCover = it.mediaCover,
                link = it.link,
                quota = it.quota,
                beginTime = it.beginTime,
                registrants = it.registrants,
                isFavorite = false
            )
            eventList.add(event)
        }
        return eventList
    }

    fun mapEntitiesToDomain(input: List<EventEntity>): List<Event> =
        input.map {
            Event(
                id = it.id,
                name = it.name,
                description = it.description,
                mediaCover = it.mediaCover,
                link = it.link,
                quota = it.quota,
                beginTime = it.beginTime,
                registrants = it.registrants,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Event) = EventEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        mediaCover = input.mediaCover,
        link = input.link,
        quota = input.quota,
        beginTime = input.beginTime,
        registrants = input.registrants,
        isFavorite = input.isFavorite
    )
}