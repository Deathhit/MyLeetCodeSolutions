package com.deathhit.myleetcodesolutions.question_details

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.viewModelScope
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import kotlinx.coroutines.*

class QuestionDetailsViewModel(application: Application) :
    StateViewModel<QuestionDetailsViewModel.State>(application) {
    class State(
        val statusCode: Status<Spanned>,
        val statusDescription: Status<String>,
        val statusInput: Status<String>,
        val statusOutput: Status<String>,
        val statusTitle: Status<String>
    )

    private val questionModel by lazy { questionVO!!.createQuestionModel(application) }

    private val statusCode = StatePackage<Spanned>()
    private val statusDescription = StatePackage<String>()
    private val statusInput = StatePackage<String>()
    private val statusOutput = StatePackage<String>()
    private val statusTitle = StatePackage<String>()

    var questionVO: QuestionVO? = null

    private var jobRun: Job? = null

    override fun createState(): State =
        State(statusCode, statusDescription, statusInput, statusOutput, statusTitle)

    override fun onLoadData() {
        super.onLoadData()
        run()
        showDetails()
    }

    fun run() {
        jobRun?.cancel()
        jobRun = viewModelScope.launch {
            val answerVO = withContext(Dispatchers.Default) { questionModel.run() }
            statusInput.content = answerVO.inputText
            statusOutput.content = answerVO.outputText
            postState()
        }
    }

    private fun showDetails() {
        statusCode.content = questionModel.code
        statusDescription.content = questionModel.description
        statusTitle.content = questionVO?.name
        postState()
    }
}