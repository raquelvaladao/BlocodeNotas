package com.raquel.blocodenotas.viewmodel

import androidx.room.TypeConverter
import com.raquel.blocodenotas.data.Priority


class Converter {
    @TypeConverter
    fun fromPriorityObjectToString(priority: Priority): String{
        return priority.name
    }
    @TypeConverter
    fun fromStringToPriorityObject(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}