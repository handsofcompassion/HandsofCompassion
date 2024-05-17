package com.example.handsofcompassion.Repository

import android.annotation.SuppressLint
import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Adapter.ReceiverAdapter
import com.example.handsofcompassion.Data.FireStore.ReceiverFireStore
import com.example.handsofcompassion.Data.Receiver
import com.example.handsofcompassion.Listneers.AuthListneers
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryReceiver @Inject constructor(private val receiverFireStore: ReceiverFireStore) {

    fun createReceiver(
        name: String,
        cpf: String,
        phone: String,
        email: String,
        address: String,
        birth: String,
        listneers: AuthListneers
    ) {

        receiverFireStore.createReceiver(name,
            cpf,
            phone,
            email,
            address,
            birth,
            listneers
        )
    }

    @SuppressLint("NotifyDatasetChanged")
    fun getReceiver(
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        receiverFireStore.getReceiver(receiversList, adapter)

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceivers(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        receiverFireStore.searchReceivers(typedText, receiversList, adapter)

    }

    @SuppressLint("NotifyDatasetChanged")
    fun searchReceiversCPF(
        typedText: String,
        receiversList: MutableList<Receiver>,
        adapter: ReceiverAdapter
    ) {

        receiverFireStore.searchReceiversCPF(typedText, receiversList, adapter)
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

        receiverFireStore.updateReceivers(name, cpf, phone, email, address, birth, id, adapter, listeners)

    }

}