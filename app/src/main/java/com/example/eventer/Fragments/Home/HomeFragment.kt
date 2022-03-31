package com.example.eventer.Fragments.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.eventer.Fragments.Add.EventAdapter
import com.example.eventer.Fragments.Add.RegisterEvent

import com.example.eventer.R


class HomeFragment : Fragment() {


    lateinit var adapter: EventAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var resView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resView= view.findViewById(R.id.recycleView)

        initial()




    }

    private fun initial() {



        recyclerView=resView
        adapter= EventAdapter()

        recyclerView.adapter=adapter
        adapter.setList(event().asReversed())

    }


    private fun event():ArrayList<RegisterEvent>{

        val eventList=ArrayList<RegisterEvent>()

        var event1=RegisterEvent(" Endava Internship","12.03.2022","+37378373234")
        eventList.add(event1)
        var event2=RegisterEvent(" Pentalog Internship","23.03.2022","+37368324242")
        eventList.add(event2)
        var event3=RegisterEvent(" Amdaris Internship","12.03.2022","+37369873223")
        eventList.add(event3)
        var event4=RegisterEvent(" Unifan Internship","12.03.2022","+37369873223")
        eventList.add(event4)
        var event5=RegisterEvent("Tennis Tournament","30.03.2022","+37368860464")
        eventList.add(event5)
        var event6=RegisterEvent(" Concert","09.03.2022","+37368860464")
        eventList.add(event6)
        var event7=RegisterEvent(" Concert Rammstein","023.04.2022","+37368860464")
        eventList.add(event7)

        return eventList

    }




}