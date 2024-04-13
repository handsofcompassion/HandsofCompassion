package com.example.handsofcompassion.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivitySelectionScreen2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectionScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySelectionScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDoar.setOnClickListener{
            startLoginDonationActivity()
        }

        binding.btnReceber.setOnClickListener {
            startLoginReceiverActivity()
        }
        binding.btnPerfilAtendente.setOnClickListener {

            startLoginOrSingInAttendantActivity()
        }

    }

    private fun startLoginDonationActivity() {
        val intent = Intent(this, LoginDonation::class.java)
        startActivity(intent)
    }
    private fun startLoginReceiverActivity() {
        val intent = Intent(this, LoginReceiver::class.java)
        startActivity(intent)
    }
    private fun startLoginOrSingInAttendantActivity() {
        val intent = Intent(this, LoginOrSingInAttendant::class.java)
        startActivity(intent)
    }
}