package com.example.taskapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserRoom(
    @ColumnInfo(name = "user_name")
    val name : String?,
    @ColumnInfo(name = "user_avatar")
    val avatar : String?,
    @ColumnInfo(name = "user_email")
    val email : String?
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}