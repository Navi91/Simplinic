package com.dkrasnov.simplinic.dagger

import android.content.Context
import com.dkrasnov.simplinic.app.SimplinicApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: SimplinicApplication) {

    @Provides
    fun provideApplication(): SimplinicApplication = application

    @Provides
    fun provideContext(): Context = application
}