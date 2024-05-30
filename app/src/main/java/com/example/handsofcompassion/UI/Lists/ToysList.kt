package com.example.handsofcompassion.UI.Lists

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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityToysListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ToysList : AppCompatActivity() {

    private lateinit var binding: ActivityToysListBinding
    private lateinit var adapterToys: AdapterToys
    private val toysList: MutableList<Toys> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToysListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvToys = binding.rvDonors
        adapterToys = AdapterToys(this, toysList)
        rvToys.adapter = adapterToys

        viewModel.getToys(toysList,adapterToys)


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbartoys
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.brinquedos).toUpperCase()
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
            startPrincipalActivity()
        }
    }

    private fun startPrincipalActivity() {
        val intent = Intent(this, SelectStockType::class.java)
        startActivity(intent)
        finish()
    }
}