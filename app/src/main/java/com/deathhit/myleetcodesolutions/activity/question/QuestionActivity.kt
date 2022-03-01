package com.deathhit.myleetcodesolutions.activity.question

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.databinding.ActivityQuestionBinding
import com.deathhit.myleetcodesolutions.model.QuestionVO
import com.deathhit.myleetcodesolutions.fragment.question_details.QuestionDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionActivity : AppCompatActivity() {
    companion object {
        private const val TAG =
            "com.deathhit.myleetcodesolutions.activity.question.QuestionActivity"
        private const val TAG_QUESTION_DETAILS_FRAGMENT = "$TAG.TAG_QUESTION_DETAILS_FRAGMENT"

        private const val ID_CONTAINER = R.id.activity_frameLayout_container

        fun createIntent(context: Context, questionVO: QuestionVO): Intent {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra(QuestionActivityViewModel.KEY_QUESTION_VO, questionVO)
            return intent
        }
    }

    private val viewModel: QuestionActivityViewModel by viewModels()

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState ?: viewModel.addQuestionDetailsFragment()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    state.eventAddQuestionDetailsFragment.signForEvent(this@QuestionActivity) {
                        addAddQuestionDetailsFragment(it)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
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