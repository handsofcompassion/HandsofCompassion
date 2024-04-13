package com.example.handsofcompassion.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityLoginReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityLoginReceiverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClickparareceber.setOnClickListener {
            startSearchOrNewReceiverActivity()
        }

    }
    private fun startSearchOrNewReceiverActivity() {
        val intent = Intent(this, SearchOrNewReceiver::class.java)
        startActivity(intent) }
}