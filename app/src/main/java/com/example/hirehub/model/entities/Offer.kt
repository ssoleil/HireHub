package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer_table")
class Offer (

    @PrimaryKey(autoGenerate = true) val offer_id: Int,
    @ColumnInfo(name = "offer_name") val offerName: String,
    @ColumnInfo(name = "offer_category_id") val offerCategoryId: String?,
    @ColumnInfo(name = "offer_company_name") val offerCompanyName: String?,
    @ColumnInfo(name = "offer_salary") val offerSalary: String?,
    @ColumnInfo(name = "offer_city") val offerCity: String?,
    @ColumnInfo(name = "offer_description") val offerDescription: String,
    @ColumnInfo(name = "offer_position") val offerPosition: String,
    @ColumnInfo(name = "offer_status") val offerStatus: String,
)