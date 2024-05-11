package com.example.handsofcompassion.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryDonor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDonor @Inject constructor(private val repositoryDonor: RepositoryDonor) :
    ViewModel() {

    fun createDonor(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        listneers: AuthListneers
    ) {

        viewModelScope.launch {

            repositoryDonor.createDonor(
                name,
                cpf,
                phone,
                email,
                address,
                birth,
                listneers
            )
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getDonor(
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        viewModelScope.launch {

            repositoryDonor.getDonor(donorsList, adapter)

        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchDonors(
        typedText: String,
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        viewModelScope.launch {

            repositoryDonor.searchDonors(typedText, donorsList, adapter)


        }
    }

        @SuppressLint("NotifyDatasetChanged")
        fun searchDonorsCPF(
            typedText: String,
            donorsList: MutableList<Donor>,
            adapter: DonorAdapter
        ) {

            viewModelScope.launch {

                repositoryDonor.searchDonorsCPF(typedText, donorsList, adapter)


            }

        }
    }