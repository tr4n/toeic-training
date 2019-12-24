package com.example.toeictraining.ui.fragments.intro


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.toeictraining.R
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.intro_date_fragment.*
import java.util.Calendar


class IntroDateFragment : Fragment(){

    companion object {
        val TAG = IntroDateFragment::class.java.name
    }

    private lateinit var viewModel: IntroDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroDateViewModel::class.java)

        initViews()
        pickDate()
    }

    private fun initViews() {

    }


    private fun pickDate() {
        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)

        textDate.setOnClickListener {
            val dlg = DatePickerDialog(
                this.activity,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    textDate.text = String.format(DateUtils.DATE_FORMAT, year, month, dayOfMonth)
                },
                currentYear,
                currentMonth,
                currentDay
            )
            dlg.show()
        }
    }

//        context?.let {
//            DatePickerDialog(
//                it,
//                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
//                    textDate.text = String.format(DateUtils.DATE_FORMAT, year, month, dayOfMonth)
//                }, currentYear, currentMonth, currentDay
//            ).show()
//        }
}