package com.raquel.blocodenotas.data

import androidx.room.TypeConverter


class Converter {
    @TypeConverter
    fun fromPriorityObjectToString(priority: Priority): String{
        return priority.name
    }
    @TypeConverter
    fun fromStringToPriorityObject(priority: String): Priority{
        return Priority.valueOf(priority)
    }
}