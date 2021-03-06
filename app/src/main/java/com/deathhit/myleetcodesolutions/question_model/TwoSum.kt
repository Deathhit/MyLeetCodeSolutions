package com.deathhit.myleetcodesolutions.question_model

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.AnswerVO
import kotlin.random.Random

class TwoSum(context: Context) : QuestionModel(context) {
    companion object {
        private const val MAX_LENGTH_OF_NUMBERS = 10
        private const val MAX_VALUE_OF_NUMBERS = 100
        private const val MIN_LENGTH_OF_NUMBERS = 2

        private const val STRING_CODE = R.string.two_sum_code
        private const val STRING_DESCRIPTION = R.string.two_sum_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_NUMBERS_X = R.string.common_numbers_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_TARGET_X = R.string.common_target_x
    }

    override val code: Spanned by lazy {
        HtmlCompat.fromHtml(
            context.getString(STRING_CODE),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    override val description: String by lazy { context.getString(STRING_DESCRIPTION) }

    override fun run(): AnswerVO {
        val numbers = generateNumbers()
        val target = generateTarget(numbers)
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_NUMBERS_X,
                numbers.toList().toString()
            ) + ", " + context.getString(
                STRING_TARGET_X, target.toString()
            )
        )
        val output = twoSum(numbers, target)
        val outputText = context.getString(STRING_OUTPUT_X, output.toList().toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateNumbers(): IntArray = IntArray(
            Random.nextInt(
                MIN_LENGTH_OF_NUMBERS,
                MAX_LENGTH_OF_NUMBERS
            )
        ).map { Random.nextInt(MAX_VALUE_OF_NUMBERS) }
            .toIntArray()

    private fun generateTarget(numbers: IntArray): Int {
        val firstIndex = Random.nextInt(numbers.size)
        var secondIndex: Int
        do {
            secondIndex = Random.nextInt(numbers.size)
        } while (firstIndex == secondIndex)
        return numbers[firstIndex] + numbers[secondIndex]
    }

    private fun twoSum(numbers: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        numbers.forEachIndexed { index, num ->
            map[target - num]?.let {
                return intArrayOf(index, it)
            }
            map[num] = index
        }
        throw IllegalStateException("No solution!")
    }
}