package com.deathhit.myleetcodesolutions.fragment.question_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class QuestionDetailsFragment : Fragment() {
    companion object {
        private const val ID_BTN_RUN = R.id.button_run
        private const val ID_TEXT_CODE = R.id.textView_code
        private const val ID_TEXT_DESCRIPTION = R.id.textView_description
        private const val ID_TEXT_INPUT = R.id.textView_input
        private const val ID_TEXT_OUTPUT = R.id.textView_output
        private const val ID_TEXT_TITLE = R.id.textView_title
        private const val LAYOUT = R.layout.fragment_question_details

        fun create(questionVO: QuestionVO): QuestionDetailsFragment {
            val args = Bundle()
            args.putParcelable(QuestionDetailsViewModel.KEY_QUESTION_VO, questionVO)
            val fragment = QuestionDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: QuestionDetailsViewModel by viewModels()

    private val onRunListener: View.OnClickListener = View.OnClickListener {
        viewModel.run()
    }

    private var btnRun: Button? = null
    private var textCode: TextView? = null
    private var textDescription: TextView? = null
    private var textInput: TextView? = null
    private var textOutput: TextView? = null
    private var textTitle: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LAYOUT, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRun = view.findViewById(ID_BTN_RUN)
        textCode = view.findViewById(ID_TEXT_CODE)
        textDescription = view.findViewById(ID_TEXT_DESCRIPTION)
        textInput = view.findViewById(ID_TEXT_INPUT)
        textOutput = view.findViewById(ID_TEXT_OUTPUT)
        textTitle = view.findViewById(ID_TEXT_TITLE)

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect { state ->
                state.statusCode.signForViewStatus(this@QuestionDetailsFragment) { textCode?.text = it }
                state.statusDescription.signForViewStatus(this@QuestionDetailsFragment) { textDescription?.text = it }
                state.statusInput.signForViewStatus(this@QuestionDetailsFragment) { textInput?.text = it }
                state.statusOutput.signForViewStatus(this@QuestionDetailsFragment) { textOutput?.text = it }
                state.statusTitle.signForViewStatus(this@QuestionDetailsFragment) { textTitle?.text = it }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        btnRun?.setOnClickListener(onRunListener)
    }

    override fun onPause() {
        super.onPause()
        btnRun?.setOnClickListener(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        btnRun = null
        textCode = null
        textDescription = null
        textInput = null
        textOutput = null
        textTitle = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }
}