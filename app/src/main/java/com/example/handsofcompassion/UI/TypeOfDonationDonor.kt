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
import com.example.handsofcompassion.databinding.ActivityCreateNewDonorBinding
import com.example.handsofcompassion.databinding.ActivityTypeOfDonationDonorBinding
import com.example.handsofcompassion.databinding.ActivityTypeOfDonationReceiverBinding

class TypeOfDonationDonor : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfDonationDonorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeOfDonationDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCreateNewDonor
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.doe_btn).toUpperCase()
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
            finish()
        }
    }
    private fun startSearchOrNewReceiverActivity() {
        val intent = Intent(this, SearchOrNewDonation::class.java)
        startActivity(intent)
        finish()
    }
}