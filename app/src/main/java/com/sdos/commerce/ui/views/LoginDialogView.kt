package com.sdos.commerce.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.sdos.commerce.R
import com.sdos.commerce.ui.viewmodels.LoginDialogViewModel
import kotlinx.android.synthetic.main.login_custom_view.*


class LoginDialogView: DialogFragment() {

    private val loginDialogViewModel: LoginDialogViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(LoginDialogViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_custom_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = false
        initializeListeners()
    }

    private fun initializeListeners() {
        btnDone.setOnClickListener {
            loginDialogViewModel.loginUser(ed_user.text.toString(), ed_pass.text.toString())
        }
    }


    companion object {
        fun newInstance() = LoginDialogView()
    }
}