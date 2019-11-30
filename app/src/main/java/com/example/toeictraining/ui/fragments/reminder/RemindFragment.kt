package com.example.toeictraining.ui.fragments.reminder


import android.app.TimePickerDialog
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.base.entity.Topic
import kotlinx.android.synthetic.main.fragment_reminder.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RemindFragment : BaseFragment<RemindViewModel>(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    override val layoutResource: Int get() = R.layout.fragment_reminder
    override val viewModel: RemindViewModel by viewModel()

    private val remindTopicAdapter: RemindTopicAdapter = get()

    private var isFirstTimeBindData = true

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun initComponents() {
        recyclerReviews?.adapter = remindTopicAdapter.apply {
            onTopicSelected = { topic ->
                viewModel.updateTopicReviews(topic)
            }
        }
        switchReminder.setOnCheckedChangeListener(this)
        switchReviewWords.setOnCheckedChangeListener(this)
        imageBackRemind.setOnClickListener(this)
        imageSaveReminder.setOnClickListener(this)
        textPickerTime.setOnClickListener(this)
    }

    override fun observeData() = with(viewModel) {
        super.observeData()
        remindTime.observe(viewLifecycleOwner, Observer(::onObserverRemindTime))
        topics.observe(viewLifecycleOwner, Observer(::onObserverReviewTopics))
    }

    private fun onObserverRemindTime(time: String?) {
        time?.let {
            textPickerTime?.run {
                text = time
                isVisible = true
            }
            switchReminder.isChecked = true
        }
    }

    private fun onObserverReviewTopics(topics: List<Topic>) = remindTopicAdapter.submitList(topics)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageSaveReminder -> toast(getString(R.string.msg_save_setting))
            R.id.imageBackRemind -> activity?.onBackPressed()
            R.id.textPickerTime -> showTimePickerDialog()
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.switchReminder -> handleTimeRemindSwitch(buttonView, isChecked)
            R.id.switchReviewWords -> handleReviewModeEnable(buttonView, isChecked)
        }
    }

    private fun handleTimeRemindSwitch(buttonView: CompoundButton, isChecked: Boolean) {
        textPickerTime?.isVisible = isChecked == true

        if (buttonView.isShown && isChecked) {
            if (!isFirstTimeBindData) showTimePickerDialog()
            isFirstTimeBindData = false
        }
    }

    private fun handleReviewModeEnable(buttonView: CompoundButton, isChecked: Boolean) {
        recyclerReviews?.isVisible = isChecked == true
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                textPickerTime.text = String.format("%02d:%02d", hourOfDay, minute).also {
                    viewModel.saveRemindTime(it)
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
