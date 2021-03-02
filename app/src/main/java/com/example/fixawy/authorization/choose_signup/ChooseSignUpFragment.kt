package com.example.fixawy.authorization.choose_signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseViewModel
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import kotlinx.android.synthetic.main.fragment_choose_sign_up.*

class ChooseSignUpFragment : BaseFragment<BaseViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initListeners() {
        super.initListeners()
        start_fixer_button.setOnClickListener {
            addFragmentWithNavigation(R.id.action_chooseSignUpFragment_to_fixerSignUpFragment)
        }
        start_client_button.setOnClickListener {
            addFragmentWithNavigation(R.id.action_chooseSignUpFragment_to_signUpFragment)
        }
    }

}