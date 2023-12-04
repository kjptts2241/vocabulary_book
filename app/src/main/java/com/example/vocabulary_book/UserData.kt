package com.example.vocabulary_book

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserData(

    @PrimaryKey(autoGenerate = true) val id: Int = 0, // PrimaryKey
    val email : String?,       // 유저 이메일
    val nickname : String?,        // 유저 닉네임
    val profileImageUrl : String?,    // 유저 프로필 URL

): Parcelable