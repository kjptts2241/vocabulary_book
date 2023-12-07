package com.example.vocabulary_book.repository

import com.example.vocabulary_book.CapstoneApplication
import com.example.vocabulary_book.db.MyDatabase
import com.example.vocabulary_book.db.entity.UserEntity

class UserRepository {

    private val context = CapstoneApplication.context()

    private val db = MyDatabase.getDatabase(context)

    // CRUD
    fun insert(userEntity: UserEntity) = db.userDao().insert(userEntity)

    fun getAllUser() = db.userDao().getAllUser()

    fun update(userEntity: UserEntity) = db.userDao().update(userEntity)

    fun delete(userEntity: UserEntity) = db.userDao().delete(userEntity)

    fun isEmailExists(email: String) = db.userDao().isEmailExists(email)
}