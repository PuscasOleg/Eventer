package com.example.eventer.Fragments.Add


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.example.eventer.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AddEventFragment : Fragment() {


    private lateinit var themeView: TextInputEditText
    private lateinit var descriptionView: TextInputEditText
    private lateinit var phoneNumberView: TextInputEditText
    private lateinit var locationView: TextInputEditText
    private lateinit var addEvent: Button
    private lateinit var dateEditText: TextInputEditText



    private val user = FirebaseAuth.getInstance().currentUser
    private val userId = Firebase.auth.uid
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)

    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        themeView = view.findViewById(R.id.theEditText)
        dateEditText = view.findViewById(R.id.dateEdit)
        descriptionView = view.findViewById(R.id.descriptionEditText)
        phoneNumberView = view.findViewById(R.id.phone_numberEditText)
        locationView = view.findViewById(R.id.locationEdit)
        addEvent = view.findViewById(R.id.addEventBtn)



        addEvent.setOnClickListener {
            addEvent()
        }



        dateEditText.datePicker(requireActivity().supportFragmentManager)





    }


    private fun addEvent() {
        val themeViewString: String = themeView.text.toString()
        val dateEditTextString: String = dateEditText.text.toString()
        val descriptionViewString: String = descriptionView.text.toString()
        val phoneNumberViewString: String = phoneNumberView.text.toString()
        val locationViewString: String = locationView.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Events")

        val event = EventClass(
            user!!.email,
            userId,
            themeViewString,
            descriptionViewString,
            locationViewString,
            dateEditTextString,
            phoneNumberViewString
        )
        database.child(themeViewString).setValue(event).addOnSuccessListener {
            themeView.text?.clear()
            dateEditText.text?.clear()
            descriptionView.text?.clear()
            phoneNumberView.text?.clear()
            locationView.text?.clear()
            Toast.makeText(activity, "Successful", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(activity, "Failure", Toast.LENGTH_LONG).show()
        }
    }


    private fun TextInputEditText.datePicker(fm: FragmentManager) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build()


        setOnClickListener {

            datePicker.show(fm, "tag")
        }

        datePicker.addOnPositiveButtonClickListener {
            setText(datePicker.headerText)

        }
    }




}