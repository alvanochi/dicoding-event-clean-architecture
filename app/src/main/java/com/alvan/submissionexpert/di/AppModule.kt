package com.alvan.submissionexpert.di

import com.alvan.submissionexpert.ui.main.MainViewModel
import com.alvan.submissionexpert.core.domain.usecase.EventInteractor
import com.alvan.submissionexpert.core.domain.usecase.EventUseCase
import com.alvan.submissionexpert.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EventUseCase> { EventInteractor(get()) }
}


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}