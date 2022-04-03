package com.example.eventer.Fragments.Add


class RegisterEvent {

    var theme: String? = null
     var description: String? = null
     var location:String? = null
    var date:String? = null
    var phone: String? = null



    constructor()

    constructor(theme: String?,description: String?,location: String?, date: String?,phone:String?) {
        this.theme = theme
        this.description=description
        this.location=location
        this.date = date
        this.phone = phone


    }

    constructor(theme: String?, date: String?, phone: String?) {
        this.theme = theme
        this.date = date
        this.phone = phone
    }



}