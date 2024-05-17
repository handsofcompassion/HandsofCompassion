package com.example.handsofcompassion.UI.Profile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityDonorsListDetailBinding
import com.example.handsofcompassion.databinding.ActivityProfileDonorsBinding

class ProfileDonors : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDonorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDonorsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_profile_donors)

    }
}