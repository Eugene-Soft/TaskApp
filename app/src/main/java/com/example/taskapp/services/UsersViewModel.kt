package com.example.taskapp.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskapp.repository.UsersRepository
import com.example.taskapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel constructor(private val repository : UsersRepository) : ViewModel() {


    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers(){
        val response = repository.getAllUsers()
        response.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                userList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}
