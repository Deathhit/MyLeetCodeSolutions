package com.deathhit.myleetcodesolutions.question_model

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.model.AnswerVO
import kotlin.random.Random

class MedianOfTwoSortedArrays(context: Context) : QuestionModel(context) {
    companion object {
        private const val MAX_LENGTH_OF_NUMBERS = 5
        private const val MAX_VALUE_OF_NUMBERS = 9
        private const val MIN_LENGTH_OF_NUMBERS1 = 0
        private const val MIN_LENGTH_OF_NUMBERS2 = 1

        private const val STRING_CODE = R.string.median_of_two_sorted_arrays_code
        private const val STRING_DESCRIPTION = R.string.median_of_two_sorted_arrays_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_NUMBERS1_X = R.string.common_numbers1_x
        private const val STRING_NUMBERS2_X = R.string.common_numbers2_x
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
        val numbers1 = generateNumbers1()
        val numbers2 = generateNumbers2()
        val inputText = context.getString(
            STRING_INPUT_X, context.getString(
                STRING_NUMBERS1_X, numbers1.toList().toString()
            ) + ", " + context.getString(
                STRING_NUMBERS2_X, numbers2.toList().toString()
            )
        )
        val output = findMedianSortedArrays(numbers1, numbers2)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun findMedianSortedArrays(numbers1: IntArray, numbers2: IntArray): Double {
        if (numbers1.size > numbers2.size)
            return findMedianSortedArrays(numbers2, numbers1)
        val mergedSize = numbers1.size + numbers2.size
        val mergedMedianIndex = (mergedSize + 1) / 2
        var lowerBound = 0
        var upperBound = numbers1.size
        while (lowerBound < upperBound) {
            val medianIndex1 = (lowerBound + upperBound) / 2
            val medianIndex2 = mergedMedianIndex - medianIndex1
            if (numbers1[medianIndex1] < numbers2[medianIndex2 - 1])
                lowerBound = medianIndex1 + 1
            else
                upperBound = medianIndex1
        }

        val medianIndex1 = lowerBound
        val medianIndex2 = mergedMedianIndex - medianIndex1

        val firstMedianValue = maxOf(
            if (medianIndex1 <= 0) Int.MIN_VALUE else numbers1[medianIndex1 - 1],
            if (medianIndex2 <= 0) Int.MIN_VALUE else numbers2[medianIndex2 - 1]
        )

        if (mergedSize % 2 > 0)
            return firstMedianValue.toDouble()

        val secondMedianValue = minOf(
            if (medianIndex1 >= numbers1.size) Int.MAX_VALUE else numbers1[medianIndex1],
            if (medianIndex2 >= numbers2.size) Int.MAX_VALUE else numbers2[medianIndex2]
        )

        return (firstMedianValue + secondMedianValue).toDouble() / 2
    }

    private fun generateNumbers1(): IntArray {
        val intArray = IntArray(
            Random.nextInt(
                MIN_LENGTH_OF_NUMBERS1,
                MAX_LENGTH_OF_NUMBERS
            )
        ).map { Random.nextInt(MAX_VALUE_OF_NUMBERS) }
            .toIntArray()
        intArray.sort()
        return intArray
    }

    private fun generateNumbers2(): IntArray {
        val intArray = IntArray(
            Random.nextInt(
                MIN_LENGTH_OF_NUMBERS2,
                MAX_LENGTH_OF_NUMBERS
            )
        ).map { Random.nextInt(MAX_VALUE_OF_NUMBERS) }
            .toIntArray()
        intArray.sort()
        return intArray
    }
}