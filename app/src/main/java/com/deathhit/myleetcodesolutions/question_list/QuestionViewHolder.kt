package com.deathhit.myleetcodesolutions.question_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

class QuestionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false)
) {
    companion object {
        private const val ID_TEXT_NAME = R.id.textView_name
        private const val LAYOUT = R.layout.item_question
    }

    val textName = itemView.findViewById<TextView>(ID_TEXT_NAME)

    var item: QuestionVO? = null
}