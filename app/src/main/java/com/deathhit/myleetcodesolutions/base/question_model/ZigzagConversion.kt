package com.deathhit.myleetcodesolutions.base.question_model

import android.app.Application
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.model.AnswerVO
import kotlin.random.Random

class ZigzagConversion(application: Application) : QuestionModel(application) {
    companion object {
        private const val MAX_LENGTH_OF_S = 15
        private const val MAX_VALUE_OF_NUM_ROWS = 5
        private const val MIN_LENGTH_OF_S = 1
        private const val MIN_VALUE_OF_NUM_ROWS = 1

        private const val STRING_CODE = R.string.zigzag_conversion_code
        private const val STRING_DESCRIPTION = R.string.zigzag_conversion_description
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_NUM_OF_ROWS_X = R.string.common_num_rows_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_S_X = R.string.common_s_x
    }

    override fun code(): Spanned = HtmlCompat.fromHtml(
        application.getString(STRING_CODE),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )

    override fun description(): String = application.getString(STRING_DESCRIPTION)

    override suspend fun run(): AnswerVO {
        val numRows = generateNumRows()
        val s = generateS()
        val inputText = application.getString(
            STRING_INPUT_X,
            application.getString(
                STRING_NUM_OF_ROWS_X,
                numRows.toString()
            ) + ", " + application.getString(
                STRING_S_X, s
            )
        )
        val output = convert(s, numRows)
        val outputText = application.getString(STRING_OUTPUT_X, output)
        return AnswerVO(inputText, outputText)
    }

    private fun convert(s: String, numRows: Int): String {
        if (numRows <= 1)
            return s

        val strBuilderArray = Array(numRows) { StringBuilder() }
        s.forEachIndexed { index, char ->
            val row = evaluateRow(index, numRows)
            strBuilderArray[row].append(char)
        }

        return strBuilderArray.joinToString("")
    }

    private fun evaluateRow(index: Int, numRows: Int): Int {
        val cycle = 2 * numRows - 2
        val cycleIndex = index % cycle
        return if (cycleIndex / numRows == 0)
            cycleIndex
        else
            cycle - cycleIndex
    }

    private fun generateNumRows(): Int = Random.nextInt(MIN_VALUE_OF_NUM_ROWS, MAX_VALUE_OF_NUM_ROWS)

    private fun generateS(): String {
        val alphabet: List<Char> = ('a'..'z').toList()
        val length = Random.nextInt(
            MIN_LENGTH_OF_S,
            MAX_LENGTH_OF_S
        )
        return List(length) { alphabet.random() }.joinToString("")
    }
}