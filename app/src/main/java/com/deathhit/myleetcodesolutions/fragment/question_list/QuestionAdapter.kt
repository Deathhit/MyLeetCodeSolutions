package com.deathhit.myleetcodesolutions.fragment.question_list

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.deathhit.myleetcodesolutions.model.QuestionVO

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
        configureViewHolder(holder)
        return holder
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.item =
            getItem(position)?.also { item -> bindTextName(item, holder.binding.textViewName) }
    }

    private fun bindTextName(item: QuestionVO, textName: TextView) {
        textName.text = item.name
    }

    private fun configureViewHolder(holder: QuestionViewHolder) {
        holder.itemView.setOnClickListener {
            holder.item?.let { item -> onItemClick(item, holder.adapterPosition) }
        }
    }

    abstract fun onItemClick(item: QuestionVO, pos: Int)
}