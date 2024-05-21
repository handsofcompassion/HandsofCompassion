package com.example.handsofcompassion.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelReceiver @Inject constructor(private val repositoryReceiver: RepositoryReceiver) : ViewModel() {


    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CPF = "extra_cpf"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_ADRESS = "extra_adress"
        const val EXTRA_BIRTH = "extra_birth"
    }

    fun createReceiver(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        listneers: AuthListneers
    ) {

        viewModelScope.launch {

            repositoryReceiver.createReceiver(
                name,
                cpf,
                phone,
                email,
                address,
                birth,
                listneers)
        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getReceiver(
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        viewModelScope.launch {

            repositoryReceiver.getReceiver(receiversList, adapter)

        }
    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceivers(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        viewModelScope.launch {

            repositoryReceiver.searchReceivers(typedText, receiversList, adapter)

        }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceiversCPF(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        viewModelScope.launch {

            repositoryReceiver.searchReceiversCPF(typedText, receiversList, adapter)

        }

    }

    @SuppressLint("NotifyDatasetChanged")
    fun updateReceivers(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        id: String,
        adapter: ReceiverAdapter,
        listeners: AuthListneers
    ) {

        repositoryReceiver.updateReceivers(name, cpf, phone, email, address, birth, id, adapter, listeners)

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