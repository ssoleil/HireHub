package com.example.hirehub.model.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(tableName = "offer_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = OfferCategory::class,
//            childColumns = ["offer_category_id"],
//            parentColumns = ["categoryId"],
//            onDelete = CASCADE
//        )
//    ]
)
class Offer (

    @PrimaryKey(autoGenerate = true) val offer_id: Int,
    @ColumnInfo(name = "offer_name") val offerName: String,
    @ColumnInfo(name = "offer_category_id") var offerCategoryId: Int,
    @ColumnInfo(name = "offer_company_name") var offerCompanyName: String?,
    @ColumnInfo(name = "offer_salary") var offerSalary: String?,
    @ColumnInfo(name = "offer_city") var offerCity: String?,
    @ColumnInfo(name = "offer_description") var offerDescription: String?,
    @ColumnInfo(name = "offer_position") val offerPosition: String?,

    //might be "active" (new) or "closed" if the hr submit the seeker on an offer
    @ColumnInfo(name = "offer_status") val offerStatus: String,
)


//data class OfferWithCategoryOffer(
//    @Embedded val offer: Offer,
//    @Relation(
//        parentColumn = "offer_id",
//        entityColumn = "categoryId"
//    )
//    val offerCategory: OfferCategory
//)