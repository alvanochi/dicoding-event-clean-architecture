package com.alvan.submissionexpert.detail

import androidx.lifecycle.ViewModel
import com.alvan.submissionexpert.core.domain.model.Event
import com.alvan.submissionexpert.core.domain.usecase.EventUseCase

class DetailViewModel(private val eventUseCase: EventUseCase) : ViewModel() {
    fun setFavoriteEvent(event: Event, newStatus: Boolean) = eventUseCase.setFavoriteEvent(event, newStatus)
}