package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class LongestSubstringWithoutRepeatingCharacters(context: Context) : QuestionModel(
    context
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
        val output = lengthOfLongestSubstring(s)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
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