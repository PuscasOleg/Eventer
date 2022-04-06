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


    constructor(
        theme: String?,
        description: String?,
        userId: String?,
        userEmail: String?
    ) : this() {
        this.theme = theme
        this.description = description
        this.userId = userId
        this.userEmail = userEmail

    }


    lateinit var themeEvent: TextView
    lateinit var detailEvent: TextView
    lateinit var authorEvent: TextView

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

        themeEvent.text = theme
        detailEvent.text = description
        authorEvent.text = userEmail


    }


}
