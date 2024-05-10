package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivitySearchOrNewReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchOrNewReceiver : AppCompatActivity() {
    private lateinit var binding: ActivitySearchOrNewReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySearchOrNewReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReceber.setOnClickListener {

            startCreateNewDonorActivity()

        }

        }
    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, CreateNewReceiver::class.java)
        startActivity(intent)
        finish()
    }
    }


