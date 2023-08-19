package com.example.platz_api_login_rejister.Utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManeger @Inject constructor(@ApplicationContext  context:Context) {

    private val  pref=context.getSharedPreferences("pref",Context.MODE_PRIVATE)


    fun setPref(kay:String,value:String){

        var prefEidt=pref.edit()
        prefEidt.putString(kay,value)
        prefEidt.apply()
    }

    fun getPref(kay:String):String{

        return pref.getString(kay,"").toString()
    }

}