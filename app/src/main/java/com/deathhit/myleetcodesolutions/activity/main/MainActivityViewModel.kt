package com.deathhit.myleetcodesolutions.activity.main

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
class MainActivityViewModel @Inject constructor() : ViewModel() {
    data class State(
        val eventAddQuestionListFragment: Event<Unit>,
        val eventGoToQuestionActivity: Event<QuestionVO>
    )

    private val _stateFlow = MutableStateFlow(State(StatePackage(), StatePackage()))
    val stateFlow = _stateFlow.asStateFlow()

    fun addQuestionListFragment() {
        _stateFlow.update { state ->
            state.copy(eventAddQuestionListFragment = StatePackage(Unit))
        }
    }

    fun goToQuestionActivity(questionVO: QuestionVO) {
        _stateFlow.update { state ->
            state.copy(eventGoToQuestionActivity = StatePackage(questionVO))
        }
    }
}