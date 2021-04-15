package com.example.fixawy.home.client_profile.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.City
import com.example.fixawy.model.Client
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.client_edit_profile_fragment.*
import kotlinx.android.synthetic.main.fixer_edit_profile_fragment.*
import org.koin.android.ext.android.get

class ClientEditProfileFragment : BaseFragment<ClientEditProfileViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.client_edit_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        client_profile_progress_bar.visibility = View.VISIBLE
        viewModel.getCities()
        updateUser()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.clientUpdatedLiveData.observe(this , {
            client_profile_save_progress_bar.visibility = View.GONE
            UserRepo.getInstance().clientLiveData.value = viewModel.clientLiveData.value
            showAcceptedSnackBar(requireView(), getString(R.string.edit_successful))
            finishFragment()
        })
        viewModel.citiesLiveData.observe(this, {
            client_profile_progress_bar.visibility = View.GONE
            updateSpinner(it)
        })
    }

    override fun initListeners() {
        super.initListeners()
        client_save_edit_profile_btn.setOnClickListener {
            if (fieldsAreNotEmpty()) {
                viewModel.updateClient(createClient())
            } else {
                showSnackBar(requireView(), getString(R.string.edit_failed))
            }
        }
    }

    private fun updateUser() {
        var repo: UserRepo = get()
        var client = repo.clientLiveData.value
        viewModel.clientLiveData.value = client
        client_name_show.setText(client?.name)
        client_phone_show.setText(client?.mobile)
        client_password_show.setText(client?.password)
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
        ClientRegionSpinner.adapter = adapter
        ClientRegionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        var city = viewModel.clientLiveData.value?.city
        var index = viewModel.citiesLiveData.value?.indexOf(city)
        if (index != null)
            ClientRegionSpinner.setSelection(index)
    }

    private fun fieldsAreNotEmpty(): Boolean {
        return (client_name_show.text.isNotEmpty())
    }

    private fun createClient(): Client {
        var client = viewModel.clientLiveData.value
        client?.name = client_name_show.text.toString()
        client?.city = viewModel.citiesLiveData.value?.find { city -> city.name == ClientRegionSpinner.selectedItem.toString() }
        client?.password = client_password_show.text.toString()
        return client!!
    }


}