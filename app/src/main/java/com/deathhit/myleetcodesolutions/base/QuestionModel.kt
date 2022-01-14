package com.deathhit.myleetcodesolutions.base

import android.content.Context
import android.text.Spanned
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

abstract class QuestionModel(val context: Context) {
    abstract val code: Spanned
    abstract val description: String

    abstract fun run(): AnswerVO
}