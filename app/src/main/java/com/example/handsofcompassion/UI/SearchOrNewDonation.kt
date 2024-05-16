package com.example.handsofcompassion.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Objects.ItemSpacingDecoration
import com.example.handsofcompassion.R
import com.example.handsofcompassion.ViewModel.ViewModelDonor
import com.example.handsofcompassion.databinding.ActivitySearchOrNewDonationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchOrNewDonation : AppCompatActivity() {

    private lateinit var binding: ActivitySearchOrNewDonationBinding
    private lateinit var adapterDonors: DonorAdapter
    private val donorsList: MutableList<Donor> = mutableListOf()
    private val viewModel: ViewModelDonor by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchOrNewDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rvDonors = binding.rvDonors
        adapterDonors = DonorAdapter(this, donorsList)
        rvDonors.adapter = adapterDonors
        rvDonors.addItemDecoration(ItemSpacingDecoration(this, 0))



        binding.editName.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
            override fun onQueryTextChange(newText: String?): Boolean {


                viewModel.searchDonorsCPF(newText!!, donorsList, adapterDonors)
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
                return true
            }
        })




        binding.btnReceber.setOnClickListener {

            startCreateNewDonorActivity()

        }


    }

    private fun startCreateNewDonorActivity() {
        val intent = Intent(this, CreateNewDonor::class.java)
        startActivity(intent)
        finish()
    }
}