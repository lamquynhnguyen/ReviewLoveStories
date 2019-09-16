package vn.sunasterisk.reviewlovestories.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.adapter.ReviewAdapter
import vn.sunasterisk.reviewlovestories.base.BaseFragment
import vn.sunasterisk.reviewlovestories.base.MyApplication
import vn.sunasterisk.reviewlovestories.databinding.FragmentHomeBinding
import vn.sunasterisk.reviewlovestories.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeFragmentDataBinding: FragmentHomeBinding
    private lateinit var reviewFavoriteAdapter: ReviewAdapter
    private lateinit var reviewLatestAdapter: ReviewAdapter
    override val getContentViewId = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelAndDataBinding()
        observeData()
        handleEvents()
    }

    override fun setUpViewModelAndDataBinding() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeFragmentDataBinding = getDataBinding() as FragmentHomeBinding
        homeFragmentDataBinding.homeViewModel = homeViewModel
    }

    override fun observeData() {
        homeViewModel.reviewsByHeart.observe(this, Observer { reviews ->
            reviewFavoriteAdapter = ReviewAdapter(MyApplication.applicationContext(), reviews)
            recyclerFavoriteReview.adapter = reviewFavoriteAdapter
        })
        homeViewModel.reviewsByTime.observe(this, Observer { reviews ->
            reviewLatestAdapter = ReviewAdapter(MyApplication.applicationContext(), reviews)
            recyclerLatestReview.adapter = reviewLatestAdapter
        })
        homeViewModel.isLoadFavoriteSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                recyclerFavoriteReview.visibility = View.VISIBLE
                progressBarFavorite.visibility = View.GONE
            }
        })
        homeViewModel.isLoadLatestSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                recyclerLatestReview.visibility = View.VISIBLE
                progressBarLatest.visibility = View.GONE
            }
        })
    }

    override fun handleEvents() {

    }
}
