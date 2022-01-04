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
        private const val STRING_LONGEST_PALINDROMIC_SUBSTRING =
            R.string.question_vo_longest_palindromic_substring
        private const val STRING_LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS =
            R.string.question_vo_longest_substring_without_repeating_characters
        private const val STRING_MEDIAN_OF_TWO_SORTED_ARRAYS =
            R.string.question_vo_median_of_two_sorted_arrays
        private const val STRING_TWO_SUM = R.string.question_vo_two_sum

        fun valueOf(context: Context, question: Question): QuestionVO {
            return when (question) {
                Question.ADD_TWO_NUMBERS -> QuestionVO(
                    context.getString(STRING_ADD_TWO_NUMBERS),
                    question
                )
                Question.LONGEST_PALINDROMIC_SUBSTRING -> QuestionVO(
                    context.getString(STRING_LONGEST_PALINDROMIC_SUBSTRING),
                    question
                )
                Question.LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS -> QuestionVO(
                    context.getString(
                        STRING_LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS
                    ), question
                )
                Question.MEDIAN_OF_TWO_SORTED_ARRAYS -> QuestionVO(
                    context.getString(
                        STRING_MEDIAN_OF_TWO_SORTED_ARRAYS
                    ), question
                )
                Question.TWO_SUM -> QuestionVO(context.getString(STRING_TWO_SUM), question)
            }
        }
    }
}