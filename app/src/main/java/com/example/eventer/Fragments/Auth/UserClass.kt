package com.example.eventer.Fragments.Auth


 data class UserClass(var userName: String?="", var email: String?="") {

    var ProfileImage:String?=null

    constructor(profileImage:String,userName: String, email: String) : this(userName,email) {
        this.ProfileImage=profileImage

    }
}

