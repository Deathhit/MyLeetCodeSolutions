package com.deathhit.myleetcodesolutions.activity.question

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.deathhit.lib_state_package.Event
import com.deathhit.lib_state_package.StatePackage
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionActivityViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    companion object {
        private const val TAG =
            "com.deathhit.myleetcodesolutions.activity.question.QuestionActivityViewModel"
        const val KEY_QUESTION_VO = "$TAG.KEY_QUESTION_VO"
    }

    data class State(
        val attrQuestionVO: QuestionVO,
        val eventAddQuestionDetailsFragment: Event<QuestionVO>
    )

    private val _stateFlow =
        MutableStateFlow(State(savedStateHandle[KEY_QUESTION_VO]!!, StatePackage()))
    val stateFlow = _stateFlow.asStateFlow()

    fun addQuestionDetailsFragment() {
        _stateFlow.update { state ->
            state.copy(eventAddQuestionDetailsFragment = StatePackage(state.attrQuestionVO))
        }
    }

    fun saveState() {
        savedStateHandle.set(KEY_QUESTION_VO, stateFlow.value.attrQuestionVO)
    }
}