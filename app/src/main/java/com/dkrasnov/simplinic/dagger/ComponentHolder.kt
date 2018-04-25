package com.dkrasnov.simplinic.dagger

import com.dkrasnov.simplinic.app.SimplinicApplication

class ComponentHolder {

    companion object {
        private val applicationComponents: ApplicationComponents by lazy {
            Dagger
        }

        fun applicationComponents(application: SimplinicApplication): ApplicationComponents {
            if (applicationComponents == null) {

            }

            return applicationComponents
        }
    }
}