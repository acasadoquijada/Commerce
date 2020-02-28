package com.sdos.commerce.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.sdos.commerce.databinding.FragmentDetailEmployeeBinding
import com.sdos.commerce.entities.Employee
import com.sdos.commerce.ui.viewmodels.DetailEmployeView
import com.sdos.commerce.ui.viewmodels.DetailEmployeeViewModel
import com.sdos.commerce.ui.views.DateDialogView
import com.sdos.commerce.ui.views.DateDialogView.Companion.TAG_DATE_DIALOG
import com.sdos.commerce.util.showMessageError
import kotlinx.android.synthetic.main.fragment_detail_employee.*
import java.lang.Error

class DetailEmployeeFragment : Fragment(), DetailEmployeView {

    private var date: String? = null
    private lateinit var viewModel: DetailEmployeeViewModel
    private var employee = Employee()
    private lateinit var binding: FragmentDetailEmployeeBinding
    private lateinit var mapInputText: Map<ErrorField, TextInputLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEmployeeBinding.inflate(inflater)
            .apply {
                employee = null
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.employee = employee
        viewModel = ViewModelProviders.of(this).get(DetailEmployeeViewModel::class.java)
        viewModel.init(this)
        initialize()
    }

    private fun initialize() {
        retrieveArguments()
        prepareListeners()
        populateMap()
        viewModel.getListSkills().observe(this, Observer {
            context?.let {context ->
                val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, it.map { it.name })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spn_rol.adapter = adapter
            }
        })
        viewModel.getEmployees().observe(this, Observer {
            val a = 22
            val m = 111
        })
    }

    private fun retrieveArguments() {
        arguments?.let {
            employee = it.get(ARGUMENT_EXTRA_EMPLOYEE) as Employee
        }
    }

    private fun populateMap() {
        mapInputText = mapOf(
            ErrorField.ERROR_FIELD_NAME to input_name,
            ErrorField.ERROR_FIELD_SURNAME to input_surname,
            ErrorField.ERROR_FIELD_EMAIL to input_email,
            ErrorField.ERROR_FIELD_PHONE to input_phone,
            ErrorField.ERROR_FIELD_DATE to input_date,
            ErrorField.ERROR_FIELD_PASS to input_pass
        )
    }

    private fun prepareListeners() {
        ed_date.setOnClickListener { ed ->
            fragmentManager?.let { fm ->
                DateDialogView.newInstance()
                    .apply {
                        setListener {
                            (ed as EditText).setText(it)
                        }
                    }
                    .show(fm, TAG_DATE_DIALOG) }
        }

        btnDone.setOnClickListener {
            viewModel.onButtonAddClicked(employee)
        }
    }

    override fun showError(errorFieldList: List<ErrorField>) {
        errorFieldList.forEach {
            mapInputText[it]?.showMessageError("Campo incorrecto")
        }
    }

    companion object {
        const val ARGUMENT_EXTRA_EMPLOYEE = "ARGUMENT_EXTRA_EMPLOYEE"
    }

    enum class ErrorField {
        ERROR_FIELD_NAME, ERROR_FIELD_SURNAME, ERROR_FIELD_EMAIL, ERROR_FIELD_PHONE, ERROR_FIELD_DATE, ERROR_FIELD_PASS
    }
}
