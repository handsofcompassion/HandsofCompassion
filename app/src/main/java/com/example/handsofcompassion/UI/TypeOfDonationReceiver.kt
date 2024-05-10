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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityTypeOfDonationReceiverBinding

class TypeOfDonationReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfDonationReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeOfDonationReceiverBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_type_of_donation_receiver)



    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCreateNewDonor
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.donationishere).toUpperCase()
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
            startSearchOrNewReceiverActivity()
        }
    }
    private fun startSearchOrNewReceiverActivity() {
        val intent = Intent(this, SearchOrNewDonation::class.java)
        startActivity(intent)
        finish()
    }
}