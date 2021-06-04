package com.raquel.blocodenotas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
        @PrimaryKey(autoGenerate = true)
    val id: Int,
        val notesTitleDB: String,
        val notesSubtitleDB: String,
        val notesContent: String,
        val notesDate: String,
        val notesPriorityDB: String

)