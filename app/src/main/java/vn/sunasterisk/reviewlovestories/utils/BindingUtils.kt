package vn.sunasterisk.reviewlovestories.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import vn.sunasterisk.reviewlovestories.R

object BindingUtils {
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun ImageView.setImageUrl(imageUrl: String) {
        Glide.with(this.context)
            .load(Uri.parse(imageUrl))
            .error(R.mipmap.ic_launcher)
            .into(this)
    }
}
