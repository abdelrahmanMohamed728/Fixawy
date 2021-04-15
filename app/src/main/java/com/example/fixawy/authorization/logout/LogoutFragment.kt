package com.example.fixawy.authorization.logout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseViewModel
import com.example.basemodule2.base.BaseFragment
import com.example.fixawy.MainActivity
import com.example.fixawy.R
import kotlinx.android.synthetic.main.fragment_logout.*


class LogoutFragment : BaseFragment<BaseViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout_btn.setOnClickListener {
            val pref: SharedPreferences = context?.getSharedPreferences("Auth",
                Context.MODE_PRIVATE
            )!!
            val editor: SharedPreferences.Editor = pref.edit()
            editor.clear()
            editor.apply()
            addActivity(MainActivity())
            activity?.finishAffinity()
        }
    }

}