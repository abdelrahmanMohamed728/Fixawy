package com.example.fixawy.authorization.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.HomeActivity
import kotlinx.android.synthetic.main.log_in_fragment.*

class LogInFragment : BaseFragment<LogInViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.log_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initListeners() {
        super.initListeners()
        signInButton.setOnClickListener {
            openHomeActivity()
        }
    }

    private fun openHomeActivity() {
        addActivity(HomeActivity())
        activity?.finishAffinity()
        showSnackBar(requireView(), getString(R.string.signInSuccessful))
    }

}