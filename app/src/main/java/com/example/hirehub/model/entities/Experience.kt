package com.example.hirehub.model.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "experience_table",
    foreignKeys = [
        ForeignKey(
        entity = User::class,
        childColumns = ["experience_user_id"],
        parentColumns = ["user_id"],
        onDelete = CASCADE
        ),
        ForeignKey(
            entity = Position::class,
            childColumns = ["experience_position_id"],
            parentColumns = ["positionId"],
            onDelete = CASCADE
        ),
    ])
@TypeConverters(DateConverter::class)
class Experience (

    @PrimaryKey(autoGenerate = true) val experienceId: Int = 0,
    @ColumnInfo(name = "experience_user_id") val user_id: Int,
    @ColumnInfo(name = "experience_company") val company: String,
    @ColumnInfo(name = "experience_year_start") val year_start: String,
    @ColumnInfo(name = "experience_year_end") val year_end: String,
    @ColumnInfo(name = "experience_position_id") val position_id: Int,
    @ColumnInfo(name = "experience_description") val description: String?,

    )



