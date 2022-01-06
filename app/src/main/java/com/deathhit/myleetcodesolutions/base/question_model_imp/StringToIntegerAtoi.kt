package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

class StringToIntegerAtoi(context: Context) : QuestionModel(context) {
    companion object {
        private const val STRING_CODE = R.string.string_to_integer_atoi_code
        private const val STRING_DESCRIPTION = R.string.string_to_integer_atoi_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_S_X = R.string.common_s_x
    }

    override fun code(): Spanned = HtmlCompat.fromHtml(
        context.getString(STRING_CODE),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )

    override fun description(): String = context.getString(STRING_DESCRIPTION)

    override suspend fun run(): AnswerVO {
        val s = generateS()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_S_X,
                s
            )
        )
        val output = myAtoi(s)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateS(): String {
        val values = listOf(
            "42",
            " +42",
            "   -42",
            "4193+++ with words",
            "words with 3914---",
            "-91283472332",
            "91283472332"
        )
        return values.random()
    }

    private fun myAtoi(s: String): Int {
        var pointer = s.indexOfFirst { it != ' ' }
        if (pointer == -1)
            return 0

        var factor = 1
        when {
            s[pointer] == '-' -> {
                factor = -1
                pointer++
            }
            s[pointer] == '+' ->
                pointer++
        }

        val firstDigitPointer = pointer
        var result = 0
        while (pointer < s.length) {
            if (!s[pointer].isDigit())
                return result
            val digit = s[pointer].toString().toInt() * factor
            if (pointer - firstDigitPointer + 1 > 9) {    //9 is the number of digits - 1 of Int.MAX_VALUE and Int.MIN_VALUE
                if (result < 0 && result < (Int.MIN_VALUE - digit) / 10)
                    return Int.MIN_VALUE
                else if (result > 0 && result > (Int.MAX_VALUE - digit) / 10)
                    return Int.MAX_VALUE
            }
            result = result * 10 + digit
            pointer++
        }
        return result
    }
}