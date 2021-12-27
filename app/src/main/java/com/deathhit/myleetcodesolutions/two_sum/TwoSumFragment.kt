package com.deathhit.myleetcodesolutions.two_sum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.deathhit.framework.toolbox.StateFragment
import com.deathhit.myleetcodesolutions.R

class TwoSumFragment : StateFragment<TwoSumViewModel.State, TwoSumViewModel>() {
    companion object {
        private const val ID_BTN_RUN = R.id.button_run
        private const val ID_TEXT_CODE = R.id.textView_code
        private const val ID_TEXT_INPUT = R.id.textView_input
        private const val ID_TEXT_OUTPUT = R.id.textView_output
        private const val LAYOUT = R.layout.fragment_two_sum

        fun create(): TwoSumFragment = TwoSumFragment()
    }

    private val onRunListener: View.OnClickListener = View.OnClickListener {
        viewModel.run()
    }

    private var btnRun: Button? = null
    private var textCode: TextView? = null
    private var textInput: TextView? = null
    private var textOutput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.run()
    }

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
        textInput = view.findViewById(ID_TEXT_INPUT)
        textOutput = view.findViewById(ID_TEXT_OUTPUT)

        viewModel.getStateLiveData().observe(viewLifecycleOwner, { state ->
            state.statusCode.signForStatus(this)?.let { textCode?.text = it }
            state.statusInput.signForStatus(this)?.let { textInput?.text = it }
            state.statusOutput.signForStatus(this)?.let { textOutput?.text = it }
        })

        viewModel.showCode()
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
        textInput = null
        textOutput = null
    }

    override fun createViewModel(args: Bundle): TwoSumViewModel {
        val viewModel: TwoSumViewModel by viewModels()
        return viewModel
    }

    override fun onSaveViewModelArgs(args: Bundle) {

    }
}