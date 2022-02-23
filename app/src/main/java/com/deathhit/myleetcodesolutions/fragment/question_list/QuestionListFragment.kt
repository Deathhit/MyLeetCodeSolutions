package com.deathhit.myleetcodesolutions.fragment.question_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class QuestionListFragment : Fragment() {
    companion object {
        private const val ID_RECYCLER_VIEW = R.id.recyclerView
        private const val LAYOUT = R.layout.fragment_question_list

        fun create(): QuestionListFragment {
            val args = Bundle()
            val fragment = QuestionListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: QuestionListViewModel by viewModels()

    private var recyclerView: RecyclerView? = null

    private var questionAdapter: QuestionAdapter? = null

    private var onStateListener: ((QuestionListViewModel.State) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LAYOUT, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(ID_RECYCLER_VIEW).apply {
            setHasFixedSize(true)
            questionAdapter = createQuestionAdapter().also { adapter = it }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect { state ->
                state.statusQuestionList.signForViewStatus(this@QuestionListFragment) {
                    questionAdapter?.submitList(it)
                }

                onStateListener?.invoke(state)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null

        questionAdapter = null
    }

    fun setStateListener(onStateListener: ((QuestionListViewModel.State) -> Unit)?) {
        this.onStateListener = onStateListener
    }

    private fun createQuestionAdapter(): QuestionAdapter {
        return object : QuestionAdapter() {
            override fun onItemClick(item: QuestionVO, pos: Int) {
                viewModel.goToQuestionActivity(item)
            }
        }
    }
}