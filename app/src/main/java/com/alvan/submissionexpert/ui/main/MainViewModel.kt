package com.alvan.submissionexpert.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alvan.submissionexpert.core.domain.usecase.EventUseCase

class MainViewModel(eventUseCase: EventUseCase) : ViewModel() {
    val event = eventUseCase.getAllEvent().asLiveData()
}
