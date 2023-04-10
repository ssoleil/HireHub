package com.example.hirehub.model.entities

import androidx.room.*

@Entity(tableName = "skill_table",
        indices = [Index(value = ["skill_name"], unique = true)],
        foreignKeys = [
            androidx.room.ForeignKey(
                entity = OfferCategory::class,
                childColumns = ["skill_category_offer_id"],
                parentColumns = ["categoryId"],
                onDelete = ForeignKey.CASCADE
            )
        ])


class Skill (

    @PrimaryKey(autoGenerate = true) val skillId: Int = 0,
    @ColumnInfo(name = "skill_category_offer_id") val category_offer_id: Int,
    @ColumnInfo(name = "skill_name") val name: String,

)


data class SkillWithSkillCategoryOffer(
    @Embedded val offer: Offer,
    @Relation(
        parentColumn = "skill_category_offer_id",
        entityColumn = "categoryId"
    )
    val offerCategory: OfferCategory
)
