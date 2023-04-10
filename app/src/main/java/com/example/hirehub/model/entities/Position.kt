package com.example.hirehub.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "position_table",
    indices = [Index(value = ["position_name"], unique = true)]
    )
class Position (
    @PrimaryKey(autoGenerate = true) val positionId: Int = 0,
    @ColumnInfo(name = "position_name") val positionName: String,
)
