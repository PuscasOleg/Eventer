package com.example.eventer.Presenter.authPresenter



import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eventer.Fragments.Auth.LoginFragment
import com.example.eventer.Fragments.Auth.UserClass
import com.example.eventer.MAIN
import com.example.eventer.NavInterface
import com.example.eventer.Presenter.AuthPresenter
import com.example.eventer.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


open class AuthPresenterImp : AuthPresenter, NavInterface {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun registration(
        userName: String,
        email: String,
        password: String,
        confPassword: String
    ) {

        if (!checkEmailValidation(email)) {
            Toast.makeText(MAIN, "Invalid Email", Toast.LENGTH_SHORT).show()
            return
        }

        if (!checkPasswordValidation(password)) {
            Toast.makeText(MAIN, "Invalid Password", Toast.LENGTH_SHORT).show()
            return
        }


        if (!isUserNameEntered(userName)) {
            Toast.makeText(MAIN, "Enter UserName", Toast.LENGTH_SHORT).show()
            return

        }
        if (!isCheckPasswordEntered(confPassword, password)) {

            Toast.makeText(MAIN, "Confirm Password", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
            if (it.isSuccessful) {

                val user = UserClass(userName = userName, email = email)

                FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.currentUser
                            Toast.makeText(MAIN, "Success", Toast.LENGTH_LONG).show()
                            replaceFragment(LoginFragment())
                        } else {
                            Toast.makeText(MAIN, "Failure", Toast.LENGTH_LONG).show()
                            //Error message
                        }

                    }
            } else {
                //Error message
                Toast.makeText(MAIN, "Failure", Toast.LENGTH_LONG).show()
            }

        }


        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            if (it.result?.signInMethods?.isEmpty() == false) {
                Toast.makeText(MAIN, "Email already exist", Toast.LENGTH_LONG).show()

            }
        }
    }


    private fun checkEmailValidation(email: String): Boolean {
        return email.isNotEmpty() && email.contains("@") && email.contains(".")


    }


    private fun checkPasswordValidation(password: String): Boolean {
        return password.length > 8 || password.isNotEmpty()
    }


    private fun isUserNameEntered(userName: String): Boolean {
        return userName.isNotEmpty()

    }

    private fun isCheckPasswordEntered(confPassword: String, password: String): Boolean {
        return confPassword.isNotEmpty() || confPassword == password

    }

    override fun replaceFragment(fragment: Fragment) {
        MAIN.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .commit()
    }


}

