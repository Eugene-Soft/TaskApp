package com.example.taskapp.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskapp.model.UserRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class UserDbViewModel (application: Application) : AndroidViewModel(application){

    val allUsers : LiveData<List<UserRoom>>
    val userDbRepository : UserDbRepository

    init {
        val dao = UserDb.getInstance(application).getUsersDao()
        userDbRepository = UserDbRepository(dao)
        allUsers = userDbRepository.allUsers
    }

    fun addUser(user : UserRoom) = viewModelScope.launch(Dispatchers.IO){
        userDbRepository.insert(user)
    }

}