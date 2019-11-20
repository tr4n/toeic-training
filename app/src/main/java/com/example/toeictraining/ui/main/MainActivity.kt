package com.example.toeictraining.ui.main

import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseActivity
import com.example.toeictraining.ui.fragments.test.do_test.DoTestFragment
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.fragments.vocabulary.VocabularyFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view_menu.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : BaseActivity() {

    override val layoutResource: Int = R.layout.activity_main
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun initComponent() {
        setToolbar()
        setNavigationView()
        setRightButtonText("")

        openFragment(R.id.content, HomeTestFragment(), false)
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

        textLearnWord.setOnClickListener {
            toolbar?.toolbar_title?.text = getString(R.string.title_vocabulary)
            openFragment(R.id.content, VocabularyFragment(), true)
            drawer_layout.closeDrawer(GravityCompat.START)
        }
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
}
