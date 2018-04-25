package com.dkrasnov.simplinic.dagger

import com.dkrasnov.simplinic.app.SimplinicApplication

class ComponentHolder {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun initApplicationComponents(application: SimplinicApplication) {
            applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(application)).build()
        }

        fun applicationComponent() = applicationComponent
    }
}