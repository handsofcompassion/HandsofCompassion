package com.example.handsofcompassion.UI.Toys

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
import com.example.handsofcompassion.Adapter.AdapterToys
import com.example.handsofcompassion.Data.Toys
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfClothersReceiver
import com.example.handsofcompassion.UI.SelectStockType
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityToysReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToysReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityToysReceiverBinding
    private lateinit var adapterToys: AdapterToys
    private val toysList: MutableList<Toys> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToysReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()

        val rvToys = binding.rvToys
        adapterToys = AdapterToys(this, toysList)
        rvToys.adapter = adapterToys

        viewModel.getToys(toysList,adapterToys)


        binding.editToys.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchToys(newText!!, toysList, adapterToys)
                return true
            }

        })

        binding.editToys.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editToys.onActionViewCollapsed()
                toysList.clear()
                adapterToys.notifyDataSetChanged()
                viewModel.getToys(toysList, adapterToys)
                return true
            }
        })
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
        val intent = Intent(this, TypeOfClothersReceiver::class.java)
        startActivity(intent)
        finish()
    }
}