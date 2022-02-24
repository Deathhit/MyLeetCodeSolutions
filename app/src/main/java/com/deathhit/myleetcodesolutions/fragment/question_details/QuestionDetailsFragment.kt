package com.deathhit.myleetcodesolutions.fragment.question_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.deathhit.myleetcodesolutions.databinding.FragmentQuestionDetailsBinding
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class QuestionDetailsFragment : Fragment() {
    companion object {
        fun create(questionVO: QuestionVO): QuestionDetailsFragment {
            val args = Bundle()
            args.putParcelable(QuestionDetailsViewModel.KEY_QUESTION_VO, questionVO)
            val fragment = QuestionDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val onRunListener: View.OnClickListener = View.OnClickListener {
        viewModel.run()
    }

    private val binding: FragmentQuestionDetailsBinding get() = _binding!!
    private var _binding: FragmentQuestionDetailsBinding? = null

    private val viewModel: QuestionDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect { state ->
                state.statusCode.signForViewStatus(this@QuestionDetailsFragment) {
                    binding.textViewCode.text = it
                }
                state.statusDescription.signForViewStatus(this@QuestionDetailsFragment) {
                    binding.textViewDescription.text = it
                }
                state.statusInput.signForViewStatus(this@QuestionDetailsFragment) {
                    binding.textViewInput.text = it
                }
                state.statusOutput.signForViewStatus(this@QuestionDetailsFragment) {
                    binding.textViewOutput.text = it
                }
                state.statusTitle.signForViewStatus(this@QuestionDetailsFragment) {
                    binding.textViewTitle.text = it
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.buttonRun.setOnClickListener(onRunListener)
    }

    override fun onPause() {
        super.onPause()
        binding.buttonRun.setOnClickListener(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }
}