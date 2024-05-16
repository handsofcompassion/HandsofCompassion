package com.example.handsofcompassion.Repository

import android.annotation.SuppressLint
import com.example.handsofcompassion.Adapter.DonorAdapter
import com.example.handsofcompassion.Data.Donor
import com.example.handsofcompassion.Data.FireStore.DonorFireStore
import com.example.handsofcompassion.Listneers.AuthListneers
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryDonor @Inject constructor(private val donorFireStore: DonorFireStore) {

    fun createDonor(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        listneers: AuthListneers
    ) {

        donorFireStore.createDonor(
            name,
            cpf,
            phone,
            email,
            address,
            birth,
            listneers
        )
    }
    @SuppressLint("NotifyDatasetChanged")
    fun getDonor(
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        donorFireStore.getDonor(donorsList, adapter)

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchDonors(
        typedText: String,
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        donorFireStore.searchDonor(typedText, donorsList, adapter)

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchDonorsCPF(
        typedText: String,
        donorsList: MutableList<Donor>,
        adapter: DonorAdapter
    ) {

        donorFireStore.searchDonorsCPF(typedText, donorsList, adapter)

    }
}