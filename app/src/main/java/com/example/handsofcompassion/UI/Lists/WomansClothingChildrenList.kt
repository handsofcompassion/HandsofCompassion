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
import com.example.handsofcompassion.Adapter.AdapterWomansChildrenClothing
import com.example.handsofcompassion.Adapter.AdapterWomansClothing
import com.example.handsofcompassion.Data.WomanChildrenClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityWomansClothingChildrenListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class WomansClothingChildrenList : AppCompatActivity() {

    private lateinit var binding: ActivityWomansClothingChildrenListBinding
    private lateinit var adapterWomansClothing: AdapterWomansChildrenClothing
    private val womansChildrenClothingList: MutableList<WomanChildrenClothing> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomansClothingChildrenListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvWomansChildren = binding.rvDonors
        adapterWomansClothing = AdapterWomansChildrenClothing(this, womansChildrenClothingList)
        rvWomansChildren.adapter = adapterWomansClothing

        viewModel.getWomansClhotingChildren(womansChildrenClothingList, adapterWomansClothing)

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarwomandChildren
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.femininoInfntil).toUpperCase()
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