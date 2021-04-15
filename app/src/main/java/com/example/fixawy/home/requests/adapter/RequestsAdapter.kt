package com.example.fixawy.home.requests.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.base.OnClickItem
import com.example.fixawy.R
import com.example.fixawy.model.Request
import com.example.fixawy.model.User
import kotlinx.android.synthetic.main.reservation_layout.view.*

class RequestsAdapter (var context: Context, var requests: MutableList<Request>, var onClickItem: OnClickItem ,
                       var mode : Int,var type : Int) :
    RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        return RequestsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.reservation_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        var request = requests[position]
        if (type == User.CLIENT_TYPE) {
            holder.personName.text = request.fixer?.name
            holder.phone.text = request.fixer?.mobile
        }
        else {
            holder.personName.text = request.client?.name
            holder.phone.text = request.client?.mobile
        }
        holder.date.text = request.date
        if (mode == ACTIVE_REQUESTS_MODE) {
            if (request.status == Request.ACCEPTED_STATUS) {
                holder.status.background =
                    ContextCompat.getDrawable(context, R.drawable.accepted_background)
                holder.status.text = context.getString(R.string.accepted_status)
            } else if (request.status == Request.PENDING_STATUS) {
                holder.status.background =
                    ContextCompat.getDrawable(context, R.drawable.status_background)
                holder.status.text = context.getString(R.string.pending_status)
            }
        }
        else {
            holder.status.visibility = View.GONE
        }
        holder.layout.setOnClickListener {
            onClickItem.onItemClicked(position)
        }
    }


    class RequestsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var personName: TextView = view.person_name_text_view
        var date: TextView = view.date_text_view
        var status: TextView = view.status_text_view
        var layout: LinearLayout = view.request_linear_layout
        var phone : TextView = view.person_number_text_view
    }

    companion object{
        const val ACTIVE_REQUESTS_MODE = 0
        const val PAST_REQUESTS_MODE = 1
    }
}