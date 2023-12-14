package com.example.vocabulary_book.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vocabulary_book.db.dao.UserDao
import com.example.vocabulary_book.db.dao.VokanoteDao
import com.example.vocabulary_book.db.entity.User
import com.example.vocabulary_book.db.entity.Vokanote

@Database(entities = [User::class, Vokanote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun vokanoteDao() : VokanoteDao

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {

                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}