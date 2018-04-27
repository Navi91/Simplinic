package com.dkrasnov.simplinic.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Dmitriy on 28.04.2018.
 */
abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var items: List<T>? = null

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun getItem(position: Int) = items?.getOrNull(position)

    abstract override fun onBindViewHolder(holder: VH, position: Int)
}