package com.deathhit.myleetcodesolutions.question_details

import android.app.Application
import android.text.Spanned
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.base.enum_type.Question
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import com.deathhit.myleetcodesolutions.base.solution.AddTwoNumbersSolution
import com.deathhit.myleetcodesolutions.base.solution.Solution
import com.deathhit.myleetcodesolutions.base.solution.TwoSumSolution

class QuestionDetailsViewModel(application: Application) :
    StateViewModel<QuestionDetailsViewModel.State>(application) {
    class State(
        val statusCode: Status<Spanned>,
        val statusDescription: Status<String>,
        val statusInput: Status<String>,
        val statusOutput: Status<String>,
        val statusTitle: Status<String>
    )

    private val solution by lazy { createSolution() }

    private val statusCode = StatePackage<Spanned>()
    private val statusDescription = StatePackage<String>()
    private val statusInput = StatePackage<String>()
    private val statusOutput = StatePackage<String>()
    private val statusTitle = StatePackage<String>()

    var questionVO: QuestionVO? = null

    override fun createState(): State =
        State(statusCode, statusDescription, statusInput, statusOutput, statusTitle)

    fun run() {
        val answerVO = solution.run()
        statusInput.content = answerVO.inputText
        statusOutput.content = answerVO.outputText
        postState()
    }

    fun showDetails() {
        statusCode.content = solution.getCode()
        statusDescription.content = solution.getDescription()
        statusTitle.content = questionVO?.name
        postState()
    }

    private fun createSolution(): Solution{
        return when(questionVO!!.question) {
            Question.ADD_TWO_NUMBERS -> AddTwoNumbersSolution(getApplication())
            Question.TWO_SUM -> TwoSumSolution(getApplication())
        }
    }
}