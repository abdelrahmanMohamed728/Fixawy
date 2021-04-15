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
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.subDepartmentLiveData.observe(this , {
            subdepartment_details_text_view.text = it.nameAr
        })
        viewModel.endFragmentLivedata.observe(this , {
            requestDetailsProgressbar.visibility = View.GONE
            finishFragment()
        })
        viewModel.rateUpdatedLiveData.observe(this , {
            showAcceptedSnackBar(requireView(),"تم التقييم بنجاح")
        })
        rate_fixer.setOnClickListener {
            if (viewModel.request?.fixer?.id != null)
                viewModel.rateFixer(viewModel.request?.fixer?.id!!,ratingBar.rating)
        }
    }

    override fun initListeners() {
        super.initListeners()
        cancel_button.setOnClickListener {
            requestDetailsProgressbar.visibility = View.VISIBLE
            viewModel.cancelRequest()
        }
        pending_accept_button.setOnClickListener {
            requestDetailsProgressbar.visibility = View.VISIBLE
            viewModel.acceptRequest()
        }
        pending_cancel_button.setOnClickListener {
            requestDetailsProgressbar.visibility = View.VISIBLE
            viewModel.cancelRequest()
        }

        finish_button.setOnClickListener {
            showAcceptedSnackBar(requireView(),"تم إرسال طلبك")
            requestDetailsProgressbar.visibility = View.VISIBLE
            viewModel.finishRequest()
        }

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
        dateTextView.text = viewModel.request?.date
        var priceString = viewModel.request?.price.toString()
        fixer_job_text_view.text = viewModel.request?.client?.mobile
        details_price_text_view.text = "$priceString ريال "
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
        if (viewModel.mode == Request.FIXER_ACCEPTED_MODE){
            finish__layout.visibility = View.VISIBLE
        }
    }

    private fun initClientView() {
        fixer_name_text_view.text = viewModel.request?.fixer?.name
        dateTextView.text = viewModel.request?.date
        fixer_job_text_view.text = viewModel.request?.fixer?.job?.name
        var priceString = viewModel.request?.price.toString()
        fixer_job_text_view.text = viewModel.request?.fixer?.mobile
        details_price_text_view.text = "$priceString ريال "
        if (viewModel.mode == Request.PAST_MODE){
            cancel_layout.visibility = View.GONE
            rateLayout.visibility = View.VISIBLE
        }
    }

}