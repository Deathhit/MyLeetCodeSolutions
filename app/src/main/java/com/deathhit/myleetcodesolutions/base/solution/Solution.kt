package com.deathhit.myleetcodesolutions.base.solution

import android.app.Application
import android.text.Spanned
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

abstract class Solution(val application: Application) {
    abstract fun getCode(): Spanned
    abstract fun getDescription(): String
    abstract fun run(): AnswerVO
}