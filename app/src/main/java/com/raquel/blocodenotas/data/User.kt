package com.raquel.blocodenotas.data

import android.renderscript.RenderScript
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
        @PrimaryKey(autoGenerate = true)
    val id: Int,
        var notesTitleDB: String,
        var notesSubtitleDB: String,
        var notesContent: String,
        var notesDate: String,
        var notesPriorityDB: Priority

)