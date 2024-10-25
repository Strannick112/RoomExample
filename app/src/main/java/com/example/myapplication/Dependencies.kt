package com.example.myapplication

import android.content.Context
import androidx.room.Room

object Dependencies {
    private lateinit var appContext: Context

    fun init(context: Context){
        appContext = context
    }

    val appDatabase: Database by lazy{
        Room.databaseBuilder(appContext, Database::class.java, "database.db")
            .build()
    }
}
