package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class PalindromeNumber(context: Context) : QuestionModel(context) {
    companion object {
        private const val STRING_CODE = R.string.palindrome_number_code
        private const val STRING_DESCRIPTION = R.string.palindrome_number_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_X_X = R.string.common_x_x
    }

    override val code: Spanned by lazy {
        HtmlCompat.fromHtml(
            context.getString(STRING_CODE),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    override val description: String by lazy { context.getString(STRING_DESCRIPTION) }

    override fun run(): AnswerVO {
        val x = generateX()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_X_X,
                x.toString()
            )
        )
        val output = isPalindrome(x)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun isPalindrome(x: Int): Boolean {
        var reversedX: Long = 0
        var varX = x
        while (varX > 0) {
            val remainder = varX % 10
            reversedX = reversedX * 10 + remainder
            varX /= 10
        }
        return x.toLong() == reversedX
    }

    private fun generateX(): Int {
        val values = listOf(
            121,
            -121,
            10,
            -10,
            Int.MAX_VALUE,
            Int.MIN_VALUE,
            1234554321,
            -1234554321
        )
        return values.random()
    }
}