package com.example.vocabulary_book.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int?, // PrimaryKey

    @ColumnInfo(name = "email")
    var email : String, // 이메일

    @ColumnInfo(name = "nickname")
    var nickname : String, // 닉네임

    @ColumnInfo(name = "imageUrl")
    var imageUrl : String // 프로필 이미지 URL
)