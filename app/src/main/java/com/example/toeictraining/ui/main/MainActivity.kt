package com.example.toeictraining.ui.main

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseActivity
import com.example.toeictraining.ui.fragments.reminder.RemindFragment
import com.example.toeictraining.ui.fragments.intro.IntroDateFragment
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.fragments.vocabulary.VocabularyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view_menu.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override val layoutResource: Int = R.layout.activity_main
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun initComponent() {
        //setToolbar()
        //setNavigationView()
        setRightButtonText("")

        //openFragment(R.id.content, HomeTestFragment(), false)
        openFragment(R.id.content, IntroDateFragment(), false)
    }

    override fun initData() {}

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

        textTest.setOnClickListener {
            openFragment(
                R.id.content,
                HomeTestFragment(), true
            )
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        textVocabulary.setOnClickListener(this)
        textReminder.setOnClickListener(this)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

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

    fun getDrawerToggle(): ActionBarDrawerToggle {
        return drawerToggle
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    fun setTitle(title: String) {
        toolbar.toolbar_title.text = title
    }

    fun setRightButtonText(content: String) {
        toolbar.toolbar_button_right.text = content
    }

    fun setOnClickToolbarRightButton(onClickListener: View.OnClickListener) {
        toolbar_button_right.setOnClickListener(onClickListener)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textVocabulary -> openVocabularyScreen()
            R.id.textReminder -> openReminderScreen()
        }
    }

    private fun openVocabularyScreen() {
        toolbar?.toolbar_title?.text = getString(R.string.title_vocabulary)
        openFragment(R.id.content, VocabularyFragment(), true)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun openReminderScreen() {
        openFragment(R.id.content, RemindFragment(), true)
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
