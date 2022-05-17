package com.example.eventer.Fragments.Auth


class UserClass() {
    var ProfileImage: String? = null
    var userName: String? = null
    var email: String? = null



    constructor(userName: String?, email: String?) : this() {
        this.userName = userName
        this.email = email
    }

}

