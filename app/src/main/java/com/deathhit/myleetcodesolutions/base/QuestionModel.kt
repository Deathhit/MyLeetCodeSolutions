package com.deathhit.myleetcodesolutions.base

import android.content.Context
import android.text.Spanned
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

abstract class QuestionModel(val context: Context) {
    val code by lazy { code() }
    val description by lazy { description() }

    protected abstract fun code(): Spanned
    protected abstract fun description(): String
    abstract suspend fun run(): AnswerVO
}