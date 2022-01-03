package com.deathhit.myleetcodesolutions

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.deathhit.framework.toolbox.StateActivity
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import com.deathhit.myleetcodesolutions.question_list.QuestionListFragment

class MainActivity : StateActivity<MainActivityViewModel.State, MainActivityViewModel>() {
    companion object {
        private const val TAG = "MainActivity"
        private const val TAG_QUESTION_LIST = "$TAG.TAG_QUESTION_LIST"
        private const val ID_QUESTION_LIST_CONTAINER = R.id.activity_questionListContainer
        private const val LAYOUT = R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        savedInstanceState ?: viewModel.addQuestionListFragment()
    }

    override fun createViewModel(savedInstanceState: Bundle?): MainActivityViewModel {
        val viewModel: MainActivityViewModel by viewModels()
        return viewModel
    }

    override fun onFragmentAttach(fragment: Fragment) {
        if (fragment is QuestionListFragment) {
            observeState(fragment) { state ->
                state.eventGoToQuestionActivity.signForEvent(this)?.let {
                    viewModel.goToQuestionActivity(it)
                }
            }
        }
    }

    override fun onRenderState(state: MainActivityViewModel.State) {
        state.eventAddQuestionListFragment.signForEvent(this)?.let {
            addQuestionListFragment()
        }
        state.eventGoToQuestionActivity.signForEvent(this)?.let {
            goToQuestionActivity(it)
        }
    }

    private fun addQuestionListFragment() {
        supportFragmentManager.beginTransaction()
            .add(ID_QUESTION_LIST_CONTAINER, QuestionListFragment.create(), TAG_QUESTION_LIST)
            .commit()
    }

    private fun goToQuestionActivity(questionVO: QuestionVO) {
        startActivity(QuestionActivity.createIntent(this, questionVO))
    }
}