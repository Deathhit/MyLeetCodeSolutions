package com.deathhit.myleetcodesolutions.add_two_numbers

import android.app.Application
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.R
import kotlin.collections.HashMap
import kotlin.random.Random

class AddTwoNumbersViewModel(application: Application) :
    StateViewModel<AddTwoNumbersViewModel.State>(application) {
    class State(
        val statusCode: Status<Spanned>,
        val statusInput: Status<String>,
        val statusOutput: Status<String>
    )

    companion object {
        private const val MIN_LENGTH_OF_NUMBERS = 2
        private const val MAX_LENGTH_OF_NUMBERS = 10
        private const val MAX_VALUE_OF_NUMBERS = 100

        private const val STRING_CODE = R.string.two_sum_code
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_NUMBERS_X = R.string.two_sum_numbers_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
        private const val STRING_TARGET_X = R.string.two_sum_target_x
    }

    private val statusCode = StatePackage<Spanned>()
    private val statusInput = StatePackage<String>()
    private val statusOutput = StatePackage<String>()

    override fun createState(): State = State(statusCode, statusInput, statusOutput)

    fun run() {
        val numbers = generateNumbers()
        val target = generateTarget(numbers)
        val application = getApplication<Application>()
        val inputText = application.getString(
            STRING_INPUT_X,
            application.getString(
                STRING_NUMBERS_X,
                numbers.toList().toString()
            ) + ", " + application.getString(
                STRING_TARGET_X, target.toString()
            )
        )
        val output = solve(numbers, target)
        val outputText = application.getString(STRING_OUTPUT_X, output.toList().toString())

        statusInput.content = inputText
        statusOutput.content = outputText
        postState()
    }

    fun showCode() {
        val application = getApplication<Application>()
        statusCode.content = HtmlCompat.fromHtml(application.getString(STRING_CODE), FROM_HTML_MODE_COMPACT)
        postState()
    }

    private fun generateNumbers(): IntArray {
        return IntArray(
            Random.nextInt(
                MIN_LENGTH_OF_NUMBERS,
                MAX_LENGTH_OF_NUMBERS
            )
        ).map { Random.nextInt(MAX_VALUE_OF_NUMBERS) }
            .toIntArray()
    }

    private fun generateTarget(numbers: IntArray): Int {
        val firstIndex = Random.nextInt(numbers.size)
        var secondIndex: Int
        do {
            secondIndex = Random.nextInt(numbers.size)
        } while (firstIndex == secondIndex)
        return numbers[firstIndex] + numbers[secondIndex]
    }

    private fun solve(numbers: IntArray, target: Int): IntArray {
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