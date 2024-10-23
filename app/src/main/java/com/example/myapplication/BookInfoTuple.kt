package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class BookInfoTuple (
    val id: Int,
    val author: String,
    val genre: String,
    val title: String,
    val year: Int
)
