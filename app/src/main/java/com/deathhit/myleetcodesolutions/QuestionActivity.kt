package com.deathhit.myleetcodesolutions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.deathhit.framework.toolbox.StateActivity
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import com.deathhit.myleetcodesolutions.question_details.QuestionDetailsFragment

class QuestionActivity :
    StateActivity<QuestionActivityViewModel.State, QuestionActivityViewModel>() {
    companion object {
        private const val TAG = "QuestionActivity"
        private const val KEY_QUESTION_VO = "$TAG.KEY_QUESTION_VO"
        private const val TAG_QUESTION_DETAILS_FRAGMENT = "$TAG.TAG_QUESTION_DETAILS_FRAGMENT"

        private const val ID_CONTAINER = R.id.activity_frameLayout_container
        private const val LAYOUT = R.layout.activity_question

        fun createIntent(context: Context, questionVO: QuestionVO): Intent {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra(KEY_QUESTION_VO, questionVO)
            return intent
        }
    }

    override fun createViewModel(args: Bundle): QuestionActivityViewModel {
        val viewModel: QuestionActivityViewModel by viewModels()
        viewModel.questionVO = args.getParcelable(KEY_QUESTION_VO)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        viewModel.getStateLiveData().observe(this, { state ->
            state.eventAddQuestionDetailsFragment.signForEvent(this)
                ?.let { addAddQuestionDetailsFragment(it) }
        })

        savedInstanceState ?: viewModel.addQuestionDetailsFragment()
    }

    override fun onFragmentAttach(fragment: Fragment) {

    }

    override fun onSaveViewModelArgs(args: Bundle) {
        args.putParcelable(KEY_QUESTION_VO, viewModel.questionVO)
    }

    private fun addAddQuestionDetailsFragment(questionVO: QuestionVO) {
        supportFragmentManager.beginTransaction()
            .add(
                ID_CONTAINER,
                QuestionDetailsFragment.create(questionVO),
                TAG_QUESTION_DETAILS_FRAGMENT
            )
            .commit()
    }
}