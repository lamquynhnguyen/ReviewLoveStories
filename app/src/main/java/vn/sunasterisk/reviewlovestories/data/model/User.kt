package vn.sunasterisk.reviewlovestories.data.model

data class User(
    var name: String,
    var email: String,
    var password: String,
    var imageUrl: String? = null,
    var countReview: Int = 0
)
