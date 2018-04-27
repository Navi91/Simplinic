package com.dkrasnov.simplinic.dagger

import com.dkrasnov.simplinic.mvp.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, SystemModule::class, SimplinicModule::class))
interface ApplicationComponent {

    fun inject(mvpPresenter: MainPresenter)
}