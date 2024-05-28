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
import com.example.handsofcompassion.UI.Clothes.MensClothingChildrensDonor
import com.example.handsofcompassion.UI.Clothes.MensClothingDonor
import com.example.handsofcompassion.UI.Clothes.WomensClothingChildrensDonor
import com.example.handsofcompassion.UI.Clothes.WomensClothingDonor
import com.example.handsofcompassion.UI.SearchOrNewDonation
import com.example.handsofcompassion.UI.TypeOfDonationDonor
import com.example.handsofcompassion.databinding.ActivityTypeOfClothesDonorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeOfClothesDonor : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfClothesDonorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeOfClothesDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()


        binding.infantilMasculino.setOnClickListener {

            startMensClothingChildrenActivity()

        }

        binding.masculino.setOnClickListener {

            startMensClothingActivity()

        }

        binding.infantilFeminino.setOnClickListener {

            startWomensClothingChildrenActivity()

        }

        binding.feminino.setOnClickListener {

          startWomensClothingActivity()

        }


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbartypeClothes
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
        val intent = Intent(this, TypeOfDonationDonor::class.java)
        startActivity(intent)
        finish()
    }
    private fun startMensClothingChildrenActivity() {
        val intent = Intent(this, MensClothingChildrensDonor::class.java)
        startActivity(intent)
        finish()
    }
    private fun startMensClothingActivity() {
        val intent = Intent(this, MensClothingDonor::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWomensClothingActivity() {
        val intent = Intent(this, WomensClothingDonor::class.java)
        startActivity(intent)
        finish()
    }
    private fun startWomensClothingChildrenActivity() {
        val intent = Intent(this, WomensClothingChildrensDonor::class.java)
        startActivity(intent)
        finish()
    }

}