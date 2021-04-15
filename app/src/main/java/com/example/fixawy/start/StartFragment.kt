package com.example.fixawy.start


import android.content.Context
import android.content.SharedPreferences
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref: SharedPreferences = context?.getSharedPreferences("Auth", Context.MODE_PRIVATE)!!
        if (pref.contains("username")){
            addFragmentWithNavigation(R.id.action_startFragment_to_logInFragment)
        }
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