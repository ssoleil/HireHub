package com.example.hirehub.model.entities

import androidx.room.*
import java.util.Date

@Entity(tableName = "education_table")
@TypeConverters(DateConverter::class)
class Education (

    @PrimaryKey(autoGenerate = true) val educationId: Int = 0,
    @ColumnInfo(name = "education_year_start") val year_start: Date,
    @ColumnInfo(name = "education_year_end") val year_end: Date,
    @ColumnInfo(name = "education_host_institution") val host_institution: String,
    @ColumnInfo(name = "education_name") val name: String,
    @ColumnInfo(name = "education_faculty") val faculty: String,

)
