package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO

class RomanToInteger(context: Context) : QuestionModel(context) {
    companion object {
        private const val STRING_CODE = R.string.roman_to_integer_code
        private const val STRING_DESCRIPTION = R.string.roman_to_integer_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_S_X = R.string.common_s_x
    }

    override val code: Spanned by lazy {
        HtmlCompat.fromHtml(
            context.getString(STRING_CODE),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    override val description: String by lazy { context.getString(STRING_DESCRIPTION) }

    override fun run(): AnswerVO {
        val s = generateS()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(STRING_S_X, s)
        )
        val output = romanToInt(s)
        val outputText =
            context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateS(): String {
        val values = listOf(
            "CMXCIX",
            "CDXLIV",
            "III",
            "LVIII",
            "MCMXCIV",
            "MDCLXVI",
            "MMMDCCCLXXXVIII"
        )
        return values.random()
    }

    private fun romanToInt(s: String): Int {
        val map = mapOf(
            'M' to 1000,
            'D' to 500,
            'C' to 100,
            'L' to 50,
            'X' to 10,
            'V' to 5,
            'I' to 1
        )
        var previousValue = 0
        var result = 0
        for (i in s.length - 1 downTo 0) {
            val currentValue = map[s[i]]!!
            when {
                currentValue >= previousValue -> {
                    result += currentValue
                    previousValue = currentValue

                }
                else -> result -= currentValue
            }
        }
        return result
    }
}