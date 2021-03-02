package com.example.fixawy.home.search

import android.os.Bundle
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
import kotlinx.android.synthetic.main.search_fragment.*

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
        initDummyData()
    }

    override fun initListeners() {
        super.initListeners()
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (!newText.isNullOrEmpty()) {
                    mainAdapter.fixers = fixers.filter {fixer ->
                        fixer.name?.toLowerCase()?.contains(newText.toLowerCase())!!
                    }
                    mainAdapter.notifyDataSetChanged()
                    true
                } else
                    false
            }

        })
    }

    override fun initRecycler() {
        super.initRecycler()
        fixers = initDummyData()
        mainAdapter =
            MainAdapter(
                requireContext(),
                initDummyData().filter { it.name.contains(searchview.query) },
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