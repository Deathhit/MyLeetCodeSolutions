package com.deathhit.myleetcodesolutions.add_two_numbers

import android.app.Application
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import com.deathhit.framework.StatePackage
import com.deathhit.framework.StateViewModel
import com.deathhit.framework.Status
import com.deathhit.myleetcodesolutions.R
import kotlin.random.Random

class AddTwoNumbersViewModel(application: Application) :
    StateViewModel<AddTwoNumbersViewModel.State>(application) {
    class State(
        val statusCode: Status<Spanned>,
        val statusInput: Status<String>,
        val statusOutput: Status<String>
    )

    companion object {
        private const val MIN_LENGTH_OF_LIST_NODES = 1
        private const val MAX_LENGTH_OF_LIST_NODES = 5
        private const val MAX_VALUE_OF_LIST_NODE = 9

        private const val STRING_CODE = R.string.add_two_numbers_code
        private const val STRING_INPUT_X = R.string.common_input_x
        private const val STRING_L1_X = R.string.add_two_numbers_l1_x
        private const val STRING_L2_X = R.string.add_two_numbers_l2_x
        private const val STRING_OUTPUT_X = R.string.common_output_x
    }

    private val statusCode = StatePackage<Spanned>()
    private val statusInput = StatePackage<String>()
    private val statusOutput = StatePackage<String>()

    override fun createState(): State = State(statusCode, statusInput, statusOutput)

    fun run() {
        val l1 = generateListNode()
        val l2 = generateListNode()
        val application = getApplication<Application>()
        val inputText = application.getString(
            STRING_INPUT_X,
            application.getString(
                STRING_L1_X,
                listNodeToString(l1)
            ) + ", " + application.getString(
                STRING_L2_X, listNodeToString(l2)
            )
        )
        val output = addTwoNumbers(l1, l2)
        val outputText = application.getString(STRING_OUTPUT_X, listNodeToString(output))

        statusInput.content = inputText
        statusOutput.content = outputText
        postState()
    }

    fun showCode() {
        val application = getApplication<Application>()
        statusCode.content =
            HtmlCompat.fromHtml(application.getString(STRING_CODE), FROM_HTML_MODE_COMPACT)
        postState()
    }

    private fun listNodeToString(listNode: ListNode?): String {
        val list = ArrayList<Int>()
        var temp: ListNode? = listNode
        do {
            list.add(temp!!.`val`)
            temp = temp.next
        } while (temp != null)
        return list.toString()
    }

    private fun generateListNode(): ListNode {
        val length = Random.nextInt(MIN_LENGTH_OF_LIST_NODES, MAX_LENGTH_OF_LIST_NODES)
        val result = ListNode(generateListNodeValue())
        var temp = result
        var i = 1
        while (i < length) {
            temp.next = ListNode(generateListNodeValue())
            temp = temp.next!!
            i++
        }
        return result
    }

    private fun generateListNodeValue(): Int {
        return Random.nextInt(MAX_VALUE_OF_LIST_NODE)
    }

    private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null)
            return null
        var sum = 0
        l1?.let { sum += l1.`val` }
        l2?.let { sum += l2.`val` }
        val result = ListNode(sum % 10)
        result.next = addTwoNumbers(l1?.next, l2?.next)
        if (sum / 10 >= 1)
            result.next = addTwoNumbers(result.next, ListNode(1))
        return result
    }
}