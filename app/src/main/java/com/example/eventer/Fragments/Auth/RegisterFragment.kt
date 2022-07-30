package com.example.eventer.Fragments.Auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eventer.NavInterface
import com.example.eventer.Presenter.authPresenter.AuthPresenterImp
import com.example.eventer.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(), NavInterface {
    private lateinit var signInTextView: TextView

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var email: EditText
    private lateinit var singUpBtn: Button


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val authPresenter = AuthPresenterImp()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singUpBtn = view.findViewById(R.id.signUpButton)
        signInTextView = view.findViewById(R.id.sigInTextView)


        signInTextView.setOnClickListener {
            replaceFragment(LoginFragment())
        }


        singUpBtn.setOnClickListener {
            createUser()
            /*authPresenter.registration(
                userName = userNameEditeText.text.toString(),
                password = passwordEditText.text.toString(),
                email = emailEditText.text.toString(),
                confPassword = confirmPasswordEditeText.text.toString()
            )*/

        }


    }


    private fun createUser() {

        userName = view?.findViewById(R.id.userNameEditeText)!!
        val UserName: String = userName.text.toString().trim()
        password = view?.findViewById(R.id.passwordEditText)!!
        val Password: String = password.text.toString().trim()
        confirmPassword = view?.findViewById(R.id.confirmPasswordEditeText)!!
        val ConfirmPassword: String = confirmPassword.text.toString().trim()
        email = view?.findViewById(R.id.emailEditText)!!
        val Email = email.text.toString().trim()




        if (UserName.isEmpty()) {
            userName.error = "Please Enter Username"
            userName.requestFocus()
            return

        }
        if (Password.isEmpty()) {
            password.error = "Please Enter Password"
            password.requestFocus()
        } else if (password.toString().length < 8) {
            password.error = "Password must be more than 8 numbers"
            return
        }


        if (ConfirmPassword.isEmpty()) {
            confirmPassword.error = "Please Confirm Password"
            confirmPassword.requestFocus()
        } else if (ConfirmPassword != Password) {
            confirmPassword.error = "The Password doesn't match"
            return
        }

        if (Email.isEmpty()) {
            email.error = "Please Enter Email"
            email.requestFocus()
            return
        }


        auth.fetchSignInMethodsForEmail(Email).addOnCompleteListener {
            if (it.result?.signInMethods?.isEmpty() == false) {
                email.error = "Email already exist"
                email.requestFocus()
            }
        }


        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener { it ->
            if (it.isSuccessful) {

                val user = UserClass(UserName, Email)

                FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.currentUser
                            Toast.makeText(activity, "Created", Toast.LENGTH_LONG).show()
                            replaceFragment(LoginFragment())

                        } else {
                            Toast.makeText(activity, "Failure", Toast.LENGTH_LONG).show()

                        }

                    }
            } else {
                Toast.makeText(activity, "Failure", Toast.LENGTH_LONG).show()
            }


        }
    }


    override fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
