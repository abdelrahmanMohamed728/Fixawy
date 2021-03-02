package com.example.fixawy.authorization.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import org.koin.android.ext.android.get
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.FixerHomeActivity
import com.example.fixawy.home.HomeActivity
import com.example.fixawy.model.User
import com.example.fixawy.network.repos.UserRepo
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

    override fun initObservers() {
        super.initObservers()
        viewModel.completedClientLogInLiveData.observe(this, {
            login_progress_bar.visibility = View.GONE
            if (it)
                openHomeActivity()
        })
    }

    override fun initListeners() {
        super.initListeners()
        signInButton.setOnClickListener {
            login_progress_bar.visibility = View.VISIBLE
            viewModel.logIn(
                signInEmailET.text.toString(),
                signInPasswordET.text.toString(),
                client_radio_btn.isChecked
            )
        }
    }

    private fun openHomeActivity() {
        if (client_radio_btn.isChecked)
            addActivity(HomeActivity())
        else {
            var repo: UserRepo = get()
            repo.type = UserRepo.FIXER_TYPE
            addActivity(FixerHomeActivity())
        }
        showSnackBar(requireView(), getString(R.string.signInSuccessful))
        activity?.finishAffinity()
    }

}