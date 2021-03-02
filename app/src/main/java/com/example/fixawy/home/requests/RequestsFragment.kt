package com.example.fixawy.home.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.OnClickItem
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.home.requests.adapter.RequestsAdapter
import com.example.fixawy.model.Client
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Request
import com.example.fixawy.model.User
import com.example.fixawy.network.repos.UserRepo
import kotlinx.android.synthetic.main.requests_fragment.*
import org.koin.android.ext.android.get

class RequestsFragment : BaseFragment<RequestsViewModel>() , OnClickItem{

    lateinit var requestsAdapter : RequestsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.requests_fragment, container, false)
    }


    override fun initRecycler() {
        super.initRecycler()
        initDummyData()
        requestsAdapter =
            RequestsAdapter(
                requireContext(),
                initDummyData(),
                this,
                RequestsAdapter.ACTIVE_REQUESTS_MODE
            )
        active_requests_RV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        active_requests_RV.adapter = requestsAdapter
    }

    private fun initDummyData(): List<Request> {
        var list = mutableListOf<Request>()
        return list
    }

    override fun onItemClicked(position: Int) {
        var repo : UserRepo = get()
        var type = repo.type
        val bundle = bundleOf("request" to requestsAdapter.requests[position])
        if (type == User.CLIENT_TYPE) {
            openClientActiveRequests(position,bundle)
        }
        else{
            openFixerActiveRequests(position,bundle)
        }
    }

    private fun openFixerActiveRequests(position: Int, bundle: Bundle?) {
        val mode = requestsAdapter.requests[position].status!! + 2
        val bundle3 = bundleOf("mode" to mode)
        bundle?.putAll(bundle3)
        addFragmentWithNavigationAndBundle(
            R.id.action_requestsFragment2_to_requestDetailsFragment2,
            bundle!!
        )
    }

    private fun openClientActiveRequests(position: Int,bundle: Bundle?){
        val bundle3 = bundleOf("mode" to Request.ACTIVE_MODE)
        bundle?.putAll(bundle3)
        addFragmentWithNavigationAndBundle(
            R.id.action_requestsFragment_to_requestDetailsFragment,
            bundle!!
        )
    }

}