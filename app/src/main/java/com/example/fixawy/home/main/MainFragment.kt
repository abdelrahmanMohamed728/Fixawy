package com.example.fixawy.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.OnClickItem
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.main.adapter.MainAdapter
import com.example.fixawy.home.main.sub_department_adapter.SubDepartmentAdapter
import com.example.fixawy.home.requests.adapter.RequestsAdapter
import com.example.fixawy.model.Client
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Request
import com.example.fixawy.model.SubDepartment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.past_requests_fragment.*

class MainFragment : BaseFragment<MainViewModel>() , OnClickItem{

    lateinit var subDepartmentAdapter: SubDepartmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_progress_bar.visibility = View.VISIBLE
        viewModel.getAllSubDepartments()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.subDepartmentsLiveData.observe(this , {
            home_progress_bar.visibility = View.GONE
            subDepartmentAdapter.subDepartments = it
            subDepartmentAdapter.notifyDataSetChanged()
        })
    }

    override fun initRecycler() {
        super.initRecycler()
        subDepartmentAdapter =
            SubDepartmentAdapter(
                requireContext(),
                listOf(),
                this,
            )
        home_recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        home_recyclerview.adapter = subDepartmentAdapter
    }


    override fun onItemClicked(position: Int) {

    }

}