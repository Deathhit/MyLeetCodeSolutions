package com.deathhit.myleetcodesolutions

import android.app.Application
import com.deathhit.framework.Event
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.myleetcodesolutions.base.enum_type.Question
import com.deathhit.myleetcodesolutions.base.model.QuestionVO
import java.lang.RuntimeException

class QuestionActivityViewModel(application: Application) : StateViewModel<QuestionActivityViewModel.State>(
    application
) {
    class State(val eventAddTwoSumFragment: Event<Unit>)

    private val eventAddTwoSumFragment = StatePackage<Unit>()

    var questionVO: QuestionVO? = null

    override fun createState(): State = State(eventAddTwoSumFragment)

    fun addTheTargetFragment() {
        when(questionVO?.question) {
            Question.TWO_SUM -> eventAddTwoSumFragment.content = Unit
            else -> throw RuntimeException("Unexpected question!")
        }
        postState()
    }
}