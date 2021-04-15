package com.example.fixawy.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.OnClickItem
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.main.adapter.MainAdapter
import com.example.fixawy.model.Fixer
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.get

class SearchFragment : BaseFragment<SearchViewModel>(), OnClickItem {

    lateinit var fixers : List<Fixer>
    lateinit var mainAdapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFixers()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.fixersLiveData.observe(this , {
            var repo : UserRepo = get()
            var cityId = repo.clientLiveData.value?.city?.id
            mainAdapter.fixers = it.filter { fixer -> fixer.city?.id == cityId}
            mainAdapter.notifyDataSetChanged()
        })
    }

    override fun initListeners() {
        super.initListeners()
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var repo : UserRepo = get()
                var cityId = repo.clientLiveData.value?.city?.id
                return if (!newText.isNullOrEmpty()) {
                    mainAdapter.fixers = viewModel.fixersLiveData.value!!.filter {fixer -> (
                        fixer.name.toLowerCase().contains(newText.toLowerCase())&&fixer.city?.id == cityId)
                    }
                    mainAdapter.notifyDataSetChanged()
                    true
                } else {
                    mainAdapter.fixers = viewModel.fixersLiveData.value!!.filter { fixer -> fixer.city?.id == cityId}
                    mainAdapter.notifyDataSetChanged()
                 true
                }
            }

        })
    }

    override fun initRecycler() {
        super.initRecycler()
        fixers = initDummyData()
        mainAdapter =
            MainAdapter(
                requireContext(),
                mutableListOf(),
                this
            )
        search_rv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        search_rv.adapter = mainAdapter
    }

    private fun initDummyData(): List<Fixer> {
        var list = mutableListOf<Fixer>()
        return list.toList()
    }

    override fun onItemClicked(position: Int) {
        val bundle = bundleOf("fixer" to mainAdapter.fixers[position])
        addFragmentWithNavigationAndBundle(R.id.action_searchFragment_to_reserveFragment2,bundle)
    }
}