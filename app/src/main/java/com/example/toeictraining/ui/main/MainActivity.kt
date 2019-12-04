package com.example.toeictraining.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseActivity
import com.example.toeictraining.ui.fragments.home.HomeFragment
import com.example.toeictraining.ui.fragments.reminder.RemindFragment
import com.example.toeictraining.ui.fragments.test.do_test.DoTestFragment
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.fragments.test.start_test.StartTestFragment
import com.example.toeictraining.ui.fragments.vocabulary.VocabularyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_do_test.*
import kotlinx.android.synthetic.main.navigation_view_menu.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override val layoutResource: Int = R.layout.activity_main

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private var loadingDialog: Dialog? = null

    override fun initComponent() {
        setToolbar()
        setNavigationView()
        setRightButtonText("")
        openFragment(HomeFragment.newInstance(), false)
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
        val TAG: String = MainActivity::class.java.name
    }

    override fun initData() {}

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
    }

    private fun setNavigationView() {
        drawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        drawer_layout.addDrawerListener(drawerToggle)
        navigation_view.itemIconTintList = null

        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        textHome.setOnClickListener(this)
        textTest.setOnClickListener(this)
        textVocabulary.setOnClickListener(this)
        textReminder.setOnClickListener(this)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    fun setRightButtonText(content: String) {
        toolbar.toolbar_button_right.text = content
    }

    fun getDrawerToggle(): ActionBarDrawerToggle = drawerToggle
    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.content)) {
            is DoTestFragment -> {
                val alertDialog = AlertDialog.Builder(this)
                    .setView(R.layout.dialog_do_test)
                    .setCancelable(false)
                    .create()
                alertDialog?.let {
                    it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    it.show()
                    it.button_submit.text = getString(R.string.no)
                    it.button_not_submit.text = getString(R.string.exit)
                    it.button_submit.setOnClickListener {
                        alertDialog.cancel()
                    }
                    it.button_not_submit.setOnClickListener {
                        openFragment(
                            HomeTestFragment(),
                            false
                        )
                        alertDialog.cancel()
                    }
                }
            }

            is StartTestFragment -> {
                openFragment(
                    HomeTestFragment(),
                    false
                )
            }
            else -> {
                super.onBackPressed()
            }
        }
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    fun setTitle(title: String) {
        toolbar.toolbar_title.text = title
    }

    fun setOnClickToolbarRightButton(onClickListener: View.OnClickListener) {
        toolbar_button_right.setOnClickListener(onClickListener)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textHome -> openHomeScreen()
            R.id.textTest -> openTestScreen()
            R.id.textVocabulary -> openVocabularyScreen()
            R.id.textReminder -> openReminderScreen()
        }
    }

    private fun openHomeScreen() {
        toolbar?.toolbar_title?.text = getString(R.string.title_home)
        openFragment(HomeFragment.newInstance(),  true)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun openTestScreen() {
        openFragment(
            HomeTestFragment(), true
        )
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun openVocabularyScreen() {
        toolbar?.toolbar_title?.text = getString(R.string.title_vocabulary)
        openFragment(VocabularyFragment.newInstance(), true)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun openReminderScreen() {
        openFragment(RemindFragment.newInstance(), true)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = Dialog(this).apply {
                this.requestWindowFeature(Window.FEATURE_NO_TITLE)
                this.window?.setBackgroundDrawableResource(android.R.color.transparent)
                setContentView(R.layout.dialog_loading)
                setCanceledOnTouchOutside(true)
            }
        }
        loadingDialog?.show()
    }

    fun cancelLoadingDialog() {
        if (loadingDialog == null) return
        loadingDialog?.cancel()
    }
}
