package com.example.eventer.Fragments.Add

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import kotlinx.android.synthetic.main.item_event_layout.view.*

class EventAdapter(options: FirebaseRecyclerOptions<RegisterEvent>) :
    FirebaseRecyclerAdapter<RegisterEvent, EventAdapter.EventViewHolder>(options) {


    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_layout, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int, model: RegisterEvent) {

        holder.itemView.theme_item.text = model.theme
        holder.itemView.date_item.text =  model.date
        holder.itemView.phone_item.text = model.phone

    }


    /* private  var eventList= emptyList<RegisterEvent>()


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
         val view=LayoutInflater.from(parent.context).inflate(R.layout.item_event_layout,parent,false)
         return EventViewHolder(view)
     }

     override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
         holder.itemView.theme_item.text=eventList[position].theme
         holder.itemView.date_item.text=eventList[position].date.toString()
         holder.itemView.phone_item.text=eventList[position].phone



     }

     override fun getItemCount(): Int {
         return eventList.size
     }

     @SuppressLint("NotifyDataSetChanged")
     fun setList(list:List<RegisterEvent>){
         eventList=list
         notifyDataSetChanged()
     }*/

}