package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(indices = [Index(value = ["user_username"],
//    unique = true)])

@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true) var user_id: Int = 0,
    @ColumnInfo(name = "user_name") var userName: String,
    @ColumnInfo(name = "user_username") val userUsername: String,
    @ColumnInfo(name = "user_pwd") val userPwd: String,
    //only 2 values - "seeker", "hr"
    @ColumnInfo(name = "user_status") val userStatus: String,
    @ColumnInfo(name = "user_age") val userAge: Int?,
    @ColumnInfo(name = "user_phone") val userPhone: String?,
    @ColumnInfo(name = "user_self_description")
    val userSelfDescription: String?,
    @ColumnInfo(name = "user_company") var userCompany: String?
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = user_id
        result = 31 * result + userName.hashCode()
        result = 31 * result + userUsername.hashCode()
        result = 31 * result + userPwd.hashCode()
        result = 31 * result + userStatus.hashCode()
        result = 31 * result + (userAge ?: 0)
        result = 31 * result + (userPhone?.hashCode() ?: 0)
        result = 31 * result + (userSelfDescription?.hashCode() ?: 0)
        result = 31 * result + (userCompany?.hashCode() ?: 0)
        return result
    }
}