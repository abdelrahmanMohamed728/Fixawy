package com.example.fixawy.authorization.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.base.OnSingleClickListener
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : BaseFragment<SignUpViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }


    override fun initListeners() {
        super.initListeners()
        signUpButton.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                goToSignIn()
            }
        })
        already_have_account.setOnClickListener {
            goToSignIn()
        }
    }

    private fun goToSignIn() {
        view?.findNavController()?.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

}