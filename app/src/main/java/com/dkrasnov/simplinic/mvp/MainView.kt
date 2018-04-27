package com.dkrasnov.simplinic.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dkrasnov.simplinic.data.BigData
import com.dkrasnov.simplinic.data.RedData

/**
 * Created by Dmitriy on 27.04.2018.
 */
interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgress(progress: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setState(redState: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showRedData(data: List<RedData>?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showBigData(data: List<BigData>?)
}