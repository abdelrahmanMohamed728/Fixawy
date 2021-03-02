package com.example.fixawy.authorization.fixer_signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Job
import kotlinx.android.synthetic.main.fixer_sign_up_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.*

class FixerSignUpFragment : BaseFragment<FixerSignUpViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fixer_sign_up_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixer_sign_up_progress_bar.visibility = View.VISIBLE
        viewModel.getCities()
        viewModel.getJobs()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.citiesLiveData.observe(this, {
            updateSpinner(it)
        })
        viewModel.jobsLiveData.observe(this, {
            fixer_sign_up_progress_bar.visibility = View.GONE
            updateJobSpinner(it)
        })
        viewModel.signUpCompletedLiveData.observe(this , {
            if (it){
                goToSignIn()
            }
            else
                showSnackBar(requireView(),getString(R.string.sign_up_error))
        })
    }

    private fun goToSignIn() {
        addFragmentWithNavigation(R.id.action_fixerSignUpFragment_to_logInFragment)
    }

    override fun initListeners() {
        super.initListeners()
        signUpFixerButton.setOnClickListener {
            if (dataIsNotEmpty())
                viewModel.signUp(getFixerData())
            else
                showSnackBar(requireView(),getString(R.string.edit_failed))
        }
    }

    private fun dataIsNotEmpty(): Boolean {
        return !(FixerEmailTextField.text.isNullOrEmpty() ||
                FixersignUpMobileET.text.isNullOrEmpty() ||
                FixerSignUpPasswordET.text.isNullOrEmpty() ||
                FixerUsernameTextField.text.isNullOrEmpty())
    }

    private fun getFixerData(): Fixer {
        var fixer = Fixer(0, FixerUsernameTextField.text.toString())
        fixer.email = FixerEmailTextField.text.toString()
        fixer.mobile = FixersignUpMobileET.text.toString()
        fixer.password = FixerSignUpPasswordET.text.toString()
        fixer.city =
            viewModel.citiesLiveData.value?.find { city -> city.name == FixerSignUpCitiesSpinner.selectedItem }
        fixer.job =
            viewModel.jobsLiveData.value?.find { job -> job.name == FixerSignUpJobSpinner.selectedItem }
        return fixer
    }

    private fun updateJobSpinner(jobs: List<Job>) {
        var names = mutableListOf<String>()
        jobs.forEach {
            names.add(it.name!!)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        FixerSignUpJobSpinner.adapter = adapter
        FixerSignUpJobSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        FixerSignUpJobSpinner.setSelection(0)
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
        FixerSignUpCitiesSpinner.adapter = adapter
        FixerSignUpCitiesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
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
        FixerSignUpCitiesSpinner.setSelection(0)
    }
}