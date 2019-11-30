package com.example.toeictraining.ui.fragments.reminder


import android.app.TimePickerDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_reminder.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RemindFragment : BaseFragment<RemindViewModel>(), View.OnClickListener {

    override val layoutResource: Int get() = R.layout.fragment_reminder
    override val viewModel: RemindViewModel by viewModel()

    private val reviewAdapter: ReviewAdapter = get()

    private var remindTime = "00:00"

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun initComponents() {

        switchReminder.setOnCheckedChangeListener { buttonView, isChecked ->
            textPickerTime?.isVisible = isChecked == true

            if (buttonView.isShown && isChecked) setTimeReminder()
        }

        imageBackRemind.setOnClickListener(this)
        imageSaveReminder.setOnClickListener(this)
        textPickerTime.setOnClickListener(this)
    }

    override fun observeData() = with(viewModel) {
        super.observeData()

        timeReminder.observe(viewLifecycleOwner, Observer { time ->
            time?.let {
                textPickerTime?.run {
                    text = time
                    visibility = View.VISIBLE
                }
                switchReminder.isChecked = true
            }
        })

        topics.observe(viewLifecycleOwner, Observer {
            reviewAdapter.submitList(it)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageBackRemind -> activity?.onBackPressed()
            R.id.textPickerTime -> setTimeReminder()
            R.id.imageSaveReminder -> {
            }
        }
    }

    private fun setTimeReminder() {
        context ?: return

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                textPickerTime.text = String.format("%02d:%02d", hourOfDay, minute).also {
                    remindTime = it
                }
            },
            currentHour, currentMinute, true
        ).show()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
