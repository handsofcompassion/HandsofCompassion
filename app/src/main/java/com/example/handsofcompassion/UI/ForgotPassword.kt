package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.ViewModel.ViewModelAuth
import com.example.handsofcompassion.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val viewModel: ViewModelAuth by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnForgotPassword.setOnClickListener {

            val email = binding.editEmail.text.toString()

            viewModel.forgotPassword(email, object : AuthListneers {
                override fun onSucess(mensage: String) {
                    Toast.makeText(applicationContext, mensage, Toast.LENGTH_LONG).show()
                    startLoginActivity()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                }

            }
            )
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginOrSingInAttendant::class.java)
        startActivity(intent)
    }

}
