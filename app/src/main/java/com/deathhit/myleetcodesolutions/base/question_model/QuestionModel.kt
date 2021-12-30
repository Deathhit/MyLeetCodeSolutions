package com.deathhit.myleetcodesolutions.base.question_model

import android.app.Application
import android.text.Spanned
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

abstract class QuestionModel(val application: Application) {
    val code by lazy { code() }
    val description by lazy { description() }

    protected abstract fun code(): Spanned
    protected abstract fun description(): String
    abstract fun run(): AnswerVO
}