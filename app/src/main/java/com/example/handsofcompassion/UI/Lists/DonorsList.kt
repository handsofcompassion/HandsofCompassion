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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Objects.ItemSpacingDecoration
import com.example.handsofcompassion.R
import com.example.handsofcompassion.UI.Principalattendant
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.databinding.ActivityDonorsListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DonorsList : AppCompatActivity() {

    private lateinit var binding: ActivityDonorsListBinding
    private lateinit var adapterDonors: DonorAdapter
    private val donorsList: MutableList<Donor> = mutableListOf()
    private val viewModel: ViewModelDonor by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDonorsListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        settingsToolBar()

        val rvDonors = binding.rvDonors
        adapterDonors = DonorAdapter(this, donorsList)
        rvDonors.adapter = adapterDonors
        rvDonors.addItemDecoration(ItemSpacingDecoration(this, 0))

        viewModel.getDonor(donorsList, adapterDonors)

        binding.editName.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.searchDonors(newText!!, donorsList, adapterDonors)
                return true
            }

        })

        binding.editName.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClose(): Boolean {
                binding.editName.onActionViewCollapsed()
                donorsList.clear()
                adapterDonors.notifyDataSetChanged()
                viewModel.getDonor(donorsList, adapterDonors)
                return true
            }
        })

    }

    private fun settingsToolBar() {
        val toolbar = binding.toolbarEmployees
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setTitleTextColor(Color.WHITE)
        val titleText = resources.getString(R.string.doadores).toUpperCase()
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
        val intent = Intent(this, Principalattendant::class.java)
        startActivity(intent)
        finish()
    }
}