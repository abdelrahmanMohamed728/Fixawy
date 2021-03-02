package com.example.fixawy.home.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.R
import com.example.fixawy.model.Fixer
import com.pchmn.materialchips.model.Chip
import kotlinx.android.synthetic.main.reserve_fragment.*

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
        initFixer()
        chips_view.filterableList = initDummyServices()
    }

    private fun initDummyServices() : List<Chip>{
        var list = mutableListOf<Chip>()
        list.add(Chip("Chair 1","2$"))
        list.add(Chip("Chair 2","8$"))
        return list
    }

    private fun initFixer() {
        fixer_name.text = fixer.name
        fixer_job.text = fixer.job?.name
    }

}