package com.example.handsofcompassion.UI

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Lists.EmployeesList
import com.example.handsofcompassion.databinding.ActivityPrincipalattendantBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Principalattendant : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalattendantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalattendantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnSair.setOnClickListener {

            alertDialog()

        }
        binding.CardViewFuncioarios.setOnClickListener {

            startEmployeesActivity()

        }

    }

    private fun startSelectionActivity() {
        val intent = Intent(this, SelectionScreen::class.java)
        startActivity(intent)
    }

    private fun startEmployeesActivity() {
        val intent = Intent(this, EmployeesList::class.java)
        startActivity(intent)
        finish()
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.deslogar)
        alertDialog.setMessage(R.string.deslogarmsm)
        alertDialog.setPositiveButton(R.string.sim) { _, _ ->

            FirebaseAuth.getInstance().signOut()
            startSelectionActivity()

        }
        alertDialog.setNegativeButton(R.string.nao) { _, _ -> }
        alertDialog.show()
    }

}