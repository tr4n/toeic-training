package com.example.toeictraining.ui.fragments.reminder


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.base.entity.Topic
import com.example.toeictraining.utils.Constants
import com.example.toeictraining.utils.DateUtil
import com.example.toeictraining.utils.PracticeMode
import com.example.toeictraining.utils.showDatePickerDialog
import com.example.toeictraining.works.RemindWorker
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class RemindFragment private constructor() : BaseFragment<RemindViewModel>(),
    View.OnClickListener,
    CompoundButton.OnCheckedChangeListener,
    MaterialSpinner.OnItemSelectedListener<String> {

    override val layoutResource: Int get() = R.layout.fragment_setting
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

        context?.run {
            spinnerPracticeMode?.setItems(
                getString(R.string.title_practice_low),
                getString(R.string.title_practice_normal),
                getString(R.string.title_practice_high)
            )
        }

        registerEvents()
    }

    private fun registerEvents() {
        switchReminder.setOnCheckedChangeListener(this)
        switchReviewWords.setOnCheckedChangeListener(this)
        imageBackSetting.setOnClickListener(this)
        imageSaveReminder.setOnClickListener(this)
        textDoWorkExam.setOnClickListener(this)
        textDoWorkVocabulary.setOnClickListener(this)
        textDoWorkVocabularyTime.setOnClickListener(this)
        textDoWorkExamTime.setOnClickListener(this)
        textSettingStartDay.setOnClickListener(this)
        textOfficialDeadline.setOnClickListener(this)
        spinnerPracticeMode.setOnItemSelectedListener(this)
        textSettingStartDay.setOnClickListener(this)
    }

    override fun observeData() = with(viewModel) {
        super.observeData()

        startPracticeDay.observe(viewLifecycleOwner, Observer(::showStartDay))
        endPracticeDay.observe(viewLifecycleOwner, Observer(::showEndDay))
        practiceMode.observe(viewLifecycleOwner, Observer(::showPracticeMode))

        remindTime.observe(viewLifecycleOwner, Observer(::onObserverRemindTime))
        topics.observe(viewLifecycleOwner, Observer(::onObserverReviewTopics))
        reviewMode.observe(viewLifecycleOwner, Observer(::onObserverReviewMode))
        reviewTopic.observe(viewLifecycleOwner, Observer {
            context?.run {
                toast(getString(R.string.msg_remind_topics) + " " + it)
            }
        })
    }

    private fun showStartDay(time: String) {
        textSettingStartDay?.text = time
    }

    private fun showEndDay(time: String) {
        textOfficialDeadline?.text = time
    }

    private fun showPracticeMode(practiceMode: Int) {
        context?.run {
            spinnerPracticeMode.selectedIndex = practiceMode - 1
        }
    }

    private fun onObserverRemindTime(time: String?) {
        time?.let {
            groupConstraintDailyReminder?.isVisible = true
            textDoWorkExamTime?.text = time
            switchReminder.isChecked = true
        }
    }

    private fun onObserverReviewMode(isEnable: Boolean) {
        recyclerReviews.isVisible = isEnable == true
        switchReviewWords.isChecked = isEnable == true
    }

    private fun onObserverReviewTopics(topics: List<Topic>) = remindTopicAdapter.submitList(topics)

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.textSettingStartDay -> context?.showDatePickerDialog(DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.saveStartDay(DateUtil.getDate(dayOfMonth, month, year))
            })
            R.id.textOfficialDeadline -> context?.showDatePickerDialog(DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.saveEndDay(DateUtil.getDate(dayOfMonth, month, year))
            })
            R.id.imageSaveReminder -> toast(getString(R.string.msg_save_setting))
            R.id.imageBackSetting -> activity?.onBackPressed()
            R.id.textDoWorkExamTime -> showTimePickerDialog()
        }
    }

    override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: String?) {
        viewModel.savePracticeMode(
            when (item) {
                getString(R.string.title_practice_low) -> PracticeMode.LOW
                getString(R.string.title_practice_normal) -> PracticeMode.NORMAL
                getString(R.string.title_practice_high) -> PracticeMode.HIGH
                else -> PracticeMode.EMPTY
            }
        )
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.switchReminder -> handleTimeRemindSwitch(buttonView, isChecked)
            R.id.switchReviewWords -> handleReviewModeEnable(buttonView, isChecked)
        }
    }

    private fun handleTimeRemindSwitch(buttonView: CompoundButton, isChecked: Boolean) {
        groupConstraintDailyReminder?.isVisible = isChecked == true
        if (!buttonView.isShown) return

        if (isChecked && !isFirstTimeBindData) {
            showTimePickerDialog()
            enableDailyReminder("00:00")
        } else if (!isFirstTimeBindData) {
            disableDailyReminder()
        }
        isFirstTimeBindData = false
    }

    private fun handleReviewModeEnable(buttonView: CompoundButton, isChecked: Boolean) {
        recyclerReviews?.isVisible = isChecked == true
        viewModel.saveEnableReviewMode(isChecked)
    }

    private fun enableDailyReminder(time: String) {

        disableDailyReminder()
        val constraints = Constraints.Builder().setRequiresCharging(true).build()

        val saveRequest = PeriodicWorkRequestBuilder<RemindWorker>(1, TimeUnit.DAYS)
            .addTag(Constants.TAG_DAILY_REMINDER)
            .setInitialDelay(DateUtil.getDelayMinutes(time), TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        context?.let {
            WorkManager.getInstance(it).enqueue(saveRequest)
        }
    }

    private fun disableDailyReminder() {
        viewModel.removeRemindTime()
        context?.let {
            WorkManager.getInstance(it).cancelAllWorkByTag(Constants.TAG_DAILY_REMINDER)
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                textDoWorkExamTime.text =
                    String.format(DateUtil.TIME_FORMAT, hourOfDay, minute).also {
                        viewModel.saveRemindTime(it)
                        enableDailyReminder(it)
                    }
            },
            currentHour, currentMinute, true
        ).show()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    companion object {
        val TAG: String = RemindFragment::class.java.name
        fun newInstance() = RemindFragment()
    }
}
