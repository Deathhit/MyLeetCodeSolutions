package com.deathhit.myleetcodesolutions.question_list

import android.app.Application
import com.deathhit.framework.Event
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.base.enum_type.Question
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

class QuestionListViewModel(application: Application) :
    StateViewModel<QuestionListViewModel.State>(application) {
    class State(
        val eventGoToQuestionActivity: Event<QuestionVO>,
        val statusQuestionList: Status<List<QuestionVO>>
    )

    private val eventGoToQuestionActivity = StatePackage<QuestionVO>()
    private val statusQuestionList = StatePackage<List<QuestionVO>>()

    override fun createState(): State = State(eventGoToQuestionActivity, statusQuestionList)

    fun goToQuestionActivity(questionVO: QuestionVO) {
        eventGoToQuestionActivity.content = questionVO
        postState()
    }

    fun loadQuestionList() {
        statusQuestionList.content =
            Question.values().asList().map { QuestionVO.valueOf(getApplication(), it) }
        postState()
    }
}