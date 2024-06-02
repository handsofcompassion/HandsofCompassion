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
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityNonPerishableListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonPerishableList : AppCompatActivity() {

    private lateinit var binding: ActivityNonPerishableListBinding
    private lateinit var adapterNonPerecible: AdapterNonPerishable
    private val nonPerecibleList: MutableList<NonPerishable> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNonPerishableListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvNonPerishable = binding.rvNoperecibles
        adapterNonPerecible = AdapterNonPerishable(this, nonPerecibleList)
        rvNonPerishable.adapter = adapterNonPerecible

        Log.d("ActivityAdapter", "Adapter configurado com sucesso")

        viewModel.getNonPerecible(nonPerecibleList, adapterNonPerecible)



    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarnonPerecibles
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.naopereciveis).toUpperCase()
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