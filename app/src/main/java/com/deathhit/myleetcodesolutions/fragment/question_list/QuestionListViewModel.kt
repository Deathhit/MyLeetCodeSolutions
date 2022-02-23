package com.deathhit.myleetcodesolutions.fragment.question_list

import android.app.Application
import androidx.lifecycle.ViewModel
import com.deathhit.lib_state_package.Event
import com.deathhit.lib_state_package.StatePackage
import com.deathhit.lib_state_package.Status
import com.deathhit.myleetcodesolutions.enum_type.Question
import com.deathhit.myleetcodesolutions.model.QuestionVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(private val application: Application) :
    ViewModel() {
    data class State(
        val eventGoToQuestionActivity: Event<QuestionVO>,
        val statusQuestionList: Status<List<QuestionVO>>
    )

    private val _stateFlow = MutableStateFlow(State(StatePackage(), StatePackage()))
    val stateFlow = _stateFlow.asStateFlow()

    init {
        loadQuestionList()
    }

    fun goToQuestionActivity(questionVO: QuestionVO) {
        _stateFlow.update { state ->
            state.copy(eventGoToQuestionActivity = StatePackage(questionVO))
        }
    }

    private fun loadQuestionList() {
        _stateFlow.update { state ->
            state.copy(
                statusQuestionList = StatePackage(
                    Question.values().asList().map { QuestionVO.valueOf(application, it) })
            )
        }
    }
}