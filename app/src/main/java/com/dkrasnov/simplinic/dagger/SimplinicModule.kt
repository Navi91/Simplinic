package com.dkrasnov.simplinic.dagger

import android.content.SharedPreferences
import com.dkrasnov.simplinic.api.DataRepository
import com.dkrasnov.simplinic.api.Preferences
import com.dkrasnov.simplinic.api.RetrofitMock
import com.dkrasnov.simplinic.api.SimplinicApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dmitriy on 27.04.2018.
 */
@Module
class SimplinicModule {

    @Singleton
    @Provides
    fun providePreferences(sharedPreferences: SharedPreferences) = Preferences(sharedPreferences)

    @Singleton
    @Provides
    fun provideRetrofitMock() = RetrofitMock()

    @Provides
    fun provideSimplinicApi(retrofitMock: RetrofitMock) = SimplinicApi(retrofitMock)

    @Singleton
    @Provides
    fun provideDataRepository(preferences: Preferences, simplinicApi: SimplinicApi) =
            DataRepository(preferences, simplinicApi)
}