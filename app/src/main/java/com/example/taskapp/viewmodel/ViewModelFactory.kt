package com.example.taskapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.repository.UsersRepository
import com.example.taskapp.services.UsersViewModel

class ViewModelFactory constructor(private val repository: UsersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsersViewModel::class.java)){
            UsersViewModel(this.repository) as T
        }else{
            throw IllegalAccessException("VM не найден")
        }
    }

}