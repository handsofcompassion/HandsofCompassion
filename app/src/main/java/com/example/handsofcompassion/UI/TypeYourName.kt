package com.example.handsofcompassion.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityTypeYourNameBinding

class TypeYourName : AppCompatActivity() {

    private lateinit var binding: ActivityTypeYourNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeYourNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}