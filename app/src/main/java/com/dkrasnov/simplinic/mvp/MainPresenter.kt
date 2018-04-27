package com.dkrasnov.simplinic.mvp

import android.arch.lifecycle.*
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dkrasnov.simplinic.api.DataRepository
import com.dkrasnov.simplinic.dagger.ComponentHolder
import com.dkrasnov.simplinic.data.BigData
import com.dkrasnov.simplinic.data.RedData
import com.dkrasnov.simplinic.data.livedata.LiveResource
import com.dkrasnov.simplinic.data.livedata.Status
import javax.inject.Inject

/**
 * Created by Dmitriy on 27.04.2018.
 */
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), LifecycleOwner {

    @Inject
    lateinit var dataRepository: DataRepository

    private val lifecycleRegistry = LifecycleRegistry(this)
    private var redState = true
    private var redLiveData: LiveData<LiveResource<List<RedData>>>? = null
    private var bigLiveData: LiveData<LiveResource<List<BigData>>>? = null

    init {
        ComponentHolder.applicationComponent().inject(this)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun requestData() {
        updateState(redState)
    }

    fun switch() {
        if (redState) {
            updateState(false)
        } else {
            updateState(true)
        }
    }

    fun refresh() {
        dataRepository.clearCache()
        redLiveData?.removeObservers(this)
        bigLiveData?.removeObservers(this)

        redLiveData = null
        bigLiveData = null

        updateState(redState)
    }

    private fun observeRedData() {
        bigLiveData?.removeObservers(this)

        if (redLiveData == null) redLiveData = dataRepository.requestRedDataLiveData()

        redLiveData?.observe(this, Observer<LiveResource<List<RedData>>> {
            when (it?.status) {
                Status.SUCCESS -> {
                    setViewProgress(false)
                    viewState.showRedData(it.data)
                }
                Status.ERROR -> {
                    setViewProgress(false)
                    showViewError(it.error)
                }
                Status.LOADING -> setViewProgress(true)
            }
        })
    }

    private fun observeBigData() {
        redLiveData?.removeObservers(this)

        if (bigLiveData == null) bigLiveData = dataRepository.requestBigDataLiveData()

        bigLiveData?.observe(this, Observer<LiveResource<List<BigData>>> {
            when (it?.status) {
                Status.SUCCESS -> {
                    setViewProgress(false)
                    viewState.showBigData(it.data)
                }
                Status.ERROR -> {
                    setViewProgress(false)
                    showViewError(it.error)
                }
                Status.LOADING -> setViewProgress(true)
            }
        })
    }

    private fun updateState(redState: Boolean) {
        this.redState = redState
        viewState.setState(redState)

        if (redState) {
            observeRedData()
        } else {
            observeBigData()
        }
    }

    private fun setViewProgress(progress: Boolean) {
        viewState.setProgress(progress)
    }

    private fun showViewError(e: Throwable?) {
        viewState.showError(e?.message ?: "Unknown message")
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}