package com.deathhit.myleetcodesolutions.question_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.deathhit.framework.toolbox.StateFragment
import com.deathhit.myleetcodesolutions.R
import com.deathhit.myleetcodesolutions.base.model.QuestionVO

class QuestionListFragment : StateFragment<QuestionListViewModel.State, QuestionListViewModel>() {
    companion object {
        private const val ID_RECYCLER_VIEW = R.id.recyclerView
        private const val LAYOUT = R.layout.fragment_question_list

        fun create(): QuestionListFragment{
            val args = Bundle()
            val fragment = QuestionListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var recyclerView: RecyclerView? = null

    private var adapter: QuestionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: kotlin.run { viewModel.loadQuestionList() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LAYOUT, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(ID_RECYCLER_VIEW)

        adapter = createQuestionAdapter()

        recyclerView?.let {
            it.setHasFixedSize(true)
            it.adapter = adapter
        }

        viewModel.getStateLiveData().observe(viewLifecycleOwner, { state ->
            state.eventGoToQuestionActivity.signForEvent(this)?.let {

            }
            state.statusQuestionList.signForStatus(this)?.let { adapter?.submitList(it) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null

        adapter = null
    }

    override fun createViewModel(args: Bundle): QuestionListViewModel {
        val viewModel: QuestionListViewModel by viewModels()
        return viewModel
    }

    override fun onSaveViewModelArgs(args: Bundle) {

    }

    private fun createQuestionAdapter(): QuestionAdapter {
        return object : QuestionAdapter() {
            override fun onItemClick(item: QuestionVO, pos: Int) {
                viewModel.goToQuestionActivity(item)
            }
        }
    }
}