package com.deathhit.myleetcodesolutions.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentOnAttachListener
import androidx.lifecycle.lifecycleScope
import com.deathhit.myleetcodesolutions.activity.question.QuestionActivity
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.databinding.ActivityMainBinding
import com.deathhit.myleetcodesolutions.model.QuestionVO
import com.deathhit.myleetcodesolutions.fragment.question_list.QuestionListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "com.deathhit.myleetcodesolutions.activity.main.MainActivity"
        private const val TAG_QUESTION_LIST = "$TAG.TAG_QUESTION_LIST"

        private const val ID_QUESTION_LIST_CONTAINER = R.id.activity_questionListContainer
    }

    private val fragmentOnAttachListener: FragmentOnAttachListener =
        FragmentOnAttachListener { _, fragment ->
            onFragmentAttach(
                fragment
            )
        }

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.addFragmentOnAttachListener(fragmentOnAttachListener)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState ?: viewModel.addQuestionListFragment()

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect { state ->
                state.eventAddQuestionListFragment.signForEvent(this@MainActivity) {
                    addQuestionListFragment()
                }

                state.eventGoToQuestionActivity.signForEvent(this@MainActivity) {
                    goToQuestionActivity(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeFragmentOnAttachListener(fragmentOnAttachListener)
    }

    private fun addQuestionListFragment() {
        supportFragmentManager.beginTransaction()
            .add(ID_QUESTION_LIST_CONTAINER, QuestionListFragment.create(), TAG_QUESTION_LIST)
            .commit()
    }

    private fun goToQuestionActivity(questionVO: QuestionVO) {
        startActivity(QuestionActivity.createIntent(this, questionVO))
    }

    private fun onFragmentAttach(fragment: Fragment) {
        if (fragment is QuestionListFragment) {
            fragment.setStateListener { state ->
                state.eventGoToQuestionActivity.signForEvent(this) {
                    viewModel.goToQuestionActivity(it)
                }
            }
        }
    }
}