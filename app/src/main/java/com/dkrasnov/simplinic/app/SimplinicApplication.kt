package com.dkrasnov.simplinic.app

import android.app.Application
import com.dkrasnov.simplinic.dagger.ComponentHolder

class SimplinicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentHolder.initApplicationComponents(this)
    }
}