package com.deathhit.myleetcodesolutions.question_model

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.AnswerVO
import kotlin.random.Random

class LongestPalindromicSubstring(context: Context) : QuestionModel(context) {
    companion object {
        private const val MAX_LENGTH_OF_S = 15
        private const val MIN_LENGTH_OF_S = 0

        private const val STRING_CODE = R.string.longest_palindromic_substring_code
        private const val STRING_DESCRIPTION =
            R.string.longest_palindromic_substring_description
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
        val inputText = context.getString(STRING_INPUT_X, context.getString(STRING_S_X, s))
        val output = longestPalindrome(s)
        val outputText = context.getString(STRING_OUTPUT_X, output)
        return AnswerVO(inputText, outputText)
    }

    private fun generateS(): String {
        val alphabet: List<Char> = ('a'..'z').toList()
        val length = Random.nextInt(
            MIN_LENGTH_OF_S,
            MAX_LENGTH_OF_S
        )
        return List(length) { alphabet.random() }.joinToString("")
    }

    private fun longestPalindrome(s: String): String {
        val fillChar = '_'
        val filledStr = StringBuilder(s.length * 2 + 1).apply {
            for (i in s.indices)
                append(fillChar).append(s[i])
            append(fillChar)
        }
        val radiusArray = Array(filledStr.length) { 0 }

        var center = 0
        var maxCenter = 0
        var maxRadius = 0
        var radius = 0
        while (center < filledStr.length) {
            var nextRadius = radius + 1
            while (center - nextRadius >= 0
                && center + nextRadius < filledStr.length
                && filledStr[center + nextRadius] == filledStr[center - nextRadius]
            ) {
                radius = nextRadius
                nextRadius++
            }

            radiusArray[center] = radius
            if (radiusArray[center] > maxRadius) {
                maxCenter = center
                maxRadius = radiusArray[center]
            }

            val middleCenter = center
            val rightBound = center + radius

            center++
            radius = 0
            while (center <= rightBound) {
                val leftCenter = middleCenter - (center - middleCenter)
                val reach = center + radiusArray[leftCenter]
                if (reach < rightBound)
                    radiusArray[center] = radiusArray[leftCenter]
                else if (reach > rightBound)
                    radiusArray[center] = rightBound - center
                else {
                    radius = rightBound - center
                    break
                }
                center++
            }
        }

        return filledStr.substring(maxCenter - maxRadius, maxCenter + maxRadius)
            .filter { it != fillChar }
    }
}