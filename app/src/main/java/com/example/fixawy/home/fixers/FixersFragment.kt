package com.example.fixawy.home.fixers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.OnClickItem
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.main.adapter.MainAdapter
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.SubDepartment
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.fixers_fragment.*
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.search_rv
import org.koin.android.ext.android.get

class FixersFragment : BaseFragment<FixersViewModel>() , OnClickItem {

    lateinit var mainAdapter: MainAdapter
    lateinit var department : SubDepartment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fixers_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixerProgressBar.visibility = View.VISIBLE
        department = arguments?.get("department") as SubDepartment
        viewModel.getAllFixers()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.fixersLiveData.observe(this , {
            var repo : UserRepo = get()
            var cityId = repo.clientLiveData.value?.city?.id
            fixerProgressBar.visibility = View.GONE
            mainAdapter.fixers = it.filter { item -> item.job?.id == department.id && item.city?.id == cityId }
            mainAdapter.notifyDataSetChanged()
        })
    }

    override fun initRecycler() {
        super.initRecycler()
        mainAdapter =
            MainAdapter(
                requireContext(),
                listOf(),
                this
            )
        fixerRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fixerRecyclerView.adapter = mainAdapter
    }

    override fun onItemClicked(position: Int) {
        val bundle = bundleOf("fixer" to mainAdapter.fixers[position])
        addFragmentWithNavigationAndBundle(R.id.action_fixersFragment_to_reserveFragment,bundle)
    }

}