package com.example.handsofcompassion.UI.Clothes

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Data.WomanClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfClothersReceiver
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityWomansClothingListBinding
import com.example.handsofcompassion.databinding.ActivityWomensClothingReceiversBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WomensClothingReceivers : AppCompatActivity() {
    private lateinit var binding: ActivityWomensClothingReceiversBinding
    private lateinit var adapterWomansClothing: AdapterWomansClothing
    private val womansList: MutableList<WomanClothing> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomensClothingReceiversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvWomans = binding.rvWomans
        adapterWomansClothing = AdapterWomansClothing(this, womansList)
        rvWomans.adapter = adapterWomansClothing

        viewModel.getWomansClothing(womansList, adapterWomansClothing)

        binding.editWomans.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchWomansCLothing(newText!!, womansList, adapterWomansClothing)
                return true
            }

        })

        binding.editWomans.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editWomans.onActionViewCollapsed()
                womansList.clear()
                adapterWomansClothing.notifyDataSetChanged()
                viewModel.getWomansClothing(womansList, adapterWomansClothing)
                return true
            }
        })



    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarwomans
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.feminino).toUpperCase()
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
        val intent = Intent(this, TypeOfClothersReceiver::class.java)
        startActivity(intent)
        finish()
    }
}