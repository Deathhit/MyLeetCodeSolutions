package com.deathhit.myleetcodesolutions.model

import android.content.Context
import android.os.Parcelable
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.question_model.QuestionModel
import com.deathhit.myleetcodesolutions.enum_type.Question
import com.deathhit.myleetcodesolutions.question_model.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionVO(val name: String, val question: Question) : Parcelable {
    companion object {
        private const val STRING_ADD_TWO_NUMBERS = R.string.question_vo_add_two_numbers
        private const val STRING_CONTAINER_WITH_MOST_WATER =
            R.string.question_vo_container_with_most_water
        private const val STRING_INTEGER_TO_ROMAN =
            R.string.question_vo_integer_to_roman
        private const val STRING_LONGEST_PALINDROMIC_SUBSTRING =
            R.string.question_vo_longest_palindromic_substring
        private const val STRING_LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS =
            R.string.question_vo_longest_substring_without_repeating_characters
        private const val STRING_MEDIAN_OF_TWO_SORTED_ARRAYS =
            R.string.question_vo_median_of_two_sorted_arrays
        private const val STRING_PALINDROME_NUMBER = R.string.question_vo_palindrome_number
        private const val STRING_REGULAR_EXPRESSION_MATCHING =
            R.string.question_vo_regular_expression_matching
        private const val STRING_REVERSE_INTEGER = R.string.question_vo_reverse_integer
        private const val STRING_ROMAN_TO_INTEGER = R.string.question_vo_roman_to_integer
        private const val STRING_STRING_TO_INTEGER_ATOI =
            R.string.question_vo_string_to_integer_atoi
        private const val STRING_TWO_SUM = R.string.question_vo_two_sum
        private const val STRING_ZIGZAG_CONVERSION = R.string.question_vo_zigzag_conversion

        fun valueOf(context: Context, question: Question): QuestionVO {
            return when (question) {
                Question.ADD_TWO_NUMBERS -> QuestionVO(
                    context.getString(STRING_ADD_TWO_NUMBERS),
                    question
                )
                Question.CONTAINER_WITH_MOST_WATER -> QuestionVO(
                    context.getString(STRING_CONTAINER_WITH_MOST_WATER),
                    question
                )
                Question.INTEGER_TO_ROMAN -> QuestionVO(
                    context.getString(STRING_INTEGER_TO_ROMAN),
                    question
                )
                Question.LONGEST_PALINDROMIC_SUBSTRING -> QuestionVO(
                    context.getString(STRING_LONGEST_PALINDROMIC_SUBSTRING),
                    question
                )
                Question.LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS -> QuestionVO(
                    context.getString(STRING_LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS),
                    question
                )
                Question.MEDIAN_OF_TWO_SORTED_ARRAYS -> QuestionVO(
                    context.getString(STRING_MEDIAN_OF_TWO_SORTED_ARRAYS),
                    question
                )
                Question.PALINDROME_NUMBER -> QuestionVO(
                    context.getString(STRING_PALINDROME_NUMBER),
                    question
                )
                Question.REGULAR_EXPRESSION_MATCHING -> QuestionVO(
                    context.getString(STRING_REGULAR_EXPRESSION_MATCHING),
                    question
                )
                Question.REVERSE_INTEGER -> QuestionVO(
                    context.getString(STRING_REVERSE_INTEGER),
                    question
                )
                Question.ROMAN_TO_INTEGER -> QuestionVO(
                    context.getString(STRING_ROMAN_TO_INTEGER),
                    question
                )
                Question.STRING_TO_INTEGER_ATOI -> QuestionVO(
                    context.getString(STRING_STRING_TO_INTEGER_ATOI), question
                )
                Question.TWO_SUM -> QuestionVO(context.getString(STRING_TWO_SUM), question)
                Question.ZIGZAG_CONVERSION -> QuestionVO(
                    context.getString(STRING_ZIGZAG_CONVERSION),
                    question
                )
            }
        }
    }

    fun createQuestionModel(context: Context): QuestionModel {
        return when (question) {
            Question.ADD_TWO_NUMBERS -> AddTwoNumbers(context)
            Question.CONTAINER_WITH_MOST_WATER -> ContainerWithMostWater(context)
            Question.INTEGER_TO_ROMAN -> IntegerToRoman(context)
            Question.LONGEST_PALINDROMIC_SUBSTRING -> LongestPalindromicSubstring(context)
            Question.LONGEST_SUBSTRING_WITHOUT_REPEATING_CHARACTERS -> LongestSubstringWithoutRepeatingCharacters(
                context
            )
            Question.MEDIAN_OF_TWO_SORTED_ARRAYS -> MedianOfTwoSortedArrays(context)
            Question.PALINDROME_NUMBER -> PalindromeNumber(context)
            Question.REGULAR_EXPRESSION_MATCHING -> RegularExpressionMatching(context)
            Question.REVERSE_INTEGER -> ReverseInteger(context)
            Question.ROMAN_TO_INTEGER -> RomanToInteger(context)
            Question.STRING_TO_INTEGER_ATOI -> StringToIntegerAtoi(context)
            Question.TWO_SUM -> TwoSum(context)
            Question.ZIGZAG_CONVERSION -> ZigzagConversion(context)
        }
    }
}