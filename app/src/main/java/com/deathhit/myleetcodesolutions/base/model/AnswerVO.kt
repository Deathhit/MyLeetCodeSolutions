package com.deathhit.myleetcodesolutions.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnswerVO(val inputText: String, val outputText: String) : Parcelable