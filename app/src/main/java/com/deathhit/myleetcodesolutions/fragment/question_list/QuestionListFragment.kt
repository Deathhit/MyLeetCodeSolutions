package com.deathhit.myleetcodesolutions.fragment.question_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deathhit.myleetcodesolutions.databinding.FragmentQuestionListBinding
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionListFragment : Fragment() {
    companion object {
        fun create(): QuestionListFragment {
            val args = Bundle()
            val fragment = QuestionListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val binding: FragmentQuestionListBinding get() = _binding!!
    private var _binding: FragmentQuestionListBinding? = null

    private val viewModel: QuestionListViewModel by viewModels()

    private var questionAdapter: QuestionAdapter? = null

    private var onStateListener: ((QuestionListViewModel.State) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            questionAdapter = createQuestionAdapter().also { adapter = it }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    state.statusQuestionList.signForViewStatus(this@QuestionListFragment) {
                        questionAdapter?.submitList(it)
                    }

                    onStateListener?.invoke(state)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

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