package com.example.eventer


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.eventer.Fragments.Add.AddFragment
import com.example.eventer.Fragments.Auth.LoginFragment
import com.example.eventer.Fragments.Auth.RegisterFragment
import com.example.eventer.Fragments.Home.HomeFragment
import com.example.eventer.Fragments.Profile.AccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import leakcanary.LeakCanary


class MainActivity : AppCompatActivity() {


    private val accountFragment = AccountFragment()
    private val addFragment = AddFragment()
    private val homeFragment = HomeFragment()
    private val registerFragment = RegisterFragment()
    private val loginFragment = LoginFragment()

    private lateinit var bottomNavigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /* if(savedInstanceState==null){
             supportFragmentManager.beginTransaction()
                 .add(R.id.fragmentContainer,registerFragment)
         }*/




        bottomNavigation = findViewById(R.id.BottomNavigation)




        replaceFragment(homeFragment)

       bottomNavigation.setOnItemSelectedListener {
           when(it.itemId){
               R.id.homeBtn->{replaceFragment(homeFragment)}
               R.id.addBtn->{replaceFragment(addFragment)}
               R.id.accountBtn->{replaceFragment(accountFragment)}
           }
           true
       }



    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }


    override fun onDestroy() {
        super.onDestroy()


    }
}










