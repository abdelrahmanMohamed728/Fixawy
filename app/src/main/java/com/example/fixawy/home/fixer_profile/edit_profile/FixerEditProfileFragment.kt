package com.example.fixawy.home.fixer_profile.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.koin.android.ext.android.get
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.City
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Job
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.fixer_edit_profile_fragment.*

class FixerEditProfileFragment : BaseFragment<FixerEditProfileViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fixer_edit_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixer_edit_profile_pb.visibility = View.VISIBLE
        updateUser()
        viewModel.getCities()
        viewModel.getJobs()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.fixerUpdatedLiveData.observe(this , {
            fixer_edit_save_pb.visibility = View.GONE
            UserRepo.getInstance().fixerLiveData.value = viewModel.fixerLiveData.value
            showAcceptedSnackBar(requireView(), getString(R.string.edit_successful))
            finishFragment()
        })
        viewModel.citiesLiveData.observe(this, {
            updateSpinner(it)
        })
        viewModel.jobsLiveData.observe(this, {
            updateJobSpinner(it)
            fixer_edit_profile_pb.visibility = View.GONE
        })
    }



    private fun updateUser() {
        var repo: UserRepo = get()
        var fixer = repo.fixerLiveData.value
        viewModel.fixerLiveData.value = fixer
        fixer_name_show.setText(fixer?.name)
        fixer_phone_show.setText(fixer?.mobile)
        fixer_id_show.setText(fixer?.identityNo)
        fixer_password_show.setText(fixer?.password)
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
        JobSpinner.adapter = adapter
        JobSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        var job = viewModel.fixerLiveData.value?.job
        var index = viewModel.jobsLiveData.value?.indexOf(job)
        if (index != null)
            JobSpinner.setSelection(index)
    }

    private fun updateSpinner(cities: List<City>) {
        var regions = mutableListOf<String>()
        cities.forEach {
            regions.add(it.name!!)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, regions
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        RegionSpinner.adapter = adapter
        RegionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        var city = viewModel.fixerLiveData.value?.city
        var index = viewModel.citiesLiveData.value?.indexOf(city)
        if (index != null)
            RegionSpinner.setSelection(index)
    }

    override fun initListeners() {
        super.initListeners()
        save_edit_profile_btn.setOnClickListener {
            fixer_edit_save_pb.visibility = View.VISIBLE
           viewModel.updateFixer(createFixer())
        }
    }

    private fun createFixer(): Fixer {
        var fixer = viewModel.fixerLiveData.value
        fixer?.name = fixer_name_show.text.toString()
        fixer?.mobile = fixer_phone_show.text.toString()
        fixer?.city =
            viewModel.citiesLiveData.value?.find { city -> city.name == RegionSpinner.selectedItem.toString() }
        fixer?.job =
            viewModel.jobsLiveData.value?.find { job -> job.name == JobSpinner.selectedItem.toString() }
        fixer?.identityNo = fixer_id_show.text.toString()
        fixer?.password = fixer_password_show.text.toString()
        return fixer!!
    }


}