package com.example.bmicalculator.controller.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmicalculator.model.BMI
import com.example.bmicalculator.R
import com.example.bmicalculator.model.datalayers.BMIHistoryDatabaseHandler

class Adapter(val mContext: Context, val mBMI: ArrayList<BMI>) :
    RecyclerView.Adapter<Adapter.holder>() {

    inner class holder(itemView: View, val mContext: Context, var arrayList: ArrayList<BMI>) :
        RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.tvNameH)
        var bmi = itemView.findViewById<TextView>(R.id.tvResH)
        var buttonDelete = itemView.findViewById<ImageView>(R.id.btn_delH)

        fun bindBMI(mBMI: BMI) {
            name.text = mBMI.name
            bmi.text = mBMI.resultBmi

            var position = adapterPosition
            var bmiList: BMI = arrayList[position]

            buttonDelete.setOnClickListener {
                deleteItem(bmiList.id!!.toInt())
                arrayList.remove(bmiList)
                notifyItemRemoved(position)
            }
        }

        fun deleteItem(id: Int) {
            var dbHandler: BMIHistoryDatabaseHandler = BMIHistoryDatabaseHandler(mContext)
            dbHandler.deleteBmi(id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_bmi, parent, false)
        return holder(view, mContext, mBMI)
    }

    override fun getItemCount(): Int {
        return mBMI.count()
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.bindBMI(mBMI[position])
    }
}

