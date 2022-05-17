package com.example.eventer


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.eventer.Fragments.Add.AddEventFragment
import com.example.eventer.Fragments.Auth.LoginFragment
import com.example.eventer.Fragments.Home.HomeFragment
import com.example.eventer.Fragments.Profile.AccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {


    private val user = FirebaseAuth.getInstance().currentUser

    private val accountFragment = AccountFragment()
    private val addFragment = AddEventFragment()
    private val homeFragment = HomeFragment()
    private val loginFragment = LoginFragment()

    private lateinit var bottomNavigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.BottomNavigation)

        replaceFragment(homeFragment)

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeBtn -> {
                    replaceFragment(homeFragment)
                }
                R.id.addBtn -> {
                    if (user == null) {
                        replaceFragment(loginFragment)
                    } else {
                        replaceFragment(addFragment)
                    }
                }
                R.id.accountBtn -> {
                    replaceFragment(accountFragment)
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }


}










