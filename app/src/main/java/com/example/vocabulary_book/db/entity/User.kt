package com.example.vocabulary_book.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int?, // Primary Key

    @ColumnInfo(name = "email")
    val email : String, // 이메일

    @ColumnInfo(name = "nickname")
    val nickname : String, // 닉네임

    @ColumnInfo(name = "imageUrl")
    val imageUrl : String, // 프로필 이미지 URL
)