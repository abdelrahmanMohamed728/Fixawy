package com.example.fixawy.home.request_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.Request
import kotlinx.android.synthetic.main.request_details_fragment.*

class RequestDetailsFragment : BaseFragment<RequestDetailsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_details_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.extractArgs(arguments)
        initViews()
        initClientView()
    }

    private fun initViews() {
        if(viewModel.mode == Request.ACTIVE_MODE || viewModel.mode == Request.PAST_MODE){
            initClientView()
        }
        else{
            initFixerView()
        }
    }

    private fun initFixerView() {
        fixer_name_text_view.text = viewModel.request?.client?.name
        fixer_job_text_view.visibility = View.GONE
        if (viewModel.mode != Request.FIXER_ACCEPTED_MODE){
            cancel_layout.visibility = View.GONE
        }
        if (viewModel.mode == Request.FIXER_PENDING_MODE){
            pending__layout.visibility = View.VISIBLE
        }
        if (viewModel.mode == Request.FIXER_PAST_MODE){
            pending__layout.visibility = View.GONE
            cancel_layout.visibility = View.GONE
        }
    }

    private fun initClientView() {
        fixer_name_text_view.text = viewModel.request?.fixer?.name
        fixer_job_text_view.text = viewModel.request?.fixer?.job?.name
        var priceString = viewModel.request?.price.toString()
        details_price_text_view.text = "$priceString $"
        if (viewModel.mode == Request.PAST_MODE){
            cancel_layout.visibility = View.GONE
        }
    }

}