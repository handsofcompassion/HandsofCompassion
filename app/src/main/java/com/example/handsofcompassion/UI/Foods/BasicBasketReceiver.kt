package com.example.handsofcompassion.UI.Foods

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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypesOfFoodDonor
import com.example.handsofcompassion.databinding.ActivityBasicBasketReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicBasketReceiver : AppCompatActivity() {

    private lateinit var binding : ActivityBasicBasketReceiverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBasketReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)


        settingsToolBar()

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbasicbasket
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
        val intent = Intent(this, TypesOfFoodDonor::class.java)
        startActivity(intent)
        finish()
    }
}