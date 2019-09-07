package vn.sunasterisk.reviewlovestories.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
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
    var plotMark: Float = 0F,
    var mainCharMark: Float = 0F,
    var contentReview: String = "",
    var isShared: Boolean = false,
    var imageUrl: String = "",
    var uploadTime: Date? = null
) : Parcelable
