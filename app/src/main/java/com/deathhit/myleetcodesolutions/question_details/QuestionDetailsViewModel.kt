package com.deathhit.myleetcodesolutions.question_details

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.viewModelScope
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.base.enum_type.Question
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import com.deathhit.myleetcodesolutions.base.question_model.*
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

    private val questionModel by lazy { createQuestionModel() }

    private val statusCode = StatePackage<Spanned>()
    private val statusDescription = StatePackage<String>()
    private val statusInput = StatePackage<String>()
    private val statusOutput = StatePackage<String>()
    private val statusTitle = StatePackage<String>()

    var jobRun: Job? = null

    var questionVO: QuestionVO? = null

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

    private fun createQuestionModel(): QuestionModel {
        return when (questionVO!!.question) {
            Question.ADD_TWO_NUMBERS -> AddTwoNumbers(getApplication())
            Question.LONGEST_PALINDROMIC_SUBSTRING -> LongestPalindromicSubstring(getApplication())
            Question.LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS -> LongestSubstringWithoutRepeatingCharacters(
                getApplication()
            )
            Question.MEDIAN_OF_TWO_SORTED_ARRAYS -> MedianOfTwoSortedArrays(getApplication())
            Question.TWO_SUM -> TwoSum(getApplication())
            Question.ZIGZAG_CONVERSION -> ZigzagConversion(getApplication())
        }
    }

    private fun showDetails() {
        statusCode.content = questionModel.code
        statusDescription.content = questionModel.description
        statusTitle.content = questionVO?.name
        postState()
    }
}