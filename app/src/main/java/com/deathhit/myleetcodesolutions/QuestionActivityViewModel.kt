package com.deathhit.myleetcodesolutions

import android.app.Application
import com.deathhit.framework.Event
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

class QuestionActivityViewModel(application: Application) :
    StateViewModel<QuestionActivityViewModel.State>(
        application
    ) {
    class State(
        val eventAddQuestionDetailsFragment: Event<QuestionVO>,
    )

    private val eventAddQuestionDetailsFragment = StatePackage<QuestionVO>()

    var questionVO: QuestionVO? = null

    override fun createState(): State = State(eventAddQuestionDetailsFragment)

    fun addQuestionDetailsFragment() {
        eventAddQuestionDetailsFragment.content = questionVO
        postState()
    }
}