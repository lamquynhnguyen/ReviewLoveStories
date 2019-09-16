package vn.sunasterisk.reviewlovestories.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "reviews")
@Parcelize
data class Review(
    @PrimaryKey(autoGenerate = true) var id: Int = -1,
    var userName: String = "",
    var novelName: String = "",
    var authorName: String = "",
    var genres: List<String> = mutableListOf(),
    var heartCount: Int = 0,
    var contentReview: String = "",
    var isShared: Boolean = false,
    var uploadTime: Date? = null,
    var imageUrl: String = ""
) : Parcelable {
    fun toSimpleString(date: Date): String {
        val format = SimpleDateFormat.getDateInstance()
        return format.format(date)
    }

    fun fromNumberToString(number: Int): String = number.toString()
}
