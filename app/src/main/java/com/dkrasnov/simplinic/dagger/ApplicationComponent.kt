package com.dkrasnov.simplinic.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
}