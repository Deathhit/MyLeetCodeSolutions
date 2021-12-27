package com.deathhit.myleetcodesolutions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.deathhit.framework.toolbox.StateActivity
import com.deathhit.myleetcodesolutions.add_two_numbers.AddTwoNumbersFragment
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import com.deathhit.myleetcodesolutions.two_sum.TwoSumFragment

class QuestionActivity :
    StateActivity<QuestionActivityViewModel.State, QuestionActivityViewModel>() {
    companion object {
        private const val TAG = "QuestionActivity"
        private const val KEY_QUESTION_VO = "$TAG.KEY_QUESTION_VO"
        private const val TAG_ADD_TWO_NUMBERS_FRAGMENT = "$TAG.TAG_ADD_TWO_NUMBERS_FRAGMENT"
        private const val TAG_TWO_SUM_FRAGMENT = "$TAG.TAG_TWO_SUM_FRAGMENT"

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
            state.eventAddAddTwoNumbersFragment.signForEvent(this)?.let { addAddTwoNumbersFragment() }
            state.eventAddTwoSumFragment.signForEvent(this)?.let { addTwoSumFragment() }
        })

        savedInstanceState ?: viewModel.addTheTargetFragment()
    }

    override fun onFragmentAttach(fragment: Fragment) {

    }

    override fun onSaveViewModelArgs(args: Bundle) {
        args.putParcelable(KEY_QUESTION_VO, viewModel.questionVO)
    }

    private fun addAddTwoNumbersFragment() {
        supportFragmentManager.beginTransaction()
            .add(ID_CONTAINER, AddTwoNumbersFragment.create(), TAG_ADD_TWO_NUMBERS_FRAGMENT)
            .commit()
    }

    private fun addTwoSumFragment() {
        supportFragmentManager.beginTransaction()
            .add(ID_CONTAINER, TwoSumFragment.create(), TAG_TWO_SUM_FRAGMENT).commit()
    }
}