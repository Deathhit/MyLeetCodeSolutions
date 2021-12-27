package com.deathhit.myleetcodesolutions.base.model

import android.content.Context
import android.os.Parcelable
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.enum_type.Question
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionVO(val name: String, val question: Question) : Parcelable {
    companion object {
        private const val STRING_ADD_TWO_NUMBERS = R.string.question_vo_add_two_numbers
        private const val STRING_TWO_SUM = R.string.question_vo_two_sum

        fun valueOf(context: Context, question: Question): QuestionVO {
            return when(question) {
                Question.ADD_TWO_NUMBERS -> QuestionVO(context.getString(STRING_ADD_TWO_NUMBERS), question)
                Question.TWO_SUM -> QuestionVO(context.getString(STRING_TWO_SUM), question)
            }
        }
    }
}