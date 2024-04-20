package com.example.handsofcompassion.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityLoginDonationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginDonation : AppCompatActivity() {

    private lateinit var binding: ActivityLoginDonationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityLoginDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClickparadoar.setOnClickListener {

            startSearchOrNewDonationActivity()

        }
    }
    private fun startSearchOrNewDonationActivity() {
        val intent = Intent(this, SearchOrNewDonation::class.java)
        startActivity(intent)
    }
}