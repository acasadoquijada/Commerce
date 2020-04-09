package com.sdos.commerce.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.manday.coredata.entities.EmployeeEntity
import com.manday.coreui.fragment.BaseFragment
import com.sdos.commerce.TypeResponse
import com.sdos.commerce.databinding.FragmentDetailEmployeeBinding
import com.sdos.commerce.ui.viewmodels.DetailEmployeeViewModel
import com.sdos.commerce.ui.views.DateDialogView
import com.sdos.commerce.ui.views.DateDialogView.Companion.TAG_DATE_DIALOG
import com.sdos.commerce.util.showMessageError
import kotlinx.android.synthetic.main.fragment_detail_employee.*
import org.koin.java.KoinJavaComponent.inject

class DetailEmployeeFragment : BaseFragment() {

    private val viewModel: DetailEmployeeViewModel by inject(DetailEmployeeViewModel::class.java)
    private lateinit var binding: FragmentDetailEmployeeBinding
    private lateinit var mapInputText: Map<DetailEmployeeViewModel.ErrorField, TextInputLayout>

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

    override fun initialize() {
        prepareListeners()
        populateMap()
        viewModel.getListSkills()?.observe(this, Observer {
            context?.let {context ->
                val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, it.map { it.name })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spn_rol.adapter = adapter
                spn_rol.setSelection(binding.employee?.skill ?: 0)
            }
        })
    }

    override fun retrieveArguments() {
        arguments?.let {
            binding.employee = it.get(ARGUMENT_EXTRA_EMPLOYEE)?.let {emp ->
                 emp as EmployeeEntity
            } ?: EmployeeEntity()
        }
    }

    private fun populateMap() {
        mapInputText = mapOf(
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_NAME to input_name,
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_SURNAME to input_surname,
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_EMAIL to input_email,
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_PHONE to input_phone,
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_DATE to input_date,
            DetailEmployeeViewModel.ErrorField.ERROR_FIELD_PASS to input_pass
        )
    }

    private fun prepareListeners() {
        spn_rol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.employee?.skill = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
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
            binding.employee?.let {
                viewModel.onButtonAddClicked(it).observe(this, Observer {response ->
                    when(response.typeResponse) {
                        TypeResponse.TEXT -> { showMessage(response.text) }
                        TypeResponse.EXTRA_LIST -> {
                            response.extraList?.forEach {
                                mapInputText[it]?.showMessageError(response.text)
                            }
                        }
                    }
                })
            }
        }
    }
}
