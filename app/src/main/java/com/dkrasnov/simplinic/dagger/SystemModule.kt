package com.dkrasnov.simplinic.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Dmitriy on 27.04.2018.
 */
@Module
class SystemModule {

    @Provides
    fun provideSharedPreferences(context: Context) =
            context.getSharedPreferences("simplinic_shred_preferences", Context.MODE_PRIVATE)
}