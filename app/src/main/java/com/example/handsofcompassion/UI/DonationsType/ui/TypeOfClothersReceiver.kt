package com.example.handsofcompassion.UI.DonationsType.ui

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
import com.example.handsofcompassion.UI.Clothes.MensClothingChildrensReceiver
import com.example.handsofcompassion.UI.Clothes.MensClothingReceivers
import com.example.handsofcompassion.UI.Clothes.WomensClothingChildrensReceivers
import com.example.handsofcompassion.UI.Clothes.WomensClothingReceivers
import com.example.handsofcompassion.UI.SearchOrNewReceiver
import com.example.handsofcompassion.UI.TypeOfDonationReceiver
import com.example.handsofcompassion.databinding.ActivityTypeOfClothersReceiverBinding

class TypeOfClothersReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfClothersReceiverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeOfClothersReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        binding.feminino.setOnClickListener {

            startWomansActivity()

        }

        binding.masculino.setOnClickListener {

            startMensClothingActivity()

        }

        binding.infantilMasculino.setOnClickListener {

           startMensClothingChildrenReceiversAtionActivity()

        }

        binding.infantilFeminino.setOnClickListener {

          startWomansChildrenActivity()

        }


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbartypeClothes
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
        val intent = Intent(this, TypeOfDonationReceiver::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMensClothingActivity() {
        val intent = Intent(this, MensClothingReceivers::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMensClothingChildrenReceiversAtionActivity() {
        val intent = Intent(this, MensClothingChildrensReceiver::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWomansActivity() {
        val intent = Intent(this, WomensClothingReceivers::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWomansChildrenActivity() {
        val intent = Intent(this, WomensClothingChildrensReceivers::class.java)
        startActivity(intent)
        finish()
    }
}