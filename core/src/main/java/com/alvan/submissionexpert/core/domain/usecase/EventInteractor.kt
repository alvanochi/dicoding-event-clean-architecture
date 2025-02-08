package com.alvan.submissionexpert.core.domain.usecase

import com.alvan.submissionexpert.core.domain.model.Event
import com.alvan.submissionexpert.core.domain.repository.IEventRepository

class EventInteractor(private val eventRepository: IEventRepository): EventUseCase {

    override fun getAllEvent() = eventRepository.getAllEvent()

    override fun getFavoriteEvent() = eventRepository.getFavoriteEvent()

    override fun setFavoriteEvent(event: Event, state: Boolean) = eventRepository.setFavoriteEvent(event, state)
}