package com.example.vocabulary_book

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserData(

    @PrimaryKey(autoGenerate = true) val id: Int = 0, // PrimaryKey
    val userEmail : String,       // 유저 이메일
    val name : String,            // 유저 닉네임
    val profileImage : String,    // 유저 프로필

): Parcelable