package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.example.handsofcompassion.ViewModel.ViewModelAuth
import com.example.handsofcompassion.databinding.ActivityLoginOrSingInAttendantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOrSingInAttendant : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOrSingInAttendantBinding
    private val viewModel: ViewModelAuth by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOrSingInAttendantBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Tratamento para o hint do password sumir ao clicar.
        binding.textInputLayout.setOnClickListener {
            binding.textInputLayout.hint = null // Remover o hint quando clicar no TextInputLayout
        }
        binding.edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayout.hint = null // Remover o hint quando o foco é recebido
            } else {
                binding.textInputLayout.hint =
                    getString(R.string.digitesuasenha) // Restaurar o hint quando o foco é perdido e o campo está vazio
            }
        }

        binding.btnLogin.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            viewModel.loginAttendant(email, password, object : AuthListneers {
                override fun onSucess(mensage: String) {

                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startPrincipalAttendentActivity()
                    binding.progress.visibility = View.VISIBLE

                }

                override fun onFailure(Error: String) {

                    Toast.makeText(applicationContext, Error, Toast.LENGTH_LONG).show()
                }
            })

        }

        binding.tvEsqueceuaSenha.setOnClickListener {
            startFOrgotPasswordActivity()
        }

        binding.btnSingUp.setOnClickListener {
            startSingUpActivity()
        }


    }

    private fun startPrincipalAttendentActivity() {
        val intent = Intent(this, Principalattendant::class.java)
        startActivity(intent)
    }

    private fun startFOrgotPasswordActivity() {
        val intent = Intent(this, ForgotPassword::class.java)
        startActivity(intent)
    }

    private fun startSingUpActivity() {
        val intent = Intent(this, RegisterNewEmployee::class.java)
        startActivity(intent)
    }
}
