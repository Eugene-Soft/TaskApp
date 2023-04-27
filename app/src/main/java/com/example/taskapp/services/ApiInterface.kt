package com.example.taskapp.services

import com.example.taskapp.model.User
import com.example.taskapp.model.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    fun getAllUsers() : Call<List<User>>

    @GET("users/{login}")
    fun getUser(@Path("login") login : String) : Call<UserDetail>
}