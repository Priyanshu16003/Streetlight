package com.bhaskar.streetlight.test1.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhaskar.streetlight.R

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_layout, parent, false)
        return HomeViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

}

class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val textView = view.findViewById<TextView>(R.id.list_item)

}