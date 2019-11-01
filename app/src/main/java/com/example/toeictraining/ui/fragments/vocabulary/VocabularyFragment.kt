package com.example.toeictraining.ui.fragments.vocabulary

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.di.ScopeNames
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class VocabularyFragment : BaseFragment<VocabularyViewModel>() {

    override val layoutResource = R.layout.fragment_vocabulary

    override val viewModel: VocabularyViewModel by viewModel()

    override val lifecycleOwner: LifecycleOwner = this@VocabularyFragment

    private val categoryAdapter: CategoryAdapter = get(named(ScopeNames.CATEGORY_ADAPTER))

    override fun initComponents() {
        recyclerCategories?.adapter = categoryAdapter
    }

    override fun observeData() = with(viewModel) {
        super.observeData()

        categories.observe(lifecycleOwner, Observer {
            categoryAdapter.updateData(it)
            Log.d("VocabularyFragment", "categories = $it")
        })
    }

}

