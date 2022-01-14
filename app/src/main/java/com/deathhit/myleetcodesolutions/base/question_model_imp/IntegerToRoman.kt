package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class IntegerToRoman(context: Context) : QuestionModel(context) {
    companion object {
        private const val MAX_VALUE_OF_NUM = 9999

        private const val STRING_CODE = R.string.integer_to_roman_code
        private const val STRING_DESCRIPTION = R.string.integer_to_roman_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_NUM_X = R.string.common_num_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
    }

    override val code: Spanned by lazy {
        HtmlCompat.fromHtml(
            context.getString(STRING_CODE),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    override val description: String by lazy { context.getString(STRING_DESCRIPTION) }

    override fun run(): AnswerVO {
        val num = generateNum()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_NUM_X,
                num.toString()
            )
        )
        val output = intToRoman(num)
        val outputText =
            context.getString(STRING_OUTPUT_X, output)
        return AnswerVO(inputText, outputText)
    }

    private fun generateNum(): Int = Random.nextInt(MAX_VALUE_OF_NUM + 1)

    private fun intToRoman(num: Int): String {
        val strArray =
            arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val valueArray = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val sb = StringBuilder()
        var tempNum = num
        valueArray.forEachIndexed { index, value ->
            val n = tempNum / value
            repeat(n) {
                sb.append(strArray[index])
            }
            tempNum %= value
        }
        return sb.toString()
    }
}