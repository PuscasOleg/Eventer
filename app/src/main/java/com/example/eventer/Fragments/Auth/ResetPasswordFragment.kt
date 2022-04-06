package com.example.eventer.Fragments.Auth

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.eventer.R
import com.google.firebase.auth.FirebaseAuth


class ResetPasswordFragment : Fragment() {
    private lateinit var resetEmailPassword: EditText
    private lateinit var resetBtn: Button
    private lateinit var backBtn: TextView

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetEmailPassword = view.findViewById(R.id.emailReset)
        backBtn = view.findViewById(R.id.backToLoginBtn)
        resetBtn = view.findViewById(R.id.resetBtn)


        backBtn.setOnClickListener { back() }


        resetBtn.setOnClickListener { resetPassword() }


    }


    private fun resetPassword() {


        val ResetPassword = resetEmailPassword.text.toString().trim()

        if (ResetPassword.isEmpty()) {
            resetEmailPassword.error = "Please Enter Email"
            resetEmailPassword.requestFocus()
            return
        }
        auth.sendPasswordResetEmail(ResetPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                AlertDialog.Builder(activity).setCancelable(true).setMessage("Successful")
                    .create().show()

            } else {
                AlertDialog.Builder(activity).setCancelable(true).setMessage("Fail")
                    .create().show()

            }
        }


    }

    private fun back() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .addToBackStack(null).commit()
    }

}