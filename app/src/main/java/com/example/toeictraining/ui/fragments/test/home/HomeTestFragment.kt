package com.example.toeictraining.ui.fragments.test.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import com.example.toeictraining.ui.fragments.test.start_test.StartTestFragment
import com.example.toeictraining.ui.main.MainActivity
import kotlinx.android.synthetic.main.home_test_fragment.*

class HomeTestFragment : Fragment() {

    companion object {
        val TAG = HomeTestFragment::class.java.name
        const val PART_ID = "part_id"
        const val TIME = "time"
        val IDS_PART = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val TIMES_PART = arrayOf(
            "00:06:00",
            "00:25:00",
            "00:39:00",
            "00:30:00",
            "00:30:00",
            "00:16:00",
            "00:54:00",
            "02:00:00"
        )
    }

    private lateinit var viewModel: TestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        (activity as MainActivity).setTitle(resources.getString(R.string.test_toeic))
        (activity as MainActivity).setRightButtonText("")

        configNavigationIcon()

        setRecyclerViewListPart()

//        drawerToggle.isDrawerIndicatorEnabled = true
//        toolbar.setNavigationIcon(R.drawable.menu_white_24dp)
    }

    private fun configNavigationIcon() {
        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarDrawerToggle = (activity as MainActivity).getDrawerToggle()
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
    }

    private fun setRecyclerViewListPart() {
        val resourcesImage = arrayOf(
            R.drawable.part_1,
            R.drawable.part_2,
            R.drawable.part_3,
            R.drawable.part_4,
            R.drawable.part_5,
            R.drawable.part_6,
            R.drawable.part_7,
            R.drawable.full_7_part
        )
        recyclerViewPart.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        with(recyclerViewPart) {
            addItemDecoration(
                DividerItemDecoration(
                    context!!,
                    DividerItemDecoration.VERTICAL
                ).apply {
                    setDrawable(context?.getDrawable(R.drawable.divider_recyclerview_vertical)!!)
                })
            addItemDecoration(
                DividerItemDecoration(
                    context!!,
                    DividerItemDecoration.HORIZONTAL
                ).apply {
                    setDrawable(context?.getDrawable(R.drawable.divider_recyclerview_horizontal_10)!!)
                })
            adapter = ListPartAdapter(
                context!!,
                resourcesImage,
                object : ItemClickListener {
                    override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                        openStartTestFragment(position)
                    }
                }
            )
        }
    }

    private fun openStartTestFragment(index: Int) {
        val bundle = Bundle()
        bundle.putInt(PART_ID, IDS_PART[index])
        bundle.putString(TIME, TIMES_PART[index])
        val fragment = StartTestFragment()
        fragment.arguments = bundle
        (activity as MainActivity).openFragment(R.id.content, fragment, true)
    }

}