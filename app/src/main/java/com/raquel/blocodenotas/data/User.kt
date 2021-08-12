package com.raquel.blocodenotas.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var notesTitleDB: String,
        var notesSubtitleDB: String,
        var notesContent: String,
        var notesDate: String,
        var notesPriorityDB: Priority

): Parcelable