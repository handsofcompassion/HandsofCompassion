package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityDonationSuccessBinding

class DonationSuccess : AppCompatActivity() {

    private lateinit var binding: ActivityDonationSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClose.setOnClickListener {

            startSelectionScreenctivity()

        }

    }

    private fun startSelectionScreenctivity() {
        val intent = Intent(this, SelectionScreen::class.java)
        startActivity(intent)
    }

}