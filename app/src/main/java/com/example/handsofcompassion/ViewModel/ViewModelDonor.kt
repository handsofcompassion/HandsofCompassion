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

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CPF = "extra_cpf"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_ADRESS = "extra_adress"
        const val EXTRA_BIRTH = "extra_birth"
    }

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

    @SuppressLint("NotifyDatasetChanged")
    fun updateDonors(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        id: String,
        adapter: DonorAdapter,
        listeners: AuthListneers
    ) {

        viewModelScope.launch {

            repositoryDonor.updateDonors(name, cpf, phone, email, address, birth, id, adapter, listeners)

        }
    }
     fun formatCpf(cpf: String?): String {
        return cpf?.replace("(\\d{3})(\\d{3})(\\d{3})(\\d{2})".toRegex(), "$1.$2.$3-$4") ?: ""
    }

     fun formatPhone(telefone: String?): String {
        return telefone?.replace("(\\d{2})(\\d{5})(\\d{4})".toRegex(), "($1) $2-$3") ?: ""
    }

     fun formatBirth(data: String?): String {
        return data?.replace("(\\d{4})(\\d{2})(\\d{2})".toRegex(), "$3/$2/$1") ?: ""
    }

    }