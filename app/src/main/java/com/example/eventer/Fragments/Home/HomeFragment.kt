package com.example.eventer.Fragments.Home

import android.annotation.SuppressLint
import android.widget.SearchView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.Fragments.Add.EventAdapter
import com.example.eventer.Fragments.Add.EventClass
import com.example.eventer.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query


class HomeFragment : Fragment() {


    private lateinit var adapter: EventAdapter

    private lateinit var searchEventView: SearchView
    private lateinit var resView: RecyclerView
    var query: Query = FirebaseDatabase.getInstance().getReference("Events")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resView = view.findViewById(R.id.recycleView)
        searchEventView=view.findViewById(R.id.Search)

        searchEventView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {

                    findEvent(query)

                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    findEvent(query)
                }
                return false
            }

        })


        val options: FirebaseRecyclerOptions<EventClass> =
            FirebaseRecyclerOptions.Builder<EventClass>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Events"),
                    EventClass::class.java
                )
                .build()

        adapter = EventAdapter(options)
        resView.adapter = adapter

    }


    @SuppressLint("NotifyDataSetChanged")
    fun findEvent(str: String) {
        val options: FirebaseRecyclerOptions<EventClass> =
            FirebaseRecyclerOptions.Builder<EventClass>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Events").orderByChild("theme")
                        .startAt(str.uppercase()).endAt(str.lowercase() + "\uf8ff")//Узнать!!
                    ,
                    EventClass::class.java
                )
                .build()

        adapter = EventAdapter(options)
        adapter.notifyDataSetChanged()
        adapter.startListening()
        resView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}
