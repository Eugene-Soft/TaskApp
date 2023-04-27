package com.example.taskapp.repository

import com.example.taskapp.services.ApiInterface
import com.example.taskapp.services.ServiceBuilder

class UsersRepository constructor(private val retrofitServices : ServiceBuilder) {

    fun getAllUsers() = ServiceBuilder.buildServices(ApiInterface::class.java).getAllUsers()

}
