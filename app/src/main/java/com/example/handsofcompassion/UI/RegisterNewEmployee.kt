package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.ViewModel.ViewModelAuth
import com.example.handsofcompassion.databinding.ActivityRegisterNewEmployeeBinding
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterNewEmployee : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterNewEmployeeBinding
    private val viewModel: ViewModelAuth by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterNewEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)


        //Tratamento para o hint do password sumir ao clicar.
        binding.textInputLayoutPassword.setOnClickListener {
            binding.textInputLayoutPassword.hint =
                null // Remover o hint quando clicar no TextInputLayout
        }
        binding.edtPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutPassword.hint =
                    null // Remover o hint quando o foco é recebido
            } else {
                binding.textInputLayoutPassword.hint =
                    getString(R.string.digitesuasenha) // Restaurar o hint quando o foco é perdido e o campo está vazio
            }
        }

        binding.btnCadastrar.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val name = binding.editName.text.toString()
            viewModel.createUserAttendant(name, email, password, object : AuthListneers {
                override fun onSucess(mensage: String) {

                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startLoginActivity()

                }

                override fun onFailure(error: String) {

                    Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()

                }
            })
        }


    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginOrSingInAttendant::class.java)
        startActivity(intent)
    }
}