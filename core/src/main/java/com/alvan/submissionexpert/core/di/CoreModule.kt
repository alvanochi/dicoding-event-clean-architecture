package com.alvan.submissionexpert.core.di

import androidx.room.Room
import com.alvan.submissionexpert.core.data.EventRepository
import com.alvan.submissionexpert.core.data.local.LocalDataSource
import com.alvan.submissionexpert.core.data.local.room.EventDatabase
import com.alvan.submissionexpert.core.data.remote.RemoteDataSource
import com.alvan.submissionexpert.core.data.remote.network.ApiService
import com.alvan.submissionexpert.core.domain.repository.IEventRepository
import com.alvan.submissionexpert.core.utils.AppExecutors
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "event-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/IP3deCdJNWm0Ae27av8Odv7gpd7Z1jL6dKVGnJDOpDM=")
            .add(hostname, "sha256/K7rZOrXHknnsEhUH8nLL4MZkejquUuIvOIr6tCa0rbo=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<EventDatabase>().eventDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EventDatabase::class.java, "Event.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IEventRepository> {
        EventRepository(
            get(),
            get(),
            get()
        )
    }
}