package com.example.hirehub.model.entities.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User
import java.io.Serializable

@Entity(tableName = "user_offer_table",
    primaryKeys = ["user_id", "offer_id"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.NO_ACTION),
        ForeignKey(
            entity = Offer::class,
            parentColumns = ["offer_id"],
            childColumns = ["offer_id"],
            onDelete = ForeignKey.NO_ACTION)
    ])
class UserWithOffer (
    val user_id: Int,
    val offer_id: Int,
) : Serializable

