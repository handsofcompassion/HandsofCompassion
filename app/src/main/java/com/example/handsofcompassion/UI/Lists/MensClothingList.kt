package com.example.handsofcompassion.UI.Lists

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
import com.example.handsofcompassion.Adapter.AdapterMansClothing
import com.example.handsofcompassion.Data.MensClothing
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityMensClothingListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MensClothingList : AppCompatActivity() {

    private lateinit var binding: ActivityMensClothingListBinding
    private lateinit var adapterMensClothing: AdapterMansClothing
    private val mensClothingList: MutableList<MensClothing> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMensClothingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvMensClothing = binding.rvMens
        adapterMensClothing = AdapterMansClothing(this,mensClothingList)
        rvMensClothing.adapter = adapterMensClothing

        viewModel.getMensClhoting(mensClothingList, adapterMensClothing)

        binding.editMens.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchMensClothing(newText!!, mensClothingList, adapterMensClothing)
                return true
            }

        })

        binding.editMens.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editMens.onActionViewCollapsed()
                mensClothingList.clear()
                adapterMensClothing.notifyDataSetChanged()
                viewModel.getMensClhoting(mensClothingList, adapterMensClothing)
                return true
            }
        })


    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarmens
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.masculino).toUpperCase()
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