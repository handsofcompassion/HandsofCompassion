package com.example.handsofcompassion.UI.Lists.DetailList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.UI.Lists.EmployeesList
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
            val id = intent.extras!!.getString("id")


            viewModel.updateEMployees(name,email,id!!,adapter, object : AuthListneers{
                override fun onSucess(mensage: String) {
                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startEmployeesListActivity()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                }
            })



        }
    }
    private fun startEmployeesListActivity() {
        val intent = Intent(this, EmployeesList::class.java)
        startActivity(intent)
        finish()
    }
}