package com.example.handsofcompassion.UI

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfClothersReceiver
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfFoodReceiver
import com.example.handsofcompassion.UI.Toys.ToysReceiver
import com.example.handsofcompassion.databinding.ActivityTypeOfDonationReceiverBinding

class





TypeOfDonationReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfDonationReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeOfDonationReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        binding.btnAlimento.setOnClickListener {

            startTypeOfFoodActivity()

        }
        binding.btnRoupa.setOnClickListener {

            startTypeOfCothersActivity()

        }
        binding.btnBrinquedos.setOnClickListener {

            startToysActivity()

        }

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarTypeOfDonationReceiver
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.receber_btn).toUpperCase()
        val title = SpannableString(titleText)

        title.setSpan(
            StyleSpan(Typeface.BOLD), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            ForegroundColorSpan(Color.WHITE), 0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        title.setSpan(
            RelativeSizeSpan(1.5f),
            0, title.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        toolbar.title = title

        toolbar.setNavigationOnClickListener {
           startSearchOrNewDonationActivity()
        }
    }
    private fun startSearchOrNewDonationActivity() {
        val intent = Intent(this, SearchOrNewReceiver::class.java)
        startActivity(intent)
        finish()
    }
    private fun startTypeOfFoodActivity() {
        val intent = Intent(this, TypeOfFoodReceiver::class.java)
        startActivity(intent)
        finish()
    }
    private fun startTypeOfCothersActivity() {
        val intent = Intent(this, TypeOfClothersReceiver::class.java)
        startActivity(intent)
        finish()
    }

    private fun startToysActivity() {
        val intent = Intent(this, ToysReceiver::class.java)
        startActivity(intent)
        finish()
    }
}