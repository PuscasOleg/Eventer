package com.example.eventer.Fragments.Add

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import com.example.eventer.Fragments.Auth.LoginFragment
import com.example.eventer.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add.*
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {


    private lateinit var themeView: TextInputEditText
    private lateinit var descriptionView: TextInputEditText
    private lateinit var phoneNumberView: TextInputEditText
    private lateinit var locationView: TextInputEditText
    private lateinit var addEvent: Button

    private lateinit var dateEditText: TextInputEditText
    private val user = FirebaseAuth.getInstance().currentUser

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








        dateEditText.datePicker(requireActivity().supportFragmentManager, "tag")


        /*dateEditText.setOnClickListener {
            View.OnClickListener {
                val getDate = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    DatePickerDialog.OnDateSetListener { datePicker, day, month, year ->


                        val selectDate = Calendar.getInstance()
                        selectDate.set(Calendar.DAY_OF_MONTH, day)
                        selectDate.set(Calendar.MONTH, month)
                        selectDate.set(Calendar.YEAR, year)

                        val date = dateFormat.format(selectDate.time)

                        dateView.editText?.setText(date)
                    },
                    getDate.get(Calendar.YEAR),
                    getDate.get(Calendar.MONTH),
                    getDate.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }*/


    }


    private fun addEvent() {
        val themeViewString: String = themeView.text.toString()

        val dateEditTextString: String = dateEditText.text.toString()
        val descriptionViewString: String = descriptionView.text.toString()
        val phoneNumberViewString: String = phoneNumberView.text.toString()

        val locationViewString: String = locationView.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Events")

        val event = RegisterEvent(
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


    private fun TextInputEditText.datePicker(fm: FragmentManager, tag: String) {

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


    override fun onStart() {
        super.onStart()
        // checkUser()
    }


    /*   private fun checkUser() {
           if (user == null) {
               Toast.makeText(activity, "Only logged users can add events", Toast.LENGTH_LONG).show()
               parentFragmentManager.beginTransaction()
                   .replace(R.id.fragmentContainer, LoginFragment())
                   .addToBackStack(null)
                   .commit()
           }
       }*/


}