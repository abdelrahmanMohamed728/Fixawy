package com.example.fixawy.home.client_profile.show_profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.MainActivity
import com.example.fixawy.R
import com.example.fixawy.model.Client
import org.koin.android.ext.android.get
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.client_profile_fragment.*

class ClientProfileFragment : BaseFragment<ClientProfileViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.client_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUser()
    }



    private fun bindUser() {
        var repo : UserRepo = get()
        if (repo.clientLiveData.value != null){
            updateClient(repo.clientLiveData.value!!)
        }
    }


    override fun initListeners() {
        super.initListeners()
        client_edit_profile_btn.setOnClickListener {
            addFragmentWithNavigation(R.id.action_clientProfileFragment_to_clientEditProfileFragment)
        }
        client_log_out.setOnClickListener {
            val pref: SharedPreferences = context?.getSharedPreferences("Auth",
                Context.MODE_PRIVATE
            )!!
            val editor: SharedPreferences.Editor = pref.edit()
            editor.clear()
            editor.apply()
            addActivity(MainActivity())
            activity?.finishAffinity()
        }
    }

    override fun initObservers() {
        super.initObservers()
        var repo : UserRepo = get()
        if (repo.type == UserRepo.CLIENT_TYPE)
            repo.clientLiveData.observe(this,{
                updateClient(it)
            })
    }

    private fun updateClient(client: Client) {
        client_name_edit.text = client.name
        client_region_edit.text = client.city?.name
        client_phone_edit.text = client.mobile
    }
}