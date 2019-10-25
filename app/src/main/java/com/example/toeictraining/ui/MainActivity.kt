package com.example.toeictraining.ui

import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseActivity
import com.example.toeictraining.ui.fragments.learn_word.LearnWordFragment
import com.example.toeictraining.ui.fragments.test.TestFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view_menu.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override val layoutResource: Int = R.layout.activity_main
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun initComponent() {
        setToolbar()
        setNavigationView()
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

        txtTest.setOnClickListener {
            openFragment(
                R.id.content,
                TestFragment(), true
            )
        }

        txtLearnWord.setOnClickListener {
            openFragment(
                R.id.content,
                LearnWordFragment(), true
            )
        }

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setHomeButtonEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
    }
}
