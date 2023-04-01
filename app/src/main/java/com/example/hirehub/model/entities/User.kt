package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(indices = [Index(value = ["user_username"],
//    unique = true)])

@Entity(tableName = "user_table")
class User(

    @PrimaryKey(autoGenerate = true) val user_id: Int = 0,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "user_username") val userUsername: String,
    @ColumnInfo(name = "user_pwd") val userPwd: String,
    //only 2 values - "seeker", "hr"
    @ColumnInfo(name = "user_status") val userStatus: String,
    @ColumnInfo(name = "user_age") val userAge: Int?,
    @ColumnInfo(name = "user_phone") val userPhone: String?,
    @ColumnInfo(name = "user_self_description") val userSelfDescription: String?,
    @ColumnInfo(name = "user_company") val userCompany: String?

)