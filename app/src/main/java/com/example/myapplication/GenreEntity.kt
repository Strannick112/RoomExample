package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="genre")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo() val type: String
)
