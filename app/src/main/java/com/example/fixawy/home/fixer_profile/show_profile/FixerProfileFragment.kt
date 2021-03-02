package com.example.fixawy.home.fixer_profile.show_profile

import android.os.Bundle
import android.view.LayoutInflater
import org.koin.android.ext.android.get
import android.view.View
import android.view.ViewGroup

import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.Fixer
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.fixer_profile_fragment.*

class FixerProfileFragment : BaseFragment<FixerProfileViewModel>(){




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fixer_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var repo : UserRepo = get()
        updateFixer(repo.fixerLiveData.value!!)
    }

    override fun initListeners() {
        super.initListeners()
        edit_profile_btn.setOnClickListener {
            addFragmentWithNavigation(R.id.action_fixerProfileFragment_to_fixerEditProfileFragment)
        }
    }

    override fun initObservers() {
        super.initObservers()
        if (UserRepo.getInstance().type == UserRepo.FIXER_TYPE)
        UserRepo.getInstance().fixerLiveData.observe(this,{
            updateFixer(it)
        })
    }


    private fun updateFixer(fixer: Fixer) {
        fixer_name_edit.text = fixer.name
        fixer_id_edit.text = ""
        fixer_job_edit.text = fixer.job?.name
        fixer_region_edit.text = fixer.city?.name
    }



}