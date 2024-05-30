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
import com.example.handsofcompassion.UI.Lists.BasicBasketList
import com.example.handsofcompassion.UI.Lists.MensClothingChildrenList
import com.example.handsofcompassion.UI.Lists.MensClothingList
import com.example.handsofcompassion.UI.Lists.NonPerishableList
import com.example.handsofcompassion.UI.Lists.ToysList
import com.example.handsofcompassion.UI.Lists.WomansClothingChildrenList
import com.example.handsofcompassion.UI.Lists.WomansClothingList
import com.example.handsofcompassion.databinding.ActivitySelectStockTypeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectStockType : AppCompatActivity() {

    private lateinit var binding: ActivitySelectStockTypeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectStockTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()


        binding.cardBasicBasket.setOnClickListener {

            startBasicBasketListActivity()

        }

        binding.cardNaoPerecivel.setOnClickListener {

            startNonPerishableListAtctivity()

        }

        binding.cardRoupaMasculina.setOnClickListener {

            startMensClothingListActivity()

        }

        binding.cardMasculinoInfantil.setOnClickListener {

            startMensClothingChildrenListActivity()
            
        }

        binding.cardFeminino.setOnClickListener {

            startWomansClothingListActivity()

        }

        binding.cardFemininoInfantil.setOnClickListener {

            startWomansClothingChildrenListAtctivity()

        }

        binding.cardBrinquedos.setOnClickListener {

            startToysListActivity()

        }

    }

    private fun settingsToolBar() {
        val toolbar = binding.toolSelectTypeStock
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.estoque).toUpperCase()
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
            startPrincipalAtendentactivity()
        }
    }

    private fun startPrincipalAtendentactivity() {
        val intent = Intent(this, Principalattendant::class.java)
        startActivity(intent)
        finish()
    }

    private fun startBasicBasketListActivity() {
        val intent = Intent(this, BasicBasketList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startNonPerishableListAtctivity() {
        val intent = Intent(this, NonPerishableList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMensClothingListActivity() {
        val intent = Intent(this, MensClothingList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMensClothingChildrenListActivity() {
        val intent = Intent(this, MensClothingChildrenList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWomansClothingListActivity() {
        val intent = Intent(this, WomansClothingList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startWomansClothingChildrenListAtctivity() {
        val intent = Intent(this, WomansClothingChildrenList::class.java)
        startActivity(intent)
        finish()
    }

    private fun startToysListActivity() {
        val intent = Intent(this, ToysList::class.java)
        startActivity(intent)
        finish()
    }
}