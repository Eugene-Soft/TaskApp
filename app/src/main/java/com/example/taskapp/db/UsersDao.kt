package com.example.taskapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskapp.model.UserRoom

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user : UserRoom)

    @Query("Select * from users order by id ASC")
    fun getAllUsersHistory(): LiveData<List<UserRoom>>



}