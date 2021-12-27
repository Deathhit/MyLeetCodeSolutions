package com.deathhit.myleetcodesolutions

import android.app.Application
import com.deathhit.framework.Event
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

class MainActivityViewModel(application: Application) : StateViewModel<MainActivityViewModel.State>(
    application
) {
    class State(
        val eventAddQuestionListFragment: Event<Unit>,
        val eventGoToQuestionActivity: Event<QuestionVO>
    )

    private val eventAddQuestionListFragment = StatePackage<Unit>()
    private val eventGoToQuestionActivity = StatePackage<QuestionVO>()

    override fun createState(): State =
        State(eventAddQuestionListFragment, eventGoToQuestionActivity)

    fun addQuestionListFragment() {
        eventAddQuestionListFragment.content = Unit
        postState()
    }

    fun goToQuestionActivity(questionVO: QuestionVO) {
        eventGoToQuestionActivity.content = questionVO
        postState()
    }
}