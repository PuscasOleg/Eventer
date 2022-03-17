package com.example.eventer.Fragments.Add

class RegisterEvent {

    private var theme: String = ""
    private var description: String = ""
    private var phone: String? = null


    constructor()

    constructor(theme: String = "", description: String = "", phone: String? = null) {
        this.theme = theme
        this.description = description
        this.phone = phone
    }


}