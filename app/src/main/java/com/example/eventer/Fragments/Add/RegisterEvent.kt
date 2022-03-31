package com.example.eventer.Fragments.Add

import android.accounts.AuthenticatorDescription
import java.util.*

class RegisterEvent {

    var theme: String = ""
    var description: String = ""
    var phone: String? = null
    var date:String? = null


    constructor()

    constructor(theme: String = "", date: String, phone: String?) {
        this.theme = theme
        this.phone = phone
        this.date = date
    }


    constructor(description: String=""){
        this.description=description
    }


}