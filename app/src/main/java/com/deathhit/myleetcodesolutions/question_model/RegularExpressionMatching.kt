package com.deathhit.myleetcodesolutions.question_model

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.AnswerVO

class RegularExpressionMatching(context: Context) : QuestionModel(context) {
    companion object {
        private const val STRING_CODE = R.string.regular_expression_matching_code
        private const val STRING_DESCRIPTION = R.string.regular_expression_matching_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_P_X = R.string.common_p_x
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
        val p = generateP()
        val s = generateS()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(STRING_S_X, s) + ", " + context.getString(STRING_P_X, p)
        )
        val output = isMatch(s, p)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateP(): String {
        val values = listOf(
            "a",
            "a*",
            ".*"
        )
        return values.random()
    }

    private fun generateS(): String {
        val values = listOf(
            "aa",
            "ab"
        )
        return values.random()
    }

    private fun isMatch(s: String, p: String): Boolean {
        if (s.isNotEmpty() && p.isEmpty())
            return false

        val dp = Array(s.length + 1) { BooleanArray(p.length + 1) }
        dp[0][0] = true

        for (i in 1 until p.length + 1)
            if (p[i - 1] == '*')
                dp[0][i] = dp[0][i - 2]

        for (i in 1 until s.length + 1) {
            for (j in 1 until p.length + 1) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1]
                else if (p[j - 1] == '*') {
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.')
                        dp[i][j] = dp[i - 1][j]
                    dp[i][j] = dp[i][j] || dp[i][j - 2]
                }
            }
        }

        return dp[s.length][p.length]
    }

    /*
    private fun isMatch(s: String, p: String): Boolean {
        return isMatch(s, p, s.length - 1, p.length -1)
    }

    private fun isMatch(s: String, p: String, sPointer: Int, pPointer: Int): Boolean {
        when {
            sPointer < 0 -> {
                return when {
                    pPointer < 0 -> true
                    pPointer >= 0 && p[pPointer] == '*' -> isMatch(s, p, sPointer, pPointer - 2)
                    else -> false
                }
            }
            pPointer < 0 -> return false
        }

        if (s[sPointer] == p[pPointer] || p[pPointer] == '.')
            return isMatch(s, p, sPointer - 1, pPointer - 1)
        else if (p[pPointer] == '*') {
            var isReduced = false
            if (s[sPointer] == p[pPointer - 1] || p[pPointer - 1] == '.')
                isReduced = isMatch(s, p, sPointer - 1, pPointer)
            return isReduced || isMatch(s, p, sPointer, pPointer - 2)
        }

        return false
    }
     */
}