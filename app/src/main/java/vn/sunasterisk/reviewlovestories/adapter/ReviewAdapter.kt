package vn.sunasterisk.reviewlovestories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.databinding.ItemReviewBinding

class ReviewAdapter(
    context: Context,
    val reviews: List<Review>
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemReviewBinding>(
            inflater,
            R.layout.item_review,
            viewGroup,
            false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(reviews[position])
    }

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(review: Review) = with(binding) {
            binding.review = review
            binding.executePendingBindings()
        }
    }
}
