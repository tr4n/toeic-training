package com.example.toeictraining.ui.fragments.vocabulary

import androidx.lifecycle.Observer
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.di.ScopeNames
import com.example.toeictraining.ui.fragments.study.StudyFragment
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class VocabularyFragment : BaseFragment<VocabularyViewModel>() {

    override val layoutResource = R.layout.fragment_vocabulary

    override val viewModel: VocabularyViewModel by viewModel()

    private val categoryAdapter: CategoryAdapter by lazy {
        get<CategoryAdapter>(named(ScopeNames.CATEGORY_ADAPTER)).apply {
            onTopicClick = { topic ->
                replaceFragment(
                    id = R.id.content,
                    fragment = StudyFragment.newInstance(topic),
                    addToBackStack = true
                )
            }
        }
    }

    override fun initComponents() {
        recyclerCategories?.adapter = categoryAdapter
    }

    override fun observeData() = with(viewModel) {
        super.observeData()

        categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter.submitList(it)
        })
    }
}
