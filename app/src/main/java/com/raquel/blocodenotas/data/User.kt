package com.raquel.blocodenotas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val notesTitle: String,
    val notesSubtitle: String,
    val notesDate: String,
    val notes: String,
    val notesPriority: String,
)