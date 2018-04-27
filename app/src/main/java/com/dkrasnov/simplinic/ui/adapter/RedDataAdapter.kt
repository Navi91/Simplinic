package com.dkrasnov.simplinic.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dkrasnov.simplinic.R
import com.dkrasnov.simplinic.data.RedData
import kotlinx.android.synthetic.main.v_red_data_item.view.*

/**
 * Created by Dmitriy on 28.04.2018.
 */
class RedDataAdapter : BaseListAdapter<RedData, RedDataAdapter.RedDataItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedDataItemViewHolder {
        return RedDataItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.v_red_data_item, parent, false))
    }

    override fun onBindViewHolder(holder: RedDataItemViewHolder, position: Int) {
        holder.title.text = getItem(position)?.value
    }

    class RedDataItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.title
    }
}