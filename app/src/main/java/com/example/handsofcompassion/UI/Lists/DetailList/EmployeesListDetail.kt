package com.example.handsofcompassion.UI.Lists.DetailList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.EmployeesList
import com.example.handsofcompassion.ViewModel.ViewModelEmployees
import com.example.handsofcompassion.databinding.ActivityEmployeesListDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesListDetail : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeesListDetailBinding
    private val viewModel: ViewModelEmployees by viewModels()
    private lateinit var adapter: EmpplyeesAdapter
    private val employeesList: MutableList<Employees> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Tratamento para o hint do password sumir ao clicar.
        binding.textInputLayoutPassword.setOnClickListener {
            binding.textInputLayoutPassword.hint = null // Remover o hint quando clicar no TextInputLayout
        }
        binding.edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutPassword.hint = null // Remover o hint quando o foco é recebido
            } else {
                binding.textInputLayoutPassword.hint =
                    getString(R.string.digitesuasenha) // Restaurar o hint quando o foco é perdido e o campo está vazio
            }
        }


        adapter = EmpplyeesAdapter(this, employeesList)

        binding.btnAtualizar.setOnClickListener {

            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.edtPassword.toString()
            val id = intent.extras!!.getString("id")


            viewModel.updateEMployees(name, email, password, id!!, adapter, object : AuthListneers {
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