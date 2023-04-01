package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer_category_table")
class OfferCategory (

    @PrimaryKey(autoGenerate = true) val categoryId: Int = 0,
    @ColumnInfo(name = "category_name") val categoryName: String
)
