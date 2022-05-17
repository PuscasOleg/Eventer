package com.example.eventer.Fragments.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.eventer.R


class EventDetailFragment() : Fragment() {

    var theme: String? = null
    var description: String? = null
    var userId: String? = null
    var userEmail: String? = null
    var date: String? = null
    var phone: String? = null
    var location: String? = null


    constructor(
        theme: String?,
        description: String?,
        userId: String?,
        userEmail: String?,
        date: String?,
        phone: String?,
        location: String?
    ) : this() {
        this.theme = theme
        this.description = description
        this.userId = userId
        this.userEmail = userEmail
        this.date=date
        this.phone=phone
        this.location=location

    }


    private lateinit var themeEvent: TextView
    lateinit var detailEvent: TextView
    lateinit var authorEvent: TextView
    lateinit var phoneEvent: TextView
    lateinit var locationEvent: TextView
    lateinit var dateEvent: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeEvent = view.findViewById(R.id.detailTheme)
        detailEvent = view.findViewById(R.id.detailDescription)
        authorEvent = view.findViewById(R.id.detailAuthor)
        phoneEvent = view.findViewById(R.id.phone_detail)
        locationEvent = view.findViewById(R.id.location_detail)
        dateEvent = view.findViewById(R.id.date_detail)

        themeEvent.text = theme
        detailEvent.text = description
        authorEvent.text = userEmail
        phoneEvent.text=phone
        locationEvent.text=location
        dateEvent.text=date


    }


}
