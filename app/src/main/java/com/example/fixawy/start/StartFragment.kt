package com.example.fixawy.start


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import kotlinx.android.synthetic.main.start_fragment.*

class StartFragment : BaseFragment<StartViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun initListeners() {
        super.initListeners()
        start_login_button.setOnClickListener {
            addFragmentWithNavigation(R.id.action_startFragment_to_logInFragment)
        }
        start_signup_button.setOnClickListener {
             addFragmentWithNavigation(R.id.action_startFragment_to_chooseSignUpFragment)
        }
    }

}