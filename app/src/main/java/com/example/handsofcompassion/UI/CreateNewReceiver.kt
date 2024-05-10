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
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.R
import com.example.handsofcompassion.databinding.ActivityCreateNewReceiverBinding

class CreateNewReceiver : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCreateNewDonor
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.cadastrarparareceber).toUpperCase()
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
        val intent = Intent(this, SearchOrNewReceiver::class.java)
        startActivity(intent)
        finish()
    }
}