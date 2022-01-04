package com.deathhit.myleetcodesolutions.base.question_model

import android.app.Application
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class LongestSubstringWithoutRepeatingCharacters(application: Application) : QuestionModel(
    application
) {
    companion object {
        private const val MAX_LENGTH_OF_S = 15
        private const val MIN_LENGTH_OF_S = 0

        private const val STRING_CODE = R.string.longest_substring_without_repeating_characters_code
        private const val STRING_DESCRIPTION =
            R.string.longest_substring_without_repeating_characters_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_S_X = R.string.common_s_x
    }

    override fun code(): Spanned = HtmlCompat.fromHtml(
        application.getString(STRING_CODE),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )

    override fun description(): String = application.getString(STRING_DESCRIPTION)

    override suspend fun run(): AnswerVO {
        val s = generateS()
        val inputText = application.getString(STRING_INPUT_X, application.getString(STRING_S_X, s))
        val output = lengthOfLongestSubstring(s)
        val outputText = application.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateS(): String {
        val alphabet: List<Char> = ('a'..'z').toList()
        val length = Random.nextInt(MIN_LENGTH_OF_S, MAX_LENGTH_OF_S)
        return List(length) { alphabet.random() }.joinToString("")
    }

    private fun lengthOfLongestSubstring(s: String): Int {
        val charMap = HashMap<Char, Int>(s.length)
        var max = 0
        var start = 0
        s.forEachIndexed { index, char ->
            charMap[char]?.takeIf { it >= start }?.let {
                max = maxOf(index - start, max)
                start = it + 1
            }
            charMap[char] = index
        }
        return maxOf(max, s.length - start)
    }
}