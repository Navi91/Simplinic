package com.dkrasnov.simplinic.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dkrasnov.simplinic.R
import com.dkrasnov.simplinic.data.BigData
import com.dkrasnov.simplinic.data.RedData
import com.dkrasnov.simplinic.mvp.MainPresenter
import com.dkrasnov.simplinic.mvp.MainView
import com.dkrasnov.simplinic.ui.adapter.BigDataAdapter
import com.dkrasnov.simplinic.ui.adapter.RedDataAdapter
import kotlinx.android.synthetic.main.f_main.view.*

/**
 * Created by Dmitriy on 27.04.2018.
 */
class MainFragment : MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressView: View

    private lateinit var redDataAdapter: RedDataAdapter
    private lateinit var bigDataAdapter: BigDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.f_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redDataAdapter = RedDataAdapter()
        bigDataAdapter = BigDataAdapter()

        progressView = view.progress_view
        recyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainPresenter.requestData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.switch_state -> {
                mainPresenter.switch()
                return true
            }
            R.id.refresh -> {
                mainPresenter.refresh()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setProgress(progress: Boolean) {
        progressView.visibility = if (progress) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showError(message: String) {
    }

    override fun setState(redState: Boolean) {
        val title = if (redState) {
            "Red data"
        } else {
            "Big data"
        }

        (activity as MvpAppCompatActivity).supportActionBar?.title = title
    }

    override fun showRedData(data: List<RedData>?) {
        redDataAdapter.items = data
        recyclerView.adapter = redDataAdapter
    }

    override fun showBigData(data: List<BigData>?) {
        bigDataAdapter.items = data
        recyclerView.adapter = bigDataAdapter
    }
}