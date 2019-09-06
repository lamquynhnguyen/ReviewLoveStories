package vn.sunasterisk.reviewlovestories.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.utils.Converter

@Database(entities = [Review::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ReviewRoomDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao

    companion object {
        private var INSTANCE: ReviewRoomDatabase? = null
        private val lock = Any()
        private const val DATABASE_NAME = "Reviews.db"

        fun getInstance(context: Context): ReviewRoomDatabase =
            INSTANCE ?: synchronized(lock) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ReviewRoomDatabase::class.java, DATABASE_NAME)
                    .build().also { INSTANCE = it }
            }
    }
}
