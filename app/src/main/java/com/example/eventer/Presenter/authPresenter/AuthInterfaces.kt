package com.example.eventer.Presenter

import android.content.Context

interface AuthPresenter{
    fun  registration(userName:String,email:String,password:String,confPassword:String)


}

interface AuthView{
    fun showSuccess(message:String)
    fun showError(message: String)

}