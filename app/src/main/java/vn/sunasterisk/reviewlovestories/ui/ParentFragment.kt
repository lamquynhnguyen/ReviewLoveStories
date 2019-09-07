package vn.sunasterisk.reviewlovestories.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_parent.*
import vn.sunasterisk.reviewlovestories.MainActivity
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.base.BaseFragment

class ParentFragment : BaseFragment() {
    override val getContentViewId = R.layout.fragment_parent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setSupportActionBar(toolbarMain)
        val navController = Navigation.findNavController(activity!!, R.id.navHostFragment)
        NavigationUI.setupWithNavController(toolbarMain, navController)
        bottomNavigation.setupWithNavController(navController)
    }
}
