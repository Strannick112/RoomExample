package com.example.myapplication

import android.content.Context
import androidx.room.Room

object Dependencies {
    private lateinit var appContext: Context

    fun init(context: Context){
        appContext = context
    }

    private val appDatabase: Database by lazy{
        Room.databaseBuilder(appContext, Database::class.java, "database.db")
            .createFromAsset("room_article.db")
            .build()
    }
}