package com.example.handsofcompassion.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelReceiver @Inject constructor(private val repositoryReceiver: RepositoryReceiver) : ViewModel() {

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

}