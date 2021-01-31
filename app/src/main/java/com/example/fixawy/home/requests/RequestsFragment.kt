package com.example.fixawy.home.requests

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fixawy.R

class RequestsFragment : Fragment() {

    companion object {
        fun newInstance() = RequestsFragment()
    }

    private lateinit var viewModel: RequestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.requests_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}