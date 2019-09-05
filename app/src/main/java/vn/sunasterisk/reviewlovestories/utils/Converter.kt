package vn.sunasterisk.reviewlovestories.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


object Converter {
    @TypeConverter
    @JvmStatic
    fun fromString(string: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromList(strings: List<String>) = Gson().toJson(strings)

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long): Date = Date(value)

    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date): Long = date.time
}
