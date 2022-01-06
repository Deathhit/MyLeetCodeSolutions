package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class ReverseInteger(context: Context) : QuestionModel(context) {
    companion object {
        private const val STRING_CODE = R.string.reverse_integer_code
        private const val STRING_DESCRIPTION = R.string.reverse_integer_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_X_X = R.string.common_x_x
    }

    override fun code(): Spanned = HtmlCompat.fromHtml(
        context.getString(STRING_CODE),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )

    override fun description(): String = context.getString(STRING_DESCRIPTION)

    override suspend fun run(): AnswerVO {
        val x = generateX()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_X_X,
                x.toString()
            )
        )
        val output = reverse(x)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateX(): Int = Random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE)

    private fun reverse(x: Int): Int {
        val checkPoint = 9  //Number of digits -1 of Int.MAX_VALUE and Int.MIN_VALUE
        var length = 0
        var result = 0
        var varX = x
        while (varX != 0) {
            val digit = varX % 10
            if (++length > checkPoint
                && ((result < 0 && result < (Int.MIN_VALUE - digit) / 10)
                        || (result > 0 && result > (Int.MAX_VALUE - digit) / 10))
            )
                return 0
            result = result * 10 + digit
            varX /= 10
        }
        return result
    }
}