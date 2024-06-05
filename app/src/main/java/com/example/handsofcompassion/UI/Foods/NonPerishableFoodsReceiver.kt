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
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.AdapterNonPerishable
import com.example.handsofcompassion.Data.NonPerishable
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.DonationsType.ui.TypeOfFoodReceiver
import com.example.handsofcompassion.UI.DonationsType.ui.TypesOfFoodDonor
import com.example.handsofcompassion.ViewModel.ViewModelStock
import com.example.handsofcompassion.databinding.ActivityNonPerishableFoodsReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NonPerishableFoodsReceiver : AppCompatActivity() {

    private lateinit var binding: ActivityNonPerishableFoodsReceiverBinding
    private lateinit var adapterNonPerecible: AdapterNonPerishable
    private val nonPerecibleList: MutableList<NonPerishable> = mutableListOf()
    private val viewModel: ViewModelStock by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNonPerishableFoodsReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsToolBar()


        val rvNonPerishable = binding.rvNoperecibles
        adapterNonPerecible = AdapterNonPerishable(this, nonPerecibleList)
        rvNonPerishable.adapter = adapterNonPerecible

        Log.d("ActivityAdapter", "Adapter configurado com sucesso")

        viewModel.getNonPerecible(nonPerecibleList, adapterNonPerecible)

        binding.editNonperecibles.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchNonPerecibles(newText!!, nonPerecibleList, adapterNonPerecible)
                return true
            }

        })

        binding.editNonperecibles.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editNonperecibles.onActionViewCollapsed()
                nonPerecibleList.clear()
                adapterNonPerecible.notifyDataSetChanged()
                viewModel.getNonPerecible(nonPerecibleList, adapterNonPerecible)
                return true
            }
        })
    }

    private fun settingsToolBar() {
        val toolbar = binding.toolbarnonPerecibles
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
