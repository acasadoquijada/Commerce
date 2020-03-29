package com.manday.loginuser.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.manday.loginuser.BaseLoginDialogView
import com.manday.loginuser.R
import com.manday.loginuser.viewmodels.LoginDialogViewModel
import kotlinx.android.synthetic.main.login_custom_view.*


internal class LoginDialogView: BaseLoginDialogView() {

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
            /*
            loginDialogViewModel.loginUser(et_username.text.toString(), ed_pass.text.toString()).observe(this, Observer {
                if (it == null) {
                    message.visibility = VISIBLE
                } else {
                    this.dismiss()
                }
            })

             */
        }
    }

    companion object {
        fun newInstance() = LoginDialogView()
    }

}