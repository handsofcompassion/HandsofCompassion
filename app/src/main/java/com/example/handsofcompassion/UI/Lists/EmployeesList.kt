package com.example.handsofcompassion.UI.Lists

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.databinding.ActivityEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesList : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var adapterEMployees: EmpplyeesAdapter
    private val employeesList: MutableList<Employees> = mutableListOf()
    private val viewModel: ViewModelEmployees by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvEmployees = binding.rvEmployees
        adapterEMployees = EmpplyeesAdapter(this, employeesList)
        rvEmployees.adapter = adapterEMployees

        viewModel.getEmployees(employeesList, adapterEMployees)

    }
}