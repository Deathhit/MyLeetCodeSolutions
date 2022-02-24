package com.deathhit.myleetcodesolutions.fragment.question_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deathhit.myleetcodesolutions.databinding.ItemQuestionBinding
import com.deathhit.myleetcodesolutions.model.QuestionVO

class QuestionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {
    val binding = ItemQuestionBinding.bind(itemView)

    var item: QuestionVO? = null
}