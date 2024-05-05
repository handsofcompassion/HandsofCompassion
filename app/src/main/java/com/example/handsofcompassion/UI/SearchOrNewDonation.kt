package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityCreateNewDonorBinding
import com.example.handsofcompassion.databinding.ActivitySearchOrNewDonationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchOrNewDonation : AppCompatActivity() {

    private lateinit var binding: ActivitySearchOrNewDonationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchOrNewDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReceber.setOnClickListener {

            startCreateNewDonorActivity()

        }

        binding.btnReceberAnonimo.setOnClickListener {

            startReceiveNewDonationsTypeActivity()

        }

    }
    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, CreateNewDonor::class.java)
        startActivity(intent)
        finish()
    }
    private fun startReceiveNewDonationsTypeActivity() {
        val intent = Intent(this, CreateNewDonor::class.java)
        startActivity(intent)
        finish()
    }
}