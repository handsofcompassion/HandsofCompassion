package com.example.handsofcompassion.UI

import android.app.LocaleManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import com.example.handsofcompassion.Objects.LanguageManager
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityLanguageBinding
import com.example.handsofcompassion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class language : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgBrasil.setOnClickListener {

            LanguageManager.setLocale(this, "pt")
            recreate()
            startSelectionActivity()

        }
        binding.imgEua.setOnClickListener {

            LanguageManager.setLocale(this, "en")
            recreate()
            startSelectionActivity()

        }
        binding.imgEsp.setOnClickListener {

            LanguageManager.setLocale(this, "es")
            recreate()
            startSelectionActivity()

        }
    }

    private fun startSelectionActivity() {
        val intent = Intent(this, SelectionScreen::class.java)
        startActivity(intent)
    }
}