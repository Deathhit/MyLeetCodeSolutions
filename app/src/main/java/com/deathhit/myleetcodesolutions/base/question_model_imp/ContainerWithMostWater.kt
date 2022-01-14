package com.deathhit.myleetcodesolutions.base.question_model_imp

import android.content.Context
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.QuestionModel
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class ContainerWithMostWater(context: Context) : QuestionModel(context) {
    companion object {
        private const val MAX_LENGTH_OF_HEIGHT = 10
        private const val MAX_VALUE_OF_HEIGHT = 100
        private const val MIN_LENGTH_OF_HEIGHT = 2
        private const val MIN_VALUE_OF_HEIGHT = 1

        private const val STRING_CODE = R.string.container_with_most_water_code
        private const val STRING_DESCRIPTION = R.string.container_with_most_water_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_HEIGHT_X = R.string.common_height_x
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
        val height = generateHeight()
        val inputText = context.getString(
            STRING_INPUT_X,
            context.getString(
                STRING_HEIGHT_X,
                height.toList().toString()
            )
        )
        val output = maxArea(height)
        val outputText = context.getString(STRING_OUTPUT_X, output.toString())
        return AnswerVO(inputText, outputText)
    }

    private fun generateHeight(): IntArray = IntArray(
        Random.nextInt(
            MIN_LENGTH_OF_HEIGHT,
            MAX_LENGTH_OF_HEIGHT
        )
    ).map { Random.nextInt(MIN_VALUE_OF_HEIGHT, MAX_VALUE_OF_HEIGHT) }
        .toIntArray()

    private fun maxArea(height: IntArray): Int {
        var area = 0
        var left = 0
        var right = height.size - 1
        while (right > left) {
            area = kotlin.math.max(
                area,
                kotlin.math.min(height[left], height[right]) * (right - left)
            )
            if (height[left] < height[right])
                left++
            else
                right--
        }
        return area
    }

    /*
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var maxHeightValue = 0
        for(length in height.size - 1 downTo 1) {
            for (i in 0 until height.size - length) {
                val currentHeightValue = kotlin.math.min(height[i], height[i + length])
                if (currentHeightValue > maxHeightValue) {
                    maxHeightValue = currentHeightValue
                    val area = length * currentHeightValue
                    if (area > maxArea)
                        maxArea = area
                }
            }
        }
        return maxArea
    }
     */
}