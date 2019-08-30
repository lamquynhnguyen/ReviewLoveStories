package vn.sunasterisk.reviewlovestories.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var userName: String,
    var novelName: String,
    var authorName: String,
    var genres: List<String>,
    var heartCount: Int? = null,
    var plotMark: Float? = null,
    var mainCharMark: Float? = null,
    var contentReview: String? = null,
    var isShared: Boolean? = null,
    var imageUrl: String? = null
)
