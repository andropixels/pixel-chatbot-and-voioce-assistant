package com.rahul.pixelschatbot.utils

import android.app.Service
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object time {
    
    
    fun timestamp():String{
        
        val times= Timestamp(System.currentTimeMillis())
        val sdf=SimpleDateFormat("HH:mm")
        val  finnaltime=sdf.format(Date(times.time))
        return finnaltime.toString()
    }
}