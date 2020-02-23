package com.sdos.commerce.ui.viewholders

import android.view.View
import com.sdos.commerce.entities.Task
import kotlinx.android.synthetic.main.list_item_view.view.*

class TaskViewHolder(itemView: View): BaseViewHolder<Task>(itemView) {

    override fun onBind(task: Task) {
        itemView.name.text = task.name
        itemView.prof.text = String.format(task.duration.toString(), " horas")
        itemView.text_type.visibility = View.VISIBLE
        itemView.text_type.text = "Duración"
    }
}