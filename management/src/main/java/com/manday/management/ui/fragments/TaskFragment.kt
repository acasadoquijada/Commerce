package com.manday.management.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manday.management.data.entities.TaskEntity
import com.manday.coreui.fragment.BaseFragment
import com.manday.management.R
import com.manday.management.databinding.FragmentTaskBinding
import com.manday.management.ui.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task.*
import org.koin.java.KoinJavaComponent.inject

class TaskFragment : BaseFragment() {

    private val viewModel: TaskViewModel by inject(TaskViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTaskBinding.inflate(inflater).root
    }

    override fun initialize() {
        configureSpinner()
        text_head.text = "Tareas"
        ll_filter.visibility = VISIBLE
        mainRecyclerView.showShimmer()
        /*
        viewModel.getTasks().observe(this, Observer {
            if (it != null) {
                updateRecyclerView(it)
                mainRecyclerView.hideShimmer()
            }
        })

         */
        btnAdd.setOnClickListener {
            //onButtonAddClicked(R.id.action_taskFragment_to_detailTaskFragment)
        }
    }

    private fun configureSpinner() {
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.filter_by))
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spn_filter.adapter = adapter
        spn_filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val taskList = when (position) {
                    0 -> { viewModel.getAllTasks() }
                    1 -> { viewModel.getPendingTasks() }
                    2 -> { viewModel.getCompletedTasks() }
                    else -> { viewModel.getAllTasks() }
                }
                updateRecyclerView(taskList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun updateRecyclerView(taskList: List<TaskEntity>?) {
        /*
        taskList?.let {
            mainRecyclerView.adapter = TaskAdapter(it) {
                onItemClicked(R.id.action_taskFragment_to_detailTaskFragment, Bundle().apply {
                    putSerializable(ARGUMENT_EXTRA_TASK, it)
                })
            }
        }

         */
    }
}