package com.example.vocabulary_book.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "nickname")
    var nickname : String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl : String
)