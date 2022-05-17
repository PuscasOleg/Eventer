package com.example.eventer.Fragments.Add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.Fragments.Home.EventDetailFragment
import com.example.eventer.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import kotlinx.android.synthetic.main.item_event_layout.view.*

class EventAdapter(options: FirebaseRecyclerOptions<EventClass>) :
    FirebaseRecyclerAdapter<EventClass, EventAdapter.EventViewHolder>(options) {


    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_layout, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int, model: EventClass) {


        holder.itemView.setOnClickListener {

            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                EventDetailFragment(
                    model.theme,
                    model.description,
                    model.userId,
                    model.userEmail,
                    model.date,
                    model.phone,
                    model.location
                )
            ).addToBackStack(null).commit()

        }
        holder.itemView.theme_item.text = model.theme
        holder.itemView.date_item.text = model.date
        holder.itemView.phone_item.text = model.phone

    }


    override fun getItemCount(): Int {
        return super.getItemCount()
    }


}