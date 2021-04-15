package com.example.fixawy.home.reserve

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.Fixer
import com.example.fixawy.model.Job
import com.example.fixawy.network.model.AddRequestDTO
import com.example.fixawy.network.repos.UserRepo
import com.pchmn.materialchips.model.Chip
import kotlinx.android.synthetic.main.fixer_sign_up_fragment.*
import kotlinx.android.synthetic.main.reserve_fragment.*
import org.koin.android.ext.android.get

class ReserveFragment : BaseFragment<ReserveViewModel>() {

    lateinit var fixer: Fixer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reserve_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fixer = arguments?.get("fixer") as Fixer
        if (fixer.job?.id != null)
            viewModel.getJobs(fixer.job?.id!!)
        reserveProgressBar.visibility = View.VISIBLE
        initFixer()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.jobsLiveData.observe(this, {
            reserveProgressBar.visibility = View.GONE
            updateJobSpinner(it)
        })
        viewModel.completedLiveData.observe(this,{
            reserveProgressBar.visibility = View.GONE
            showAcceptedSnackBar(requireView(),"تم الطلب بنجاح")
            finishFragment()
        })

    }

    override fun initListeners() {
        super.initListeners()

        reserveBtn.setOnClickListener {
            reserveProgressBar.visibility = View.VISIBLE
            viewModel.addRequest(getRequestDTO())
        }
    }

    private fun getRequestDTO(): AddRequestDTO {
        var addRequestDTO = AddRequestDTO()
        addRequestDTO.fixerId = fixer.id
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            addRequestDTO.orderDate =
                datePicker.year.toString() + "-" + (changeFormat(datePicker.month + 1)) + "-" + changeFormat(
                    datePicker.dayOfMonth
                )+"T"+changeFormat(timePicker.hour)+":"+changeFormat(timePicker.minute)+":"+"00"
        }
        addRequestDTO.subDepartmentId =
            viewModel.jobsLiveData.value?.find { job -> job.name == FixerJobSpinner.selectedItem }?.id
        var repo: UserRepo = get()
        addRequestDTO.customerId = repo.clientLiveData.value?.id
        addRequestDTO.price = priceTextView.text.toString().toIntOrNull()
        return addRequestDTO
    }

    private fun changeFormat(int: Int): String {
        return if (int < 10) {
            "0$int"
        } else
            "$int"
    }

    private fun updateJobSpinner(jobs: List<Job>) {
        var names = mutableListOf<String>()
        jobs.forEach {
            names.add(it.name!!)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, names
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        FixerJobSpinner.adapter = adapter
        FixerJobSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (fixer.prices != null && position<fixer.prices!!.size)
                priceTextView.text = fixer.prices?.get(position)?.minPrice.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                priceTextView.text = "0"
            }

        }
        FixerJobSpinner.setSelection(0)
    }

    private fun initFixer() {
        fixer_name.text = fixer.name
        fixer_job.text = fixer.job?.name
    }

}