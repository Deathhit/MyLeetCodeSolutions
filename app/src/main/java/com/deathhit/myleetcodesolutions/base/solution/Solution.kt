package com.deathhit.myleetcodesolutions.base.solution

import android.app.Application
import android.text.Spanned
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

abstract class Solution(val application: Application) {
    val code by lazy { code() }
    val description by lazy { description() }

    abstract fun run(): AnswerVO
    protected abstract fun code(): Spanned
    protected abstract fun description(): String
}