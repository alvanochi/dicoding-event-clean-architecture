package com.alvan.submissionexpert

import android.app.Application
import com.alvan.submissionexpert.core.di.databaseModule
import com.alvan.submissionexpert.core.di.networkModule
import com.alvan.submissionexpert.core.di.repositoryModule
import com.alvan.submissionexpert.di.useCaseModule
import com.alvan.submissionexpert.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}