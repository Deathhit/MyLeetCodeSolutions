package com.deathhit.myleetcodesolutions.question_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

abstract class QuestionAdapter : ListAdapter<QuestionVO, QuestionViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<QuestionVO>() {
            override fun areItemsTheSame(oldItem: QuestionVO, newItem: QuestionVO): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: QuestionVO, newItem: QuestionVO): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val holder = QuestionViewHolder(parent)
        holder.itemView.setOnClickListener {
            holder.item?.let { item -> onItemClick(item, holder.adapterPosition) }
        }
        return holder
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.item = getItem(position)?.also { item -> holder.textName.text = item.name }
    }

    abstract fun onItemClick(item: QuestionVO, pos: Int)
}