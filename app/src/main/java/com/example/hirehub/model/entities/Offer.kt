package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(tableName = "offer_table",
    foreignKeys = [ForeignKey(entity = OfferCategory::class,
            childColumns = ["offer_category_id"],
            parentColumns = ["categoryId"],
            onDelete = CASCADE)])
class Offer (
    @PrimaryKey(autoGenerate = true) val offer_id: Int,
    @ColumnInfo(name = "offer_name") val offerName: String,
    @ColumnInfo(name = "offer_category_id")
    var offerCategoryId: Int,
    @ColumnInfo(name = "offer_company_name")
    var offerCompanyName: String?,
    @ColumnInfo(name = "offer_salary")
    var offerSalary: String?,
    @ColumnInfo(name = "offer_city")
    var offerCity: String?,
    @ColumnInfo(name = "offer_description")
    var offerDescription: String?,
    @ColumnInfo(name = "offer_position")
    val offerPosition: String?,
    @ColumnInfo(name = "offer_status")
    val offerStatus: String,
) : Serializable

//data class OfferWithCategory(
//    @Embedded
//    val offer: Offer,
//    @Relation(
//        parentColumn = "offer_id",
//        entityColumn = "categoryId"
//    )
//    val offerCategory: OfferCategory
//) : Serializable


data class OfferWithCategory(
    val offer_id: Int,
    val offer_name: String,
    var offer_category_id: Int,
    var offer_company_name: String?,
    var offer_salary: String?,
    var offer_city: String?,
    var offer_description: String?,
    val offer_position: String?,
    //might be "active", "new", "rejected" or "accepted" if the hr submit the seeker on an offer
    val offer_status: String,
    val categoryId: Int = 0,
    val category_name: String
) : Serializable