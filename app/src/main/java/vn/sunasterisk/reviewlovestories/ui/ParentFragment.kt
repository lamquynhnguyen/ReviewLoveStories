package vn.sunasterisk.reviewlovestories.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_parent.*
import vn.sunasterisk.reviewlovestories.MainActivity
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.base.BaseFragment
import vn.sunasterisk.reviewlovestories.databinding.FragmentParentBinding
import vn.sunasterisk.reviewlovestories.utils.AuthenticationState
import vn.sunasterisk.reviewlovestories.viewmodel.SignInViewModel

class ParentFragment : BaseFragment() {
    private val parentNavController by lazy {
        Navigation.findNavController(activity!!, R.id.navHostInParent)
    }
    private val mainNavController by lazy {
        Navigation.findNavController(activity!!, R.id.navHostInMain)
    }
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var parentFragmentBinding: FragmentParentBinding
    override val getContentViewId = R.layout.fragment_parent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbarAndBottomNav()
        setUpViewModelAndDataBinding()
        if (FirebaseAuth.getInstance().currentUser == null) {
            observeData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuLogOut -> {
                FirebaseAuth.getInstance().signOut()
                mainNavController.navigate(R.id.signInFragment)
            }
        }
        return true
    }

    override fun setUpViewModelAndDataBinding() {
        signInViewModel = ViewModelProviders.of(activity as MainActivity).get(SignInViewModel::class.java)
        parentFragmentBinding = getDataBinding() as FragmentParentBinding
        parentFragmentBinding.signInViewModel = signInViewModel
    }

    override fun observeData() {
        signInViewModel.authenticationState.observe(this, Observer { authenticationState ->
            if (authenticationState == AuthenticationState.UNAUTHENTICATED)
                mainNavController.navigate(R.id.signInFragment)
        })
    }

    override fun handleEvents() {

    }

    private fun setUpToolbarAndBottomNav() {
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbarMain)
        NavigationUI.setupWithNavController(toolbarMain, parentNavController)
        bottomNavigation.setupWithNavController(parentNavController)
        setHasOptionsMenu(true)
    }
}
