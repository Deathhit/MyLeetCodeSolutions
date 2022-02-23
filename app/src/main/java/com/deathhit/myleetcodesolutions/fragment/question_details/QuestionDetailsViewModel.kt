package com.deathhit.myleetcodesolutions.fragment.question_details

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deathhit.lib_state_package.StatePackage
import com.deathhit.lib_state_package.Status
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionDetailsViewModel @Inject constructor(
    private val application: Application,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val TAG =
            "com.deathhit.myleetcodesolutions.fragment.question_details.QuestionDetailsViewModel"
        const val KEY_QUESTION_VO = "$TAG.KEY_QUESTION_VO"
    }

    data class State(
        val attrQuestionVO: QuestionVO,
        val statusCode: Status<Spanned>,
        val statusDescription: Status<String>,
        val statusInput: Status<String>,
        val statusOutput: Status<String>,
        val statusTitle: Status<String>
    )

    private val _stateFlow = MutableStateFlow(
        State(
            savedStateHandle[KEY_QUESTION_VO]!!,
            StatePackage(),
            StatePackage(),
            StatePackage(),
            StatePackage(),
            StatePackage()
        )
    )
    val stateFlow = _stateFlow.asStateFlow()

    private val questionModel by lazy {
        stateFlow.value.attrQuestionVO.createQuestionModel(
            application
        )
    }

    private var jobRun: Job? = null

    init {
        run()
        showDetails()
    }

    fun run() {
        jobRun?.cancel()
        jobRun = viewModelScope.launch {
            val answerVO = withContext(Dispatchers.Default) { questionModel.run() }
            _stateFlow.update { state ->
                state.copy(
                    statusInput = StatePackage(answerVO.inputText),
                    statusOutput = StatePackage(answerVO.outputText)
                )
            }
        }
    }

    fun saveState() {
        savedStateHandle.set(KEY_QUESTION_VO, stateFlow.value.attrQuestionVO)
    }

    private fun showDetails() {
        _stateFlow.update { state ->
            state.copy(
                statusCode = StatePackage(questionModel.code),
                statusDescription = StatePackage(questionModel.description),
                statusTitle = StatePackage(state.attrQuestionVO.name)
            )
        }
    }
}