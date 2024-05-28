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
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Foods.BasicBasketDonor
import com.example.handsofcompassion.UI.Foods.NonPerishableFoodsDonor
import com.example.handsofcompassion.UI.SearchOrNewDonation
import com.example.handsofcompassion.UI.TypeOfDonationDonor
import com.example.handsofcompassion.databinding.ActivityTypesOfFood2DonorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypesOfFoodDonor : AppCompatActivity() {

    private lateinit var binding: ActivityTypesOfFood2DonorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypesOfFood2DonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        binding.btnAlimentoNaoPerecivel.setOnClickListener {


            startTypeNonPereciblesActivity()

        }

        binding.btnCestaBasica.setOnClickListener {


          startTypebasicActivity()

        }


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbartypeFood
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
            startTypeOfDOnationDOnorActivity()
            finish()
        }
    }
    private fun startTypeOfDOnationDOnorActivity() {
        val intent = Intent(this, TypeOfDonationDonor::class.java)
        startActivity(intent)
        finish()
    }
    private fun startTypeNonPereciblesActivity() {
        val intent = Intent(this, NonPerishableFoodsDonor::class.java)
        startActivity(intent)
        finish()
    }

    private fun startTypebasicActivity() {
        val intent = Intent(this, BasicBasketDonor::class.java)
        startActivity(intent)
        finish()
    }
}