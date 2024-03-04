package com.example.handsofcompassion.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SelectionScreen::class.java)
            startActivity(intent)
            finish()
        },1500)
        supportActionBar?.hide()
    }
}
