package com.example.toeictraining.ui.fragments.study


import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.data.db.entity.Topic
import com.example.toeictraining.di.ScopeNames
import com.example.toeictraining.utils.Constants
import kotlinx.android.synthetic.main.fragment_study.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class StudyFragment private constructor() : BaseFragment<StudyViewModel>(), View.OnClickListener {

    override val layoutResource: Int = R.layout.fragment_study

    override val viewModel: StudyViewModel by viewModel()

    override val lifecycleOwner: LifecycleOwner = this@StudyFragment

    override fun initComponents() {
        textShowDetail?.setOnClickListener(this)
        textDidntKnow?.setOnClickListener(this)
        textKnew?.setOnClickListener(this)
        textOrigin?.setOnClickListener(this)

    }

    override fun initData() {
        viewModel.mainTopic = arguments?.getParcelable(Constants.ARGUMENT_TOPIC)
            ?: get(named(ScopeNames.EMPTY_TOPIC))
        super.initData()
    }

    override fun observeData() = with(viewModel) {
        super.observeData()

        word.observe(lifecycleOwner, Observer {
            textOrigin?.text = it.origin
            textPronoun?.text = it.pronunciation
            textExplain?.text = it.explanation
            textType?.text = it.type
            textExample?.text = it.example
            textExampleTranslate?.text = it.exampleTranslate
            textLevel?.text = it.level.toString()

            Glide.with(this@StudyFragment).load(it.imageUrl).into(imageWord)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textShowDetail -> handleDetailWord()
            R.id.textOrigin -> handleDetailWord()
        }
    }

    private fun handleDetailWord() {
        textShowDetail.isVisible = !textShowDetail.isVisible
        groupDetail.isVisible = !groupDetail.isVisible
    }

    companion object {

        fun newInstance(topic: Topic): StudyFragment = StudyFragment().apply {
            arguments = bundleOf(Constants.ARGUMENT_TOPIC to topic)
        }
    }
}
