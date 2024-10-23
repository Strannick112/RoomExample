package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="author")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo() val name: String
)
