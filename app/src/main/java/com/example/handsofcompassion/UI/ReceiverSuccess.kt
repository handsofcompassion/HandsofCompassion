package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.databinding.ActivityReceiverSuccessBinding

class ReceiverSuccess : AppCompatActivity() {

    private lateinit var binding: ActivityReceiverSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiverSuccessBinding.inflate(layoutInflater)
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