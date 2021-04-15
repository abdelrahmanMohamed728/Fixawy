package com.example.fixawy.authorization.login


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.FixerHomeActivity
import com.example.fixawy.home.HomeActivity
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.log_in_fragment.*
import org.koin.android.ext.android.get

class LogInFragment : BaseFragment<LogInViewModel>() {

    var type = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.log_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref: SharedPreferences = context?.getSharedPreferences("Auth", Context.MODE_PRIVATE)!!
        if (pref.contains("username")) {
            var username = pref.getString("username", "")
            var password = pref.getString("password", "")
            type = pref.getInt("type", -1)
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                login_progress_bar.visibility = View.VISIBLE
                viewModel.logIn(username, password, type == 0)
            }
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.completedClientLogInLiveData.observe(this, {
            login_progress_bar.visibility = View.GONE
            if (type > -1) {
                addToSharedPref(type)
            } else {
                if (client_radio_btn.isChecked) {
                    addToSharedPref(0)
                } else
                    addToSharedPref(1)
            }
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
        if (client_radio_btn.isChecked) {
            var repo: UserRepo = get()
            repo.type = UserRepo.CLIENT_TYPE
            addActivity(HomeActivity())
        } else {
            var repo: UserRepo = get()
            repo.type = UserRepo.FIXER_TYPE
            addActivity(FixerHomeActivity())
        }
        showSnackBar(requireView(), getString(R.string.signInSuccessful))
        activity?.finishAffinity()
    }

    private fun addToSharedPref(type: Int) {
        val pref: SharedPreferences = context?.getSharedPreferences("Auth", Context.MODE_PRIVATE)!!
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString("username", signInEmailET.text.toString())
        editor.putString("password", signInPasswordET.text.toString())
        editor.putInt("type", type)
        editor.apply()
    }

}