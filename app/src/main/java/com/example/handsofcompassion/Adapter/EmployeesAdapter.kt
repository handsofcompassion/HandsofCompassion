package com.example.handsofcompassion.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handsofcompassion.databinding.ItemEmployeesBinding

class EmpplyeesAdapter(private val context: Context, private val employeesList: MutableList<com.example.handsofcompassion.Data.Employees>): RecyclerView.Adapter<EmpplyeesAdapter.EmployeesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
       val itemList = ItemEmployeesBinding.inflate(LayoutInflater.from(context),parent,false)
        return EmployeesViewHolder(itemList)
    }

    override fun getItemCount() = employeesList.size

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {

      holder.txtName.text = employeesList[position].name
      holder.txtEmail.text = employeesList[position].email
    }

    inner class EmployeesViewHolder(binding: ItemEmployeesBinding) : RecyclerView.ViewHolder(binding.root){

        val txtName = binding.tvName
        val txtEmail = binding.tvEmail

    }
}