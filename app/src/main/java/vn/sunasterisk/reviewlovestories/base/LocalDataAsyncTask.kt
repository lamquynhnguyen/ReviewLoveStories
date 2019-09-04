package vn.sunasterisk.reviewlovestories.base

import android.os.AsyncTask
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.data.source.local.ReviewDao
import vn.sunasterisk.reviewlovestories.utils.MethodType

class LocalDataAsyncTask(
    private val reviewDao: ReviewDao,
    private val methodNumber: Int
) : AsyncTask<Review, Any, Any>() {
    override fun doInBackground(vararg params: Review) {
        when (methodNumber) {
            MethodType.INSERT -> reviewDao.insertReview(params[0])
            MethodType.UPDATE -> reviewDao.updateReview(params[0])
            MethodType.DELETE -> reviewDao.deleteReview(params[0])
        }
    }
}
