package com.example.taskapp.db

import androidx.lifecycle.LiveData
import com.example.taskapp.model.UserRoom

class UserDbRepository(private val usersDao: UsersDao) {

    val allUsers : LiveData<List<UserRoom>> = usersDao.getAllUsersHistory()


    fun insert(user : UserRoom){
        usersDao.insertUser(user)
    }
}