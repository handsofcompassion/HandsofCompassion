package com.example.handsofcompassion.UI.Toys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityToysReceiverBinding

class ToysReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityToysReceiverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToysReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}