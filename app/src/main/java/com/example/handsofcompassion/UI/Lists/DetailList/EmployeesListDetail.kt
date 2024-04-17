package com.example.handsofcompassion.UI.Lists.DetailList

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Dialog.DialogLoading
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.EmployeesList
import com.example.handsofcompassion.UI.SearchOrNewDonation
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.databinding.ActivityEmployeesListDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesListDetail : AppCompatActivity() {

    private lateinit var binding : ActivityEmployeesListDetailBinding
    private val viewModel: ViewModelEmployees by viewModels()
    private lateinit var adapter: EmpplyeesAdapter
    private val employeesList: MutableList<Employees> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = EmpplyeesAdapter(this, employeesList)

        binding.btnAtualizar.setOnClickListener {

            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()

            if (name.isEmpty() || email.isEmpty()){

                Toast.makeText(applicationContext, "Preencha Todos os Campos.", Toast.LENGTH_LONG).show()
            }else {

                viewModel.updateEMployees(name, email, adapter)
                startEmployeesListActivity()
                binding.progress.visibility = View.VISIBLE


            }
        }
    }
    private fun startEmployeesListActivity() {
        val intent = Intent(this, EmployeesList::class.java)
        startActivity(intent)
    }
}