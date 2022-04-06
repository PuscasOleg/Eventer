package com.example.eventer.Fragments.Auth


class RegisterUser {
    var ProfileImage: String? = null
    var userName: String? = null
    var email: String? = null




    constructor()


    constructor(userName: String?, email: String?) {
        this.userName = userName
        this.email = email
    }
}

