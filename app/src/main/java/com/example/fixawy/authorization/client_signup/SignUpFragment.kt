package com.example.fixawy.authorization.client_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.base.OnSingleClickListener
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import kotlinx.android.synthetic.main.client_edit_profile_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : BaseFragment<SignUpViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sign_up_progress_bar.visibility = View.VISIBLE
        viewModel.getCities()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.signUpCompletedLiveData.observe(this, {
            if (it) {
                goToSignIn()
            } else
                showSnackBar(requireView(), getString(R.string.sign_up_error))
        })
        viewModel.citiesLiveData.observe(this, {
            sign_up_progress_bar.visibility = View.GONE
            updateSpinner(it)
        })
    }


    override fun initListeners() {
        super.initListeners()
        signUpButton.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                if (dataIsNotEmpty())
                    viewModel.signUp(getClientData())
                else
                    showSnackBar(requireView(), getString(R.string.edit_failed))
            }
        })
        already_have_account.setOnClickListener {
            goToSignIn()
        }
    }

    private fun dataIsNotEmpty(): Boolean {
        return !(UsernameTextField.text.isNullOrEmpty() ||
                signUpMobileET.text.isNullOrEmpty() ||
                signUpEmailET.text.isNullOrEmpty() ||
                signUpEmailET.text.isNullOrEmpty() ||
                SignUpPasswordET.text.isNullOrEmpty())
    }

    private fun getClientData(): Client {
        var client = Client(0, UsernameTextField.text.toString())
        client.mobile = signUpMobileET.text.toString()
        client.email = signUpEmailET.text.toString()
        client.password = SignUpPasswordET.text.toString()
        client.city =
            viewModel.citiesLiveData.value?.find { city -> city.name == SignUpCitiesSpinner.selectedItem }
        return client
    }

    private fun goToSignIn() {
        addFragmentWithNavigation(R.id.action_signUpFragment_to_signInFragment)
    }

    private fun updateSpinner(cities: List<City>) {
        var names = mutableListOf<String>()
        cities.forEach {
            names.add(it.name!!)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SignUpCitiesSpinner.adapter = adapter
        SignUpCitiesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        SignUpCitiesSpinner.setSelection(0)
    }
}