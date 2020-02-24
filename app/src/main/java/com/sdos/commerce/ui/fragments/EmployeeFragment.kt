package com.sdos.commerce.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sdos.commerce.R
import com.sdos.commerce.listeners.FragmentListener
import com.sdos.commerce.ui.adapters.EmployeeAdapter
import com.sdos.commerce.ui.viewmodels.EmployeeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class EmployeeFragment : Fragment(R.layout.fragment_main) {

    private lateinit var listener: FragmentListener
    private lateinit var viewModel: EmployeeFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_head.text = "Empleados"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeFragmentViewModel::class.java)
        initialize()
    }

    private fun initialize() {
        btnAdd.setOnClickListener {
            listener.onButtonDoneClicked(R.id.action_mainFragment_to_detailEmployeeFragment)
        }
        main_recycler_view.showShimmer()
        viewModel.setEmployees()
        if (userLogged) userLoggedIn()
    }

    fun userLoggedIn() {
        userLogged = true
        viewModel.getEmployees().observe(this, Observer {
            if (it != null) {
                main_recycler_view.adapter = EmployeeAdapter(it) {

                }
                main_recycler_view.hideShimmer()
            }
        })
    }

    companion object {
        private var userLogged = false
    }

}
