package com.alvan.submissionexpert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alvan.submissionexpert.core.domain.usecase.EventUseCase

class FavoriteViewModel(eventUseCase: EventUseCase) : ViewModel() {

    val favoriteEvent = eventUseCase.getFavoriteEvent().asLiveData()

}