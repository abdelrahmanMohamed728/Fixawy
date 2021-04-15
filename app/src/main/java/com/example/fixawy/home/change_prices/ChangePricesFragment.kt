package com.example.fixawy.home.change_prices


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.Job
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.change_prices_fragment.*
import kotlinx.android.synthetic.main.reserve_fragment.*
import org.koin.android.ext.android.get

class ChangePricesFragment : BaseFragment<ChangePricesViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_prices_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var repo : UserRepo = get()
        if (repo.fixerLiveData.value?.job?.id != null) {
            viewModel.getJobs(repo.fixerLiveData.value?.job?.id!!)
        }
    }

    override fun initListeners() {
        super.initListeners()
        updatePriceBtn.setOnClickListener {

            var repo : UserRepo = get()
            var subDepartmentId = viewModel.jobsLiveData.value?.find { job -> job.name == pricesSpinner.selectedItem }?.id
            var id = repo.fixerLiveData.value?.id
            if (id != null &&subDepartmentId!=null&&priceEditText.text.toString().toIntOrNull()!=null) {
                viewModel.updatePrice(
                    id,
                    subDepartmentId,
                    priceEditText.text.toString().toIntOrNull()!!
                )
                changePricesProgressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.jobsLiveData.observe(this, {
            changePricesProgressBar.visibility = View.GONE
            updateJobSpinner(it)
        })
        viewModel.priceUpdatedLiveData.observe(this , {
            if (it){
                changePricesProgressBar.visibility = View.GONE
                showAcceptedSnackBar(requireView(),"تم تحديث سعرك")
            }
        })
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
        pricesSpinner.adapter = adapter
        pricesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        pricesSpinner.setSelection(0)
    }
}