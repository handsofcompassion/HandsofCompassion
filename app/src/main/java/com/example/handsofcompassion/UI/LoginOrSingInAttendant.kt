package com.example.handsofcompassion.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityLoginOrSingInAttendantBinding

class LoginOrSingInAttendant : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOrSingInAttendantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOrSingInAttendantBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Tratamento para o hint do password sumir ao clicar.
       binding.textInputLayout.setOnClickListener {
            binding.textInputLayout.hint = null // Remover o hint quando clicar no TextInputLayout
        }
        binding.edtPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.textInputLayout.hint = null // Remover o hint quando o foco é recebido
            } else {
                binding.textInputLayout.hint = getString(R.string.digitesuasenha) // Restaurar o hint quando o foco é perdido e o campo está vazio
            }
        }




    }
}
