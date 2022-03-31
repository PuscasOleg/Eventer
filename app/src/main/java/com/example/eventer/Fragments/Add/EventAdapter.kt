package com.example.eventer.Fragments.Add

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.R
import kotlinx.android.synthetic.main.item_event_layout.view.*

class EventAdapter:RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    class EventViewHolder(view: View):RecyclerView.ViewHolder(view)

    private  var eventList= emptyList<RegisterEvent>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_event_layout,parent,false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.itemView.theme_item.text=eventList[position].theme
        holder.itemView.date_item.text=eventList[position].date.toString()
        holder.itemView.phone_item.text=eventList[position].phone


        holder.itemView.setOnClickListener {  }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<RegisterEvent>){
        eventList=list
        notifyDataSetChanged()
    }
}