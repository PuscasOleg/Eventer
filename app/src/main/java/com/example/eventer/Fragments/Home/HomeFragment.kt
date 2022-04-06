package com.example.eventer.Fragments.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.Fragments.Add.EventAdapter
import com.example.eventer.Fragments.Add.RegisterEvent
import com.example.eventer.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : Fragment() {


    lateinit var adapter: EventAdapter


    lateinit var resView: RecyclerView

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

        //initial()

        val options: FirebaseRecyclerOptions<RegisterEvent> =
            FirebaseRecyclerOptions.Builder<RegisterEvent>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Events"),
                    RegisterEvent::class.java
                )
                .build()

        adapter = EventAdapter(options)
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