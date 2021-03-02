package com.example.fixawy.home.main.sub_department_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.OnClickItem
import com.example.fixawy.R
import com.example.fixawy.model.SubDepartment
import kotlinx.android.synthetic.main.sub_department_list_layout.view.*

class SubDepartmentAdapter (var context: Context, var subDepartments: List<SubDepartment>, var onClickItem: OnClickItem)  :
    RecyclerView.Adapter<SubDepartmentAdapter.SubDepartmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubDepartmentViewHolder {
        return SubDepartmentViewHolder(
            LayoutInflater.from(context).inflate(R.layout.sub_department_list_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return subDepartments.size
    }

    override fun onBindViewHolder(holder: SubDepartmentViewHolder, position: Int) {
        var subDepartment = subDepartments[position]
        holder.nameTextView.text = subDepartment.name
        holder.layout.setOnClickListener {
            onClickItem.onItemClicked(position)
        }
    }


    class SubDepartmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView: TextView = view.sub_department_name
        var layout : LinearLayout = view.sub_department_layout
    }
}