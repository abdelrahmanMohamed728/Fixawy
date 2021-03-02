package com.example.basemodule2.base

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.base.BaseActivity
import com.example.base.BaseViewModel
import com.example.base.InitFragment
import com.example.basemodule2.R
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass



open class BaseFragment<T : BaseViewModel> : Fragment(), InitFragment {
    val viewModel: T by lazy { getViewModel(viewModelClass()) }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<T> {
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<T>).kotlin
    }

    fun addActivity(destActivity: AppCompatActivity) {
        val intent = Intent(activity, destActivity::class.java)
        startActivity(intent)
    }

    fun addFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(getContainer(), fragment, tag)?.addToBackStack("")?.commit()
    }

    fun addFragmentWithNavigation(route : Int){
        view?.findNavController()?.navigate(route)
    }

    fun finishFragment(){
        view?.findNavController()?.navigateUp()
    }

    fun addFragmentWithNavigationAndBundle(route : Int,bundle: Bundle){
        view?.findNavController()?.navigate(route,bundle)
    }

    fun addFragmentWithBundle(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(getContainer(), fragment, tag)?.addToBackStack("")?.commit()
    }

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setBackgroundTint(getSnackBarColor()).show()
    }

    fun showAcceptedSnackBar(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setBackgroundTint(getAcceptedColor()).show()
    }

    private fun getSnackBarColor() : Int{
        return ContextCompat.getColor(context!!, R.color.violet)
    }

    private fun getAcceptedColor() : Int {
        return ContextCompat.getColor(context!!, R.color.green)
    }

    private fun getBaseActivity(): BaseActivity {
        return (activity as BaseActivity)
    }

    private fun getContainer(): Int {
        var activity = getBaseActivity()
        return activity.container
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRecycler()
        initListeners()
        viewModel.errorsLiveData.observe(this , {
            showSnackBar(requireView(), it.messageAr)
        })
    }

    override fun initObservers() {

    }

    override fun initRecycler() {

    }

    override fun initListeners() {

    }

    fun changePartOfTextViewColor(textView: TextView, first: String, next: String, color: Int) {
        textView.setText(first + next, BufferType.SPANNABLE)
        val s = textView.text as Spannable
        val start: Int = 0
        val end: Int = first.length
        s.setSpan(
            ForegroundColorSpan(color),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.setText(s, BufferType.SPANNABLE)
    }

}
