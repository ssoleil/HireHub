package com.example.hirehub.model.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.hirehub.model.entities.Offer
import com.example.hirehub.model.entities.User
import java.io.Serializable

data class UserOfferPair (
    @Embedded
    var user: User,
    @Relation(
        parentColumn = "user_id",
        entity = Offer::class,
        entityColumn = "offer_id",
        associateBy = Junction(
            value = UserWithOffer::class,
            parentColumn = "user_id",
            entityColumn = "offer_id"
        )
    )
    var offers: List<Offer>
) : Serializable