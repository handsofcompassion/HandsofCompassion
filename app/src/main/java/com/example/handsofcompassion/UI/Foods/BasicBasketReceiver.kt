package com.example.handsofcompassion.UI.Foods

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
import com.example.handsofcompassion.Adapter.AdapterBasicBasket
import com.example.handsofcompassion.Data.BasicBasket
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfFoodReceiver
import com.example.handsofcompassion.UI.DonationsType.ui.TypesOfFoodDonor
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityBasicBasketReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicBasketReceiver : AppCompatActivity() {

    private lateinit var binding : ActivityBasicBasketReceiverBinding
    private lateinit var adapterBasicBasket: AdapterBasicBasket
    private val basicBasketList: MutableList<BasicBasket> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBasketReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)


        settingsToolBar()

        val rvBasicBasket = binding.rvDonors
        adapterBasicBasket = AdapterBasicBasket(this, basicBasketList)
        rvBasicBasket.adapter = adapterBasicBasket

        viewModel.getBasicBasket(basicBasketList,adapterBasicBasket)

        binding.editCestabasica.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchBasketBasic(newText!!, basicBasketList, adapterBasicBasket)
                return true
            }

        })

        binding.editCestabasica.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editCestabasica.onActionViewCollapsed()
                basicBasketList.clear()
                adapterBasicBasket.notifyDataSetChanged()
                viewModel.getBasicBasket(basicBasketList, adapterBasicBasket)
                return true
            }
        })

    }
    private fun settingsToolBar() {
        val toolbar = binding.toolbarCestaBasica
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
        val intent = Intent(this, TypeOfFoodReceiver::class.java)
        startActivity(intent)
        finish()
    }
}