package com.example.taskapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.model.UserRoom

@Database(entities = arrayOf(UserRoom::class), version = 2, exportSchema = false)
abstract class UserDb : RoomDatabase() {

    abstract fun getUsersDao() : UsersDao

    companion object{

        private var INSTANCE : UserDb? = null

        fun getInstance(context: Context) : UserDb{

            return INSTANCE ?: synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDb::class.java,
                        "user_database"
                    ).build()
                    INSTANCE = instance
                    instance
            }

        }

    }

}