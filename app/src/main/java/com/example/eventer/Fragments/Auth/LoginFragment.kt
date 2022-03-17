package com.example.eventer.Fragments.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.eventer.Fragments.Add.AddFragment
import com.example.eventer.R
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {


    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var sigUpTextView: TextView
    private lateinit var logInBtn: Button
    private lateinit var forgotPassword: TextView


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logInBtn = view.findViewById(R.id.LogInBtn)
        sigUpTextView = view.findViewById(R.id.signUpTextView)
        forgotPassword = view.findViewById(R.id.forgotPasswordTextView)


        forgotPassword.setOnClickListener { openResetPasswordFragment() }

        sigUpTextView.setOnClickListener { openSignUpFragment() }

        logInBtn.setOnClickListener { singInUser() }


    }

    private fun openSignUpFragment() {
        replaceFragment(RegisterFragment())
    }

    private fun openResetPasswordFragment() {
        replaceFragment(ResetPasswordFragment())
    }


    private fun singInUser() {


        password = view?.findViewById(R.id.passwordSignUp)!!
        val Password: String = password.text.toString().trim()
        email = view?.findViewById(R.id.emailSignUP)!!
        val Email = email.text.toString().trim()


        when {
            Password.isEmpty() -> {
                password.error = "Please Enter Password"
                password.requestFocus()
                return
            }
            Email.isEmpty() -> {
                email.error = "Please Enter Email"
                email.requestFocus()
                return

            }
            else -> {
                registerUser(Email, Password)
            }
        }


    }


    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment).commit()

    }


    private fun registerUser(Email: String, Password: String) {
        auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener {
            if (it.isSuccessful) {


                //connect to  GSON

                auth.currentUser
                Toast.makeText(activity, "Welcome", Toast.LENGTH_LONG).show()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, AddFragment())
                    ?.addToBackStack(null)?.commit()

            } else {
                Toast.makeText(activity, "Failure", Toast.LENGTH_LONG).show()
            }

        }

    }


}
